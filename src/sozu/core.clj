(ns sozu.core
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io])
  (:gen-class))

; TODO: dispose of readers & writers
(defn- load-sozu-file [path]
 (let [parsed (with-open [reader (io/reader path)] 
                   (doall (csv/read-csv reader))) ]
 {
     :values parsed
     :display parsed
     :dependencies nil
 }
 )
)

(defn- sheet->csvfile [path sheet]
 (with-open [writer (io/writer path)]
  (csv/write-csv writer (:display sheet))
 )
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
