(ns {{name}}.webapp
  (:require [buddy.auth.backends :as bab]
            [clojure.tools.logging :as log]
            [integrant.core :as ig]
            [ow.lifecycle :as owl]
            [ow.webapp.async :as owa]
            [ow.webapp.common.middlewares :as owcm]))

(defn construct [request-ch port]
  (let [auth-backend    (bab/basic {:realm "{{name}}"
                                    :authfn (fn [request {:keys [username password] :as authdata}]
                                              username)})
        middleware      (owcm/make-middleware :api?                   true
                                              :test-environment?      true  ;; TODO: implement properly (ow.app should know?!?)
                                              :authentication-backend auth-backend)
        httpkit-options {:port port}]
    (owa/construct :webapp          request-ch
                   :middleware      middleware
                   :httpkit-options httpkit-options)))



(defmethod ig/init-key :{{name}}/webapp [_ {:keys [channels port] :as opts}]
  (if channels
    (-> (construct (:http-requests channels) (Integer. port))
        owl/start)
    opts))

(defmethod ig/halt-key! :{{name}}/webapp [_ {:keys [channels] :as this}]
  (if-not channels
    (owl/stop this)
    this))
