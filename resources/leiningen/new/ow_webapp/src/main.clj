(ns {{name}}.main
  (:gen-class)
  (:require [ow.main :as owm]))

(defn -main [& args]
  (apply owm/main args))
