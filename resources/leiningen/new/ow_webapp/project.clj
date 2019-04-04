(defproject {{name}} "0.1.0-SNAPSHOT"

  :description "FIXME: write description"

  :url "http://example.com/FIXME"

  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[cljow-app "0.1.0-SNAPSHOT"]
                 [cljow-webapp "0.1.1-SNAPSHOT"]
                 [integrant "0.7.0"]
                 [liberator "0.15.2"]
                 [org.apache.logging.log4j/log4j-core "2.11.2"]
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/core.async "0.4.490"]
                 [org.clojure/tools.logging "0.4.1"]]

  :main ^:skip-aot {{name}}.main

  :target-path "target/%s"
  :jar-name "{{name}}.jar"
  :uberjar-name "{{name}}-standalone.jar"

  :profiles {:dev {:source-paths ["dev"]
                   :repl-options {:init-ns user}
                   :jvm-opts     ["-Dlog4j2.configurationFile=log4j2-dev.xml"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]]}

             :uberjar {:aot :all}})
