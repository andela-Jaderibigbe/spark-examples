package com.ecommerce.spark.ecommerce
import org.apache.spark.{ SparkConf, SparkContext }
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.StringType
import org.apache.spark.sql.SQLContext

object ecomSql {
  case class Ecommerce(department: String, brand: String, category: String, subCategory: String, collection: String, colour: String, colourImageUrl: String, description: String, manufacturer: String, partNo: String, material: String, modelNo: String, msrp: String, currency: String, offers: String, dimensionLength: String, dimensionWidth: String, dimensionHeight: String, dimensionWeight: String, title: String, madeIn: String, salePrice: String)
  def main(args: Array[String]){
    val sparkConf = new SparkConf().setAppName("Ecommerce SQL")
    val sc = new SparkContext(sparkConf)
    
    //Initialize the Spark SQL context
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    import sqlContext.implicits._ 
    
    //Create an RDD
    val textfile = sc.textFile("/mnt/data/ecommerce.csv", 4) 
    
    //To use normal spark SQL
    val eRdd = textfile.map(l => l.split("\t")).map(ob => Ecommerce(ob(4).toString(), ob(5).toString(), ob(7).toString(), ob(8).toString(), ob(9).toString(), ob(10).toString(), ob(11).toString(), ob(12).toString(), ob(13).toString(), ob(14).toString(), ob(15).toString(), ob(16).toString(), ob(17).toString(), ob(18).toString(), ob(19).toString(), ob(20).toString(), ob(21).toString(), ob(22).toString(), ob(23).toString(), ob(24).toString(), ob(25).toString(),ob(26).toString()))
    val eDataFrame  = eRdd.toDF()
    
    eDataFrame.registerTempTable("ecommerce")
    
    val sqlValue = sqlContext.sql("select * from ecommerce limit 10")

    sqlValue.collect().foreach(println)
  }
}