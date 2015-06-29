package com.ecommerce.spark.ecommerce
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


object SimpleApp {
  case class Ecommerce(department: String, brand: String, category: String, subCategory: String, collection: String, colour: String, colourImageUrl: String, description: String, manufacturer: String, partNo: String, material: String, modelNo: String, msrp: String, currency: String, offers: String, dimensionLength: String, dimensionWidth: String, dimensionHeight: String, dimensionWeight: String, title: String, madeIn: String, salePrice: String)
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[*]").setAppName("SparkBasics")
    val sc = new SparkContext(conf)
    //Load File from HDFS
    val ecsvPath = "hdfs://localhost:8020/data/ecommerce_1.csv"
    
    //Convert file to RDD
    val ecsvRdd = sc.textFile(ecsvPath, 2).cache()
    
    //Extract the fields to class object
    val cleanData = ecsvRdd.map(line => line.split("\t")).map(ob => Ecommerce(ob(4).toString(), ob(5).toString(), ob(7).toString(), ob(8).toString(), ob(9).toString(), ob(10).toString(), ob(11).toString(), ob(12).toString(), ob(13).toString(), ob(14).toString(), ob(15).toString(), ob(16).toString(), ob(17).toString(), ob(18).toString(), ob(19).toString(), ob(20).toString(), ob(21).toString(), ob(22).toString(), ob(23).toString(), ob(24).toString(), ob(25).toString(),ob(26).toString()))
    
    //Print Results
    println("" + cleanData.filter(r => r.salePrice.toInt > 15).take(1).mkString("[", ",", "]"))
    println("" + cleanData.map(p => ((p.msrp.toDouble - p.salePrice.toDouble) / 100) * p.salePrice.toDouble).take(2))
    println("" + cleanData.filter(l => !(l.category.isEmpty())).take(3).mkString("[", ",", " ]"))
  }
}