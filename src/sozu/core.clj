(ns sozu.core
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [instaparse.core :as insta])
  (:gen-class))

(def sozu-parser (insta/parser 
                      "<cell> = value | formula
                       value = date | number | string
                       date = optionalWhitespace #'\\d{4}-\\d{2}-\\d{2}' optionalWhitespace
                       number = #'\\d+'
                       formula = <'='> optionalWhitespace number optionalWhitespace
                       <optionalWhitespace> =  <#'\\s*'>
                       string = #'.*'
                       "))

(def cell-parser (comp first sozu-parser))

(defn- evaluate-cell [cell]
 cell
)

(defn- evaluate-sheet [sheet]
 (map #(map evaluate-cell %) sheet)
    )

(defn- calculate-sheet-dependencies [sheet]
 nil)

(defn- read-csv-file [path]
 (with-open [reader (io/reader path)] 
                   (doall (csv/read-csv reader))))

(defn- load-sozu-file [path]
 (let [sheet (map #(map cell-parser %) (read-csv-file path))]
 {
     :values sheet
     :display (evaluate-sheet sheet)
     :dependencies (calculate-sheet-dependencies sheet)
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
