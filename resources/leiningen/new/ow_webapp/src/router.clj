(ns {{name}}.router
  (:require [clojure.tools.logging :as log]
            [integrant.core :as ig]
            [ow.lifecycle :as owl]
            [ow.webapp.async.router :as owar]))

(defn construct [request-ch routed-request-ch]
  (let [routes ["/" [["foo" :foo]]]]
    (owar/construct :http-router request-ch routed-request-ch routes)))



(defmethod ig/init-key :{{name}}/router [_ {:keys [channels] :as opts}]
  (if channels
    (-> (construct (:http-requests channels) (:routed-http-requests channels))
        owl/start)
    opts))

(defmethod ig/halt-key! :{{name}}/router [_ {:keys [channels] :as this}]
  (if-not channels
    (owl/stop this)
    this))
