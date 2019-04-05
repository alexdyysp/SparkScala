# SparkScala
learning notebook

## 第一个Spark程序：WordCount
- IDEA开发环境
- Maven管理jar包：包含Spark-core_2.11和scala-2.11.12
- 使用Maven的package功能将程序打包成jar包

如何提交给Spark本地集群：
```cmd
spark-submit --master local --name WordCountApp --class WordCountScala sparkdemo1-1.0-SNAPSHOT.jar /usr/IDEA/SparkStudy/test.txt
```

## Spark组件
Spark模块
------------
- Spark Core  核心库
- Spark SQL   SQL
- Spark Streaming 准实时计算
- Spark MLlib 机器学习库
- Spark graph 图计算<br>

Spark集群运行
------------
- local   本地模式
- standalone 独立模式
- yarn    yarn模式
- mesos   mesos

start-all.sh
-------------
- start-master.sh   //rpc端口7077
- start-slave.sh    //s201:7077

webui
------
- http://localhost:8080

SparkContext
-------------
- spark集群的主要入口点
```scala
  SparkConf = new ();
  conf.setApp(AppName);     
  conf.setMaster(RunModel); 
  sc = new SparkContext(conf);
  RDD<String> rdd1 = sc.textFile(path);
  val rdd2 = rdd1.flatMap(line => line.split(" "));
  val rdd3 = rdd2.map();
  val rdd4 = rdd3.reduceByKey(_ + _);
  val list = rdd4.collect();
  list.foreach(println);
```
上述代码的简化版：
```scala
  sc.textFile(path).flatMap(_.split(" ")).map((_1)).reduceByKey(_ + _).collect().foreach(println);
```
