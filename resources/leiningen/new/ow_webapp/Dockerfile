FROM clojure:openjdk-8-lein-2.9.1-alpine as run
EXPOSE 8080
RUN adduser -D appuser
USER appuser
COPY --chown=appuser project.clj /home/appuser/
WORKDIR /home/appuser
RUN lein -U deps
COPY --chown=appuser . /home/appuser
RUN lein uberjar && mv -f target/uberjar/heroku-addon-test-standalone.jar ./ && rm -rf target
CMD java -jar heroku-addon-test-standalone.jar
