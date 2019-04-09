# Scala学习笔记
## Scala概览
### Scala语言具有如下特点：
- 一 纯面向对象编程语言
    - 1 封装/信息隐藏 Encapsulation/information hiding.
    - 2 继承 Inheritance.
    - 3 多态/动态绑定 Polymorphism/dynamic binding.
    - 4 所有预定义类型都是对象 All predefined types are objects.
    - 5 通过向对象发送消息来执行所有操作 All operations are performed by sending messages to objects.
    - 6 所有用户定义的类型都是对象 All user-defined types are objects.
    
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
    
### Array、List
#### Array
- 1 定长数组
    - Scala中的Array以Java中的Array方式实现
    - 复杂对象类型在数组定义时被初始化为null，数值型被初始化为0
    - val数组Array后，Array不可变，但其内容可变
    ```scala
    //定义一个长度为10的数值数组
    scala> val numberArray=new Array[Int](10)
    numberArray: Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    //定义一个长度为10的String类型数组
    scala> val strArray=new Array[String](10)
    strArray: Array[String] = Array(null, null, null, null, null, null, null, null,
    null, null)

    //可以看出：复杂对象类型在数组定义时被初始化为null，数值型被初始化为0

    //数组元素赋值
    scala> strArray(0)="First Element"
    //需要注意的是，val strArray=new Array[String](10)
    //这意味着strArray不能被改变，但数组内容是可以改变的
    scala> strArray
    res62: Array[String] = Array(First Element, null, null, null, null, null, null,
    null, null, null)


    //另一种定长数组定义方式
    //这种调用方式其实是调用其apply方法进行数组创建操作
    scala> val strArray2=Array("First","Second")
    strArray2: Array[String] = Array(First, Second)

    Scala中的Array以Java中的Array方式实现
    ```
- 2 变长数组 ArrayBuffer
    - 引入scala.collection.mutable.ArrayBuffer
    - += 与 ++=
    - trimEnd<br>
    string 类型的 ArrayBuffer
    ```scala
    //要使用ArrayBuffer，先要引入scala.collection.mutable.ArrayBuffer
    scala> import scala.collection.mutable.ArrayBuffer
    import scala.collection.mutable.ArrayBuffer

    //创建String类型ArrayBuffer数组缓冲
    scala> val strArrayVar=ArrayBuffer[String]()
    strArrayVar: scala.collection.mutable.ArrayBuffer[String] = ArrayBuffer()

    //+=意思是在尾部添加元素
    scala>     strArrayVar+="Hello"
    res63: strArrayVar.type = ArrayBuffer(Hello)

    //+=后面还可以跟多个元素的集合
    //注意操作后的返回值
    scala> strArrayVar+=("World","Programmer")
    res64: strArrayVar.type = ArrayBuffer(Hello, World, Programmer)

    //显示完整数组内容
    scala> strArrayVar
    res65: scala.collection.mutable.ArrayBuffer[String] = ArrayBuffer(Hello, World,
    Programmer)

    //++=用于向数组中追加内容，++=右侧可以是任何集合
    //追加Array数组
    scala> strArrayVar++=Array("Wllcome","To","XueTuWuYou")
    res66: strArrayVar.type = ArrayBuffer(Hello, World, Programmer, Wllcome, To, Xue
    TuWuYou)
    //追加List
    scala> strArrayVar++=List("Wellcome","To","XueTuWuYou")
    res67: strArrayVar.type = ArrayBuffer(Hello, World, Programmer, Wllcome, To, Xue
    TuWuYou, Wellcome, To, XueTuWuYou)

    //删除末尾n个元素
    scala> strArrayVar.trimEnd(3)

    scala> strArrayVar
    res69: scala.collection.mutable.ArrayBuffer[String] = ArrayBuffer(Hello, World,
    Programmer, Wllcome, To, XueTuWuYou)
    ```
    int 类型的 ArrayBuffer
    ```scala
    //创建整型数组缓冲
    scala> var intArrayVar=ArrayBuffer(1,1,2)
    intArrayVar: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 1, 2)

    //在数组索引为0的位置插入元素6
    scala> intArrayVar.insert(0,6)

    scala> intArrayVar
    res72: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(6, 1, 1, 2)

    //在数组索引为0的位置插入元素7,8,9
    scala> intArrayVar.insert(0,7,8,9)

    scala> intArrayVar
    res74: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(7, 8, 9, 6, 1, 1,2)

    //从索引0开始，删除4个元素
    scala> intArrayVar.remove(0,4)

    scala> intArrayVar
    res77: scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer(1, 1, 2)

    //转成定长数组
    scala> intArrayVar.toArray
    res78: Array[Int] = Array(1, 1, 2)

    //将定长数组转成ArrayBuffer
    scala> res78.toBuffer
    res80: scala.collection.mutable.Buffer[Int] = ArrayBuffer(1, 1, 2)
    ```
    多维数组
    ```scala
    //定义2行3列数组
    scala> var multiDimArr=Array(Array(1,2,3),Array(2,3,4))
    multiDimArr: Array[Array[Int]] = Array(Array(1, 2, 3), Array(2, 3, 4))

    //获取第一行第三列元素
    scala> multiDimArr(0)(2)
    res99: Int = 3

    //多维数组的遍历
    scala> for(i <- multiDimArr) println( i.mkString(","))
    1,2,3
    2,3,4
    ```
