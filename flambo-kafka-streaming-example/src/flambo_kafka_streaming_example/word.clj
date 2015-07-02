(ns flambo-kafka-streaming-example.word
  (:require [flambo.conf :as conf]
            [flambo.api :as f])
  (:require [clojure.string :as s])
  (:gen-class))

(def master "local[2]")
(def app-name "wordCount")
(def conf {})
(def env {
           "spark.executor.memory" "1G",
           "spark.files.overwrite" "true"})

(defn -main
  "This is where the magic happens."
  [& args]
  (let [c (-> (conf/spark-conf)
              (conf/master master)
              (conf/app-name "wordadapters")
              (conf/set-executor-env env)) 
	sc (f/spark-context c)]



    (-> (f/text-file sc "resources/data.txt")
       ;; (f/map (memfn _2))
        (f/flat-map (f/fn [l] (s/split l #" ")))
        (f/map (f/fn [w] [w 1])) 
        (f/reduce (f/fn [x y] (+ x y)))                                        
        (f/take 10) 
        )))
    

