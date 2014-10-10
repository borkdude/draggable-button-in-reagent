(defproject drag "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2342"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [ring/ring-core "1.3.1"]
                 [ring/ring-jetty-adapter "1.3.1"]
                 [org.webjars/react "0.11.2"]
                 [org.webjars/bootstrap "3.2.0"]
                 [cljs-http "0.1.16"]
                 [compojure "1.1.9"]
                 [liberator "0.12.2"]
                 [fogus/ring-edn "0.2.0"]
                 [reagent "0.4.2"]]

  :plugins [[lein-ring "0.8.11"]
            [lein-cljsbuild "1.0.4-SNAPSHOT"]]

  :source-paths ["src"]

  :cljsbuild {
              :builds [{:id "drag"
                        :source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/drag.js"
                                   :output-dir "resources/public/out"
                                   :optimizations :none
                                   :source-map true}}]}

  :ring {:handler drag.api/handler
         :nrepl {:start? true :port 4500}
         :port 8090}
  :global-vars {*print-length* 20})