#### List
- 1 List一但创建，其值不能被改变 
- 2 List具有递归结构（Recursive Structure),例如链表结构
  
  List基本操作
    ```scala
    //字符串类型List
    scala> val fruit=List("Apple","Banana","Orange")
    fruit: List[String] = List(Apple, Banana, Orange)

    //前一个语句与下面语句等同
    scala> val fruit=List.apply("Apple","Banana","Orange")
    fruit: List[String] = List(Apple, Banana, Orange)

    //数值类型List
    scala> val nums=List(1,2,3,4,5)
    nums: List[Int] = List(1, 2, 3, 4, 5)

    //多重List，List的子元素为List
    scala> val diagMatrix=List(List(1,0,0),List(0,1,0),List(0,0,1))
    diagMatrix: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))

    //遍历List
    scala> for (i <- nums) println("List Element: "+i)
    List Element: 1
    List Element: 2
    List Element: 3
    List Element: 4
    List Element: 5
    
    //判断是否为空
    scala> nums.isEmpty
    res108: Boolean = false

    //取第一个无素
    scala> nums.head
    res109: Int = 1

    //取除第一个元素外剩余的元素，返回的是列表
    scala> nums.tail
    res114: List[Int] = List(2, 3, 4)

    //取列表第二个元素
    scala> nums.tail.head
    res115: Int = 2

    //插入排序算法实现
    def isort(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))

    def insert(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)

    //List连接操作
    scala> List(1,2,3):::List(4,5,6)
    res116: List[Int] = List(1, 2, 3, 4, 5, 6)

    //取除最后一个元素外的元素，返回的是列表
    scala> nums.init
    res117: List[Int] = List(1, 2, 3)

    //取列表最后一个元素
    scala> nums.last
    res118: Int = 4

    //列表元素倒置
    scala> nums.reverse
    res119: List[Int] = List(4, 3, 2, 1)

    //一些好玩的方法调用
    scala> nums.reverse.reverse==nums
    res120: Boolean = true

    scala> nums.reverse.init
    res121: List[Int] = List(4, 3, 2)

    scala> nums.tail.reverse
    res122: List[Int] = List(4, 3, 2)

    //丢弃前n个元素
    scala> nums drop 3
    res123: List[Int] = List(4)

    scala> nums drop 1
    res124: List[Int] = List(2, 3, 4)

    //获取前n个元素
    scala> nums take 1
    res125: List[Int] = List(1)

    scala> nums.take(3)
    res126: List[Int] = List(1, 2, 3)

    //将列表进行分割
    scala> nums.splitAt(2)
    res127: (List[Int], List[Int]) = (List(1, 2),List(3, 4))

    //前一个操作与下列语句等同
    scala> (nums.take(2),nums.drop(2))
    res128: (List[Int], List[Int]) = (List(1, 2),List(3, 4))

    //Zip操作
    scala> val nums=List(1,2,3,4)
    nums: List[Int] = List(1, 2, 3, 4)

    scala> val chars=List('1','2','3','4')
    chars: List[Char] = List(1, 2, 3, 4)

    //返回的是List类型的元组(Tuple）
    scala> nums zip chars
    res130: List[(Int, Char)] = List((1,1), (2,2), (3,3), (4,4))

    //List toString方法
    scala> nums.toString
    res131: String = List(1, 2, 3, 4)

    //List mkString方法
    scala> nums.mkString
    res132: String = 1234

    //转换成数组
    scala> nums.toArray
    res134: Array[Int] = Array(1, 2, 3, 4)
    ```
### 函数与闭包
#### 函数字面量（值函数）
- 函数字面量（function literal），也称值函数（function values），指的是函数可以赋值给变量
  - 符号 => 左侧的表示输入，右侧表示转换操作
    ```scala
    scala> val increase=(x:Int)=>x+1
    increase: Int => Int = <function1>

    scala> println(increase(10))
    11
    ```
