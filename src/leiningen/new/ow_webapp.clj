(ns leiningen.new.ow-webapp
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "ow-webapp"))

(defn ow-webapp
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' ow-webapp project.")
    (->files data
             ["bin/start-repl.sh"                      (render "bin/start-repl.sh")]
             ["CHANGELOG.md"                           (render "CHANGELOG.md")]
             ["dev/user.clj"                           (render "dev/user.clj")]
             ["doc/intro.md"                           (render "doc/intro.md")]
             ["Dockerfile"                             (render "Dockerfile")]
             [".dockerignore"                          (render ".dockerignore")]
             [".gitignore"                             (render ".gitignore")]
             ["LICENSE"                                (render "LICENSE")]
             ["project.clj"                            (render "project.clj")]
             ["README.md"                              (render "README.md")]
             ["resources/config.edn"                   (render "resources/config.edn")]
             ["resources/log4j2.xml"                   (render "resources/log4j2.xml")]
             ["resources/log4j2-dev.xml"               (render "resources/log4j2-dev.xml")]
             ["src/{{sanitized}}/foo_resource.clj"     (render "src/foo_resource.clj")]
             ["src/{{sanitized}}/main.clj"             (render "src/main.clj")]
             ["src/{{sanitized}}/msg_channels.clj"     (render "src/msg_channels.clj")]
             ["src/{{sanitized}}/router.clj"           (render "src/router.clj")]
             ["src/{{sanitized}}/webapp.clj"           (render "src/webapp.clj")])))
