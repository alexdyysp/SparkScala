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
var greeting, messege: String = null
```