- 函数可以进一步简化
```scala

//花括方式（写法3）
scala> Array(1,2,3,4).map{(x:Int)=>x+1}.mkString(",")
res25: String = 2,3,4,5

//省略.的方式（写法4)
scala> Array(1,2,3,4) map{(x:Int)=>x+1} mkString(",")
res26: String = 2,3,4,5


//参数类型推断写法（写法5）
scala> Array(1,2,3,4) map{(x)=>x+1} mkString(",")
res27: String = 2,3,4,5

//函数只有一个参数的话，可以省略()（写法6）
scala> Array(1,2,3,4) map{x=>x+1} mkString(",")
res28: String = 2,3,4,5

//如果参数右边只出现一次，则可以进一步简化（写法7）
scala> Array(1,2,3,4) map{_+1} mkString(",")
res29: String = 2,3,4,5


 //值函数简化方式
 //val fun0=1+_，该定义方式不合法，因为无法进行类型推断
scala> val fun0=1+_
<console>:10: error: missing parameter type for expanded function ((x$1) => 1
x$1))

//值函数简化方式（正确方式）    
scala>  val fun1=1+(_:Double)
un1: Double => Double = <function1>

scala> fun1(999)
es30: Double = 1000.0

//值函数简化方式（正确方式2）  
scala> val fun2:(Double)=>Double=1+_
fun2: Double => Double = <function1>

scala> fun2(200)
res31: Double = 201.0
```
#### 闭包 Closure
- 依照函数字面量在运行时创建的函数值(对象)被称为闭包
- 通过“捕获”自由变量的绑定，从而对函数字面量执行的“关闭”行动
- 封闭项/开放项 closed term/open term
- 在运行时其值才得以确定
```scala
//(x:Int)=>x+more,这里面的more是一个自由变量（Free Variable）,more是一个没有给定含义的不定变量
//而x则的类型确定、值在函数调用的时候被赋值，称这种变量为绑定变量（Bound Variable）
scala> (x:Int)=>x+more
<console>:8: error: not found: value more
              (x:Int)=>x+more
                         ^
scala> var more=1
more: Int = 1

scala>val fun=(x:Int)=>x+more
fun: Int => Int = <function1>

scala> fun(10)
res1: Int = 11

scala> more=10
more: Int = 10

scala> fun(10)
res2: Int = 20

 //像这种运行时确定more类型及值的函数称为闭包,more是个自由变量，在运行时其值和类型得以确定
 //这是一个由开放(free)到封闭的过程，因此称为闭包

scala> val someNumbers = List(-11, -10, -5, 0, 5, 10)
someNumbers: List[Int] = List(-11, -10, -5, 0, 5, 10)

scala>  var sum = 0
sum: Int = 0

scala>  someNumbers.foreach(sum += _)

scala> sum
res8: Int = -11

scala>  someNumbers.foreach(sum += _)

scala> sum
res10: Int = -22

//下列函数也是一种闭包，因为在运行时其值才得以确定
def multiplyBy(factor:Double)=(x:Double)=>factor*x
```

### 类和对象
- 1 类定义、创建对象 
- 2 主构造器 
- 3 辅助构造器
- 4 单例对象
- 5 伴生对象与伴生类
- 6 apply方法
- 7 应用程序对象
- 8 抽象类
#### 类定义、创建对象 
```scala
//采用关键字class定义
class Person {
  //类成员必须初始化，否则会报错
  //这里定义的是一个公有成员
  var name:String=null
}
```
- 虽然我们只在Person类中定义了一个类成员（域）name，类型为String，但Scala会默认帮我们生成name()与name_=（）及构造函数Person()。其中name()对应java中的getter方法，name_=()对应java中的setter方法（由于JVM中不允许出现=，所以用$eq代替。值得注意的是定义的是公有成员，但生成的字节码中却是以私有的方式实现的，生成的getter、setter方法是公有的.因此，**可以直接new操作创建Person对象**
    ```scala
    //默认已经有构建函数，所以可以直接new
    scala> val p=new Person()
    p: Person = Person@84c504

    //直接调用getter和setter方法
    //setter方法
    scala> p.name_=("john")
    //getter方法
    scala> p.name
    res2: String = john

    //直接修改，但其实调用的是p.name_=("jonh")
    scala> p.name="jonh"
    p.name: String = jonh

    //getter方法
    scala> p.name
    res28: String = jonh
    ```
    - 你也可以定义自己的getter和setter方法
    ```scala
    class Person{
      //定义私有成员
      private var privateName:String=null;

      //getter方法
      def name=privateName
      //setter方法
      def name_=(name:String){
        this.privateName=name
      }
    }
    ```
    - 定义成私有成员，其getter、setter方法也是私有的
    - 直接能访问的是我们自己定义的getter、setter方法
        - 通过p.name=“john”这种方式进行赋值,调用者并不需要知道是其通过方法调用还是字段访问来进行操作的, 这便是著名的统一访问原则
    - 如果类的成员域是val类型的变量，则只会生成getter方法
        - val变量对应的是java中的final类型变量，只生成了getter方法
