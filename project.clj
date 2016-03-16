(defproject drag "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228" :scope "provided"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [cljs-http "0.1.39"]
                 [compojure "1.5.0"]
                 [liberator "0.14.0"]
                 [fogus/ring-edn "0.3.0"]
                 [reagent "0.5.1"]]

  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.1.2"]]

  :source-paths ["src"]

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src-cljs"]
                        :compiler {:main drag.main
                                   :asset-path "out"
                                   :output-to "resources/public/drag.js"
                                   :output-dir "resources/public/out"
                                   :optimizations :none
                                   :source-map true}}
                       {:id "prod"
                        :source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/drag.js"
                                   :optimizations :advanced}}]}

  :ring {:handler drag.api/handler
         :nrepl {:start? true :port 4500}
         :port 8090}

  :global-vars {*print-length* 20})
