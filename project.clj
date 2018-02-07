(defproject sozu "0.1.0-SNAPSHOT"
  :description "Sozu is a spreadsheet interpreter for the command line."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.csv "0.1.4"]
                 [instaparse "1.4.8"]]
  :main ^:skip-aot sozu.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