- 如果也需要程序自动会生成getter方法和setter方法，则需要引入 scala.reflect.BeanProperty , 然后采用注解的方式修饰变量
```scala
class Person {
  //类成员必须初始化，否则会报错
  //@BeanProperty用于生成getXxx,setXxx方法
  @BeanProperty var name:String="john"
}
```
#### 类主构造器
- 主构造器的定义与类的定义交织在一直，将构造器参数直接放在类名称之后，如下代码：
```scala
//下列代码不但定义了一个类Person，还定义了主构造器，主构造器的参数为String、Int类型
class Person(val name:String,val age:Int)

//不难看出：上面的代码与下列java语言编写的代码等同
public class Person{
  private final String name;
  private final int age;
  public Person(String name,int age){
       this.name=name;
       this.age=age;
  }
  public String getName(){ return name}
  public int getAge() {return age}
}

//具体使用操作如下：
scala> val p=new Person("john",29)
p: Person = Person@abdc0f

scala> p.name
res31: String = john

scala> p.age
res32: Int = 29
```
### 辅助构造器
- 主构造器之外的构造器被称为辅助构造器
- 1 辅助构建器的名称为this，java中的辅助构造函数与类名相同，这常常会导致修改类名时出现不少问题，scala语言避免了这样的问题
- 2 调用辅助构造函数时，必须先调用主构造函数或其它已经定义好的构造函数
```scala
//禁用主构造函数
class Person private(var name:String,var age:Int){
  //类成员
  private var sex:Int=0

  //辅助构造器
   def this(name:String,age:Int,sex:Int){
    this(name,age)
    this.sex=sex
   }

}
```
### apply方法
- 通过利用apply方法可以直接利用类名创建对象
- 例如前面在讲集合的时候，可以通过val intList=List(1,2,3)这种方式创建初始化一个列表对象，其实它相当于调用val intList=List.apply(1,2,3)，只不过val intList=List(1,2,3)这种创建方式更简洁一点，但我们必须明确的是这种创建方式仍然避免不了new，它后面的实现机制仍然是new的方式，只不过我们自己在使用的时候可以省去new的操作。下面就让我们来自己实现apply方法，代码如下：
```scala
//定义Student类，该类称为伴生类，因为在同一个源文件里面，我们还定义了object Student
class Student(var name:String,var age:Int){
  private var sex:Int=0
  //直接访问伴生对象的私有成员
  def printCompanionObject()=println(Student.studentNo)

}

//伴生对象
object Student {
  private var studentNo:Int=0;
  def uniqueStudentNo()={
    studentNo+=1
    studentNo
  }
  //定义自己的apply方法
  def apply(name:String,age:Int)=new Student(name,age)
  def main(args: Array[String]): Unit = {
    println(Student.uniqueStudentNo())
    val s=new Student("john",29)
    //直接访问伴生类Student中的私有成员
    println(s.sex)

    //直接利用类名进行对象的创建，这种方式实际上是调用前面的apply方法进行实现，这种方式的好处是避免了自己手动new去创建对象
    val s1=Student("john",29)
    println(s1.name)
    println(s1.age)
  }
}
```
#### 抽象类
- 抽象类是一种不能被实例化的类，抽象类中包括了若干不能完整定义的方法，这些方法由子类去扩展定义自己的实现
```scala
abstract class Animal {
  //抽象字段(域）
  //前面我们提到，一般类中定义字段的话必须初始化，而抽象类中则没有这要求
  var height:Int
  //抽象方法
  def eat:Unit
}

//Person继承Animal，对eat方法进行了实现
//通过主构造器对height参数进行了初始化
class Person(var height:Int) extends Animal{
  //对父类中的方法进行实现，注意这里面可以不加override关键字
  def eat()={
    println("eat by mouth")
  }

}

//通过扩展App创建程序的入口
object Person extends App{
  new Person(10).eat()
}
```
