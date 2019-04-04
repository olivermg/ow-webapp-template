(ns user
  (:require [clojure.tools.logging :as log]
            [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [ow.app :as oa]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)

(defonce +app+ nil)

(defn start [& {:keys [profile]}]
  (when-not +app+
    (alter-var-root #'+app+
                    (fn [_]
                      (oa/run (or profile :dev))))))

(defn stop []
  (when +app+
    (alter-var-root #'+app+
                    (fn [app]
                      (oa/quit app)))))

(defn reset []
  (do (user/stop)
      (Thread/sleep 500)
      (refresh :after 'user/start)))
