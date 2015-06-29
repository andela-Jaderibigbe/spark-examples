package com.tutor.spark.examples
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import java.io.BufferedWriter
import java.io.FileWriter
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.conf.Configuration
import java.io.OutputStreamWriter
import org.apache.hadoop.fs.Path
import java.util.Random

/**
 * Hello world!
 *
 */
object App {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[*]").setAppName("SparkBasics")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile("hdfs://localhost:8020/data/README.md")
    println(textFile.name + " file contains " + textFile.count() + " lines")
    println(textFile.name + " has fist line has: " + textFile.first())
    val linesWithSpark = textFile.filter(line => line.contains("Spark"))
    println(textFile.name + " has spark words count: " + textFile.filter(line => line.contains("Spark")).count())
    sc.stop()
  } 
}