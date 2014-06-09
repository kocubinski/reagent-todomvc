(ns reagent-todomvc.reagent-todomvc
  (:use 
   [ring.middleware reload stacktrace params keyword-params content-type session])
  (:require [cemerick.austin.repls :refer (browser-connected-repl-js)]
            [clue.core :refer (serve-resource defpage)]
            [net.cgrand.moustache :refer (app)]
            [net.cgrand.enlive-html :as enlive]
            ring.adapter.jetty))

(enlive/deftemplate page-view "templates/main.html" []
  [:body] (enlive/append
            (enlive/html [:script (browser-connected-repl-js)])))

(defpage page []
  (page-view))

(def site
  (app

   [[dir #"css|js|img|html|templates"] &]
   (app
    (wrap-content-type)
    [&] serve-resource)

   [&]
   (app
    (wrap-stacktrace)
    (wrap-params)
    (wrap-keyword-params)
    [""] (page))))

(defn run
  []
  (defonce ^:private server
    (ring.adapter.jetty/run-jetty #'site {:port 8002 :join? false}))
  server)

(defn cljs-browser-repl []
  (let [repl-env (reset! cemerick.austin.repls/browser-repl-env
                         (cemerick.austin/repl-env))]
    (cemerick.austin.repls/cljs-repl repl-env)))
