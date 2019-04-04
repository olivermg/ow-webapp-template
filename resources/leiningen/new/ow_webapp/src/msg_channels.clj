(ns {{name}}.msg-channels
  (:require [clojure.core.async :as a]
            [clojure.tools.logging :as log]
            [integrant.core :as ig]
            [ow.comm :as owc]
            [ow.lifecycle :as owl]))

(defn construct []
  (let [http-requests              (a/chan)
        routed-http-requests       (a/chan)
        routed-http-requests-pub   (a/pub routed-http-requests owc/topic-fn)
        foo-requests               (a/sub routed-http-requests-pub :foo (a/chan))]
    (owl/construct ::msg-channels :msg-channels
                   {:http-requests             http-requests
                    :routed-http-requests      routed-http-requests
                    :foo-requests              foo-requests})))



(defmethod ig/init-key :{{name}}/msg-channels [_ {:keys [http-requests] :as opts}]
  (if-not http-requests
    (-> (construct)
        owl/start)
    opts))

(defmethod ig/halt-key! :{{name}}/msg-channels [_ {:keys [http-requests routed-http-requests foo-requests] :as this}]
  (when http-requests
    (a/close! http-requests))
  (when routed-http-requests
    (a/close! routed-http-requests))
  (when foo-requests
    (a/close! foo-requests))
  (-> (owl/stop this)
      (dissoc :http-requests :routed-http-requests :foo-requests)))
