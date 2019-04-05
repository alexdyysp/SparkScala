import org.apache.spark.{SparkConf, SparkContext}

object WordCountScala {
    def main(args: Array[String]): Unit = {
        // creat object Spark configure
        val conf = new SparkConf();
        conf.setAppName("WordCountApp")
        // set Master property
        conf.setMaster("local");
        // creat Object SparkContext
        val sc = new SparkContext(conf);

        //加载文本文件
        //val rdd1 = sc.textFile("/usr/IDEA/SparkStudy/test.txt")
        val rdd1 = sc.textFile(args(0));
        // 压扁
        val rdd2 = rdd1.flatMap(line => line.split(" "));
        // 映射
        val rdd3 = rdd2.map((_,1));
        // 聚合
        val rdd4 = rdd3.reduceByKey(_ + _);
        val r = rdd4.collect();
        r.foreach(println);
    }
}