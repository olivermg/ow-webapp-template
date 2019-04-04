(ns {{name}}.foo-resource
  (:require [clojure.tools.logging :as log]
            [integrant.core :as ig]
            [liberator.core :as lc]
            [ow.comm :as owc]
            [ow.lifecycle :as owl]
            [ow.webapp.common.resources :as owcr]))

(defn construct [ch]
  (owc/construct :foo-resource ch
                 (let [resource (lc/resource owcr/default-api-resource
                                             :allowed-methods #{:post}
                                             :authorized? (fn [{:keys [request] :as ctx}]
                                                            (:identity request))
                                             :post! (fn [{:keys [request] :as ctx}]
                                                      (log/warn "got post" request))
                                             :handle-created (fn [{:keys [request] :as ctx}]
                                                               {:id :xyz :message "yup"}))]
                   (fn [this req]
                     (log/trace "foo request" req)
                     (resource req)))))



(defmethod ig/init-key :{{name}}/foo-resource [_ {:keys [channels] :as opts}]
  (if channels
    (-> (construct (:foo-requests channels))
        owl/start)
    opts))

(defmethod ig/halt-key! :{{name}}/foo-resource [_ {:keys [channels] :as this}]
  (if-not channels
    (owl/stop this)
    this))
