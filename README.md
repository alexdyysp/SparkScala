# SparkScala learning notebook
Spark是MapReduce和Hadoop的扩展模式，用于高效计算，主要特征是内存集群计算，加快应用程序的处理速度

## Spark 概览
第一个Spark程序：WordCount
---------------------------
- IDEA开发环境
- Maven管理jar包：包含Spark-core_2.11和scala-2.11.12
- 使用Maven的package功能将程序打包成jar包

如何提交给Spark本地集群：
```cmd
spark-submit --master local --name WordCountApp --class WordCountScala sparkdemo1-1.0-SNAPSHOT.jar /usr/IDEA/SparkStudy/test.txt
```

Spark模块
------------
1. Spark Core  核心库
2. Spark SQL   SQL
3. Spark Streaming 准实时计算
4. Spark MLlib 机器学习库
5. Spark graph 图计算<br>

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
  val rdd3 = rdd2.map(word=>(word,1));
  val rdd4 = rdd3.reduceByKey(_ + _);
  val list = rdd4.collect();
  list.foreach(println);
```
上述代码的简化版：
```scala
  sc.textFile(path).flatMap(_.split(" ")).map((_1)).reduceByKey(_ + _).collect().foreach(println);
```

## RDD
RDD是Spark基本数据结构，是不可变数据集。计算时先逻辑分区，每个分区独立在集群节点。
### RDDS两种操作
1. transformation转换:在一个已知dataset上创建一个新dataset
2. actions动作:将在dataset上运行得计算结果返回驱动程序
- map 是一个通过让每个数据集元素都执行一个函数，并返回的新 RDD 结果的 transformation；另一方面，reduce 通过执行一些函数，聚合 RDD 中所有元素，并将最终结果给返回驱动程序（虽然也有一个并行reduceByKey 返回一个分布式数据集）的 action。
- Spark 中的所有 transformations 都是 lazy（懒加载的），因此它不会立刻计算出结果。相反，他们只记得应用于一些基本数据集（例如 : 文件）的转换。只有当需要返回结果给驱动程序时，transformations 才开始计算。这种设计使 Spark 的运行更高效。例如，我们可以意识到，map 所创建的数据集将被用在 reduce 中，并且只有 reduce 的计算结果返回给驱动程序，而不是映射一个更大的数据集。
- 默认情况下，每次你在 RDD 运行一个 action 的时， 每个 transformed RDD 都会被重新计算。但是，您也可用 persist (或 cache) 方法将 RDD persist（持久化）到内存中；在这种情况下，Spark 为了下次查询时可以更快地访问，会把数据保存在集群上。此外，还支持持续持久化 RDDs 到磁盘，或复制到多个结点。### RDD变换

