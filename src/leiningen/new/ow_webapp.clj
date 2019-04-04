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
             ["bin/start-repl.sh"                      (render "bin/start-repl.sh" data)]
             ["CHANGELOG.md"                           (render "CHANGELOG.md" data)]
             ["dev/user.clj"                           (render "dev/user.clj" data)]
             ["doc/intro.md"                           (render "doc/intro.md" data)]
             ["Dockerfile"                             (render "Dockerfile" data)]
             [".dockerignore"                          (render ".dockerignore" data)]
             [".env"                                   (render ".env" data)]
             [".gitignore"                             (render ".gitignore" data)]
             ["LICENSE"                                (render "LICENSE" data)]
             ["project.clj"                            (render "project.clj" data)]
             ["README.md"                              (render "README.md" data)]
             ["resources/config.edn"                   (render "resources/config.edn" data)]
             ["resources/log4j2.xml"                   (render "resources/log4j2.xml" data)]
             ["resources/log4j2-dev.xml"               (render "resources/log4j2-dev.xml" data)]
             ["src/{{sanitized}}/foo_resource.clj"     (render "src/foo_resource.clj" data)]
             ["src/{{sanitized}}/main.clj"             (render "src/main.clj" data)]
             ["src/{{sanitized}}/msg_channels.clj"     (render "src/msg_channels.clj" data)]
             ["src/{{sanitized}}/router.clj"           (render "src/router.clj" data)]
             ["src/{{sanitized}}/webapp.clj"           (render "src/webapp.clj" data)])))
