# Scala学习笔记
## Scala概览
### Scala语言具有如下特点：
- 一 纯面向对象编程语言
    - 1 Encapsulation/information hiding.
    - 2 Inheritance.
    - 3 Polymorphism/dynamic binding.
    - 4 All predefined types are objects.
    - 5 All operations are performed by sending messages to objects.
    - 6 All user-defined types are objects.

- 二 函数式编程语言
    - 定义：Functional programming is a programming paradigm that treats computation as the evaluation of mathematical functions and avoids state and mutable data.

- 三 函数式编程语言应支持以下特性：
    - 1 高阶函数（Higher-order functions）
    - 2 闭包（ closures）
    - 3 模式匹配（ Pattern matching）
    - 4 单一赋值（ Single assignment ）
    - 5 延迟计算（ Lazy evaluation）
    - 6 类型推导（ Type inference ）
    - 7 尾部调用优化（ Tail call optimization）
    - 8 类型推导（ Type inference ）

- 四 Scala语言具有很强的兼容性、移植性
    - Scala运行于JVM上，能够与JAVA进行互操作，具有与JAVA一样的平台移植性

- 五 Scala语法的简洁
    - 下面给的是java的hadoop wordcount代码及spark wordcount代码 ：
    ![Image of WordCount](https://github.com/dyywinner/SparkScala/blob/master/IMAGE/WordCount.png)
    - 可以看到Scala用了短短3行就干了Java无数行干的事情
   
### 我为什么要学习Spark？
- Spark是当前最流行的开源大数据内存计算框架，采用Scala语言实现
- Scala是未来大数据处理的主流语言, Scala具有数据处理的天然优势（语言特点决定）
- Scala拥有更高效的Akka并发框架和Actor模型
- the most inportant is: **Scala背后的函数式编程思想将带来无穷的思想启迪**

## Scala初入门
### var and val
- val 是常量-你无法改变其内容
- var 是变量-如果你真的需要改变其内容
- greeting 指定类型声明
- Scala与Java/C++不同的是，大多是程序不需要这么多var变量
- **Note: 不需要给出值或者变量类型，编译器会在初始化它的表达式时自动推断出类型！！！**
```scala
// val
val answer = 8*5+2  // Int = 42
0.5*answer          // Double = 21.0
// var
var counter = 0
counter = 1         // change the variable 
// multiply 
val xmax, ymax = 100
// greeting
var greeting, messege: String = null    //字符串类型被初始化为null
```
### 函数function
Scala用以下方法进行函数定义：
![Image of WordCount](https://img-blog.csdn.net/20150818092829083)<br>
接下来就让我们愉快地写个HelloWord(至少要精通各种编程语言的Helloworld写法吧！)：
```Scala
//scala应用程序同样采用main方法作为应用程序的入口
object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("Hello World")
  }
}
```

- 1 必须给出所有参数类型，但除了递归函数，返回值不一定需要
    - 递归函数没有返回类型，Scala编译器无法校验结果类型
- 2 默认参数和带名参数
    - 调用函数时可以不显示的给出所有参数，则使用默认参数
```Scala
def decorate(str: String, left: String = "[", right: String = "]") = 
    left + str + right
decorate("Hello", "<<<", ">>>")     // "<<<Hello>>>"
decorate(left = "<<<", str = "Hello", right = ">>>")    // "<<<Hello>>>"
decorate("Hello", right = "]<<<")   // 将调用decorate("Hello","[","]<<<")
```
- 3 变长参数
    - 实现一个可接受变长度参数列表的函数
    ```Scala
    def sum(args: Int*) = {
        var result = 0
        for(arg <- args) result += arg
        result
    }
    
    val s = sum(1, 4, 9)
    val t = sum(1 to 5)     //错误，不能是一个整数区间
    val t = sum(1 to 5: _*) //将1 to 5当参数序列处理
    ```
- 4 懒值lazy
    - 当val被声明懒值lazy时，初始化被推迟，直到首次对他取值
    - 可以把lazy当做处于val和def的中间状态
    - 懒值不是没有额外开销，而是每次访问懒值，都会有一个方法被调用，这个方法会以线程安全的检查方式检查该值是否被已初始化
    ```Scala
    lazy val helloString="Hello Crazy World"    // 还没初始化
    helloString     // 被调用时才出现
    ```
- 5 异常
    - 工作机制与Java/C++一样，当抛出异常时，当前运算终止
    - **Scala没有受检异常**
    ```Scala
    if (x >= 0) { sqrt(x)
    } else throw new IllegalArgumentException("x should not be negative")
    ```
    - try/catch与try/finally互补
    ```Scala
    try{...}catch{...}finally{...}
    ```
