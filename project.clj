(defproject reagent-todomvc "0.1.0-SNAPSHOT"
  :description "Taking reagent for a test drive."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/clj" "src/cljs"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [enlive "1.1.5"]
                 [clue "0.1.2"]
                 [ring "1.2.2"]
                 [net.cgrand/moustache "1.2.0-alpha2"]

                 [org.clojure/clojurescript "0.0-2138"]
                 [reagent "0.4.2"]]

  :profiles {:dev {:repl-options {:init-ns reagent-todomvc.reagent-todomvc}
                   :plugins [[com.cemerick/austin "0.1.3"]
                             [lein-cljsbuild "1.0.1"]
                             [cider/cider-nrepl "0.7.0-SNAPSHOT"]]
                   :cljsbuild {:builds [{:source-paths ["src/cljs"]
                                         :compiler {:output-to "resources/js/app.js"
                                                    :optimizations :simple
                                                    :pretty-print true}}]}}})
