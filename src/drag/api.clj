(ns drag.api
    (:require
     [compojure.core :refer (defroutes ANY GET)]
     [compojure.route :refer (resources not-found)]
     [ring.middleware.params :refer (wrap-params)]
     [ring.middleware.edn :refer (wrap-edn-params)]
     [ring.util.response :refer (redirect)]))

(defroutes routes
  (ANY "/"
       []
       (redirect "/index.html"))

  (resources "/" {:root "public"})
  (resources "/" {:root "/META-INF/resources"})
  (not-found "404"))

(def handler
  (-> routes
      wrap-params
      wrap-edn-params))
