(defproject big-data "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-http "1.1.2"]
                 [yieldbot/flambo "0.6.0"]
                 [org.apache.spark/spark-core_2.10 "1.3.1"]
                 [org.apache.spark/spark-streaming_2.10 "1.3.1"]
                 [org.apache.spark/spark-streaming-twitter_2.10 "1.3.1"]
                 [org.apache.spark/spark-streaming-kafka_2.10 "1.3.1"]
                 [org.apache.spark/spark-sql_2.10 "1.3.1"]
                 [com.databricks/spark-csv_2.10 "1.0.1"]
                 [clj-time "0.8.0"]
                 [org.clojure/tools.trace "0.7.8"]
                 [clj-glob "1.0.0"]
                 [incanter "1.5.6"]]
  :main ^:skip-aot big-data.core
  :target-path "target/%s"
  :profiles {:dev {
     :dependencies [[midje "1.6.3"]]
     :aot [flambo.function flambo.api flambo.conf flambo.tuple flambo.sql]
     :uberjar{:aot :all} }})


