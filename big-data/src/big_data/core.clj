(ns big-data.core
  (:require [flambo.conf :as conf]
            [flambo.api :as f]
            [flambo.tuple :as ft]
            [flambo.sql :as sql]
            [clj-time.core :as t]
            [clojure.string :as s])
  (:import [org.apache.spark.sql Row RowFactory])
  (:gen-class))

(def c (-> (conf/spark-conf)
           (conf/master "local")
           (conf/app-name "Testing big-data")))

(def sc (f/spark-context c))

(defn line-count [lines]
  (->> lines
        f/count))

(defn wc [rdd]
  (->> rdd
       (f/flat-map (f/fn [l] (s/split l #" ")))
       (f/map-to-pair (f/fn [w] (ft/tuple w 1)))
       (f/reduce-by-key (f/fn [x y] (+ x y)))
       f/sort-by-key
       f/collect
       clojure.pprint/pprint))


(defn process [atn]
    (let [result (f/text-file sc "data.txt")]
      (atn result)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (clojure.pprint/pprint (process wc))
;;   (clojure.pprint/pprint (process line-count))
  (println "Hello, World!"))
