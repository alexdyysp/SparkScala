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
