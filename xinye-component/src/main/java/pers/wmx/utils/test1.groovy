package pers.wmx.utils

// 核心语法

//定义变量
def x = 2
//打印
println x
/*
不同类型也可以赋值
*/
x = "hello"
println x
msg = "world"
println "hello " + msg //hello world
println 3 / 2    // 1.5
println 3 // 2   // 3
println 3 % 2    // 1
println 3 * 2  // 6
println 3 + 2  // 5
println 3 ** 2  // 9
println  3 == 3  //true
String str = "Hello"
str = 2.3
////////////////////////////////////////////////////////////////////////
//列表
def list = []
a = list.add("Java");
println a //true
println list //[Java]
list << "F#"
println list //[Java, F#]
println list.remove("F#") //true
println list //[Java]
println list - "Java"  // []
list.addAll(["java","C#"])
list.each { println "$it"}
/*
Java
java
C#
*/
list.eachWithIndex { it, i -> println "$i: $it"}
/*
0: Java
1: java
2: C#
*/
ele = list.contains( 'java' )
println ele //true
println 'java' in list  //true
Collections.replaceAll(list,"java","Groovy")
println list //[Java, Groovy, C#]
////////////////////////////////////////////////////////////////////////
//定义map类型
def myMap = [:]
myMap.put("XH",'123')
myMap.put("Name",'Jack')
myMap.each { println "$it.key: $it.value" }
myMap.eachWithIndex { it, i -> println "$i: $it"}
/*
XH: 123
Name: Jack
0: XH=123
1: Name=Jack
*/
println myMap.containsKey('Name') //true
println myMap.containsValue('jack') // false 区分大小写
println myMap.keySet() //[XH, Name]
println myMap.values() //[123, Jack]
//for迭代map
x = ""
for ( e in myMap ) {
    x += e.value
    x += " "
}
println x
////////////////////////////////////////////////////////////////////////
//函数定义
def sayMsg(msg="Groovy"){
    "Hello,$msg"
}
println sayMsg() //Hello,Groovy
println sayMsg('Java') //Hello,Java
////////////////////////////////////////////////////////////////////////
//if分支
def func_if(x = 0){
    if(x< 0) {
        println "<0"
    } else if(x<20) {
        println "[0,20)"
    } else {
        println ">20"
    }
}
println func_if(20) //null
//for循环
def func_for(){
    def x = 0
    for (i in 0 .. 10) {
        x += i
    }
    println x //55
    x = 0
    for( i in [1,2,3,4,5,6,7,8,9,10] ) {
        x += i
    }
    println x // 55
}
func_for()
////////////////////////////////////////////////////////////////////////
//Expando动态Bean类型，支持添加属性和方法
def user = new Expando(name:"Jack")
println user //{name=Jack}
user.xh = "123"
println user //{name=Jack, xh=123}
user.info = {
    println "Name: $name,XH:$xh"
}
user.info() //Name: Jack,XH:123
////////////////////////////////////////////////////////////////////////
//扩展方法 和 Metaprogramming
String.metaClass.xxxFunc = {
    println "xxxFunc"
}
"str"?.xxxFunc() //xxxFunc
////////////////////////////////////////////////////////////////////////
class TestGroovyInterceptable implements GroovyInterceptable {
    def sum(Integer a, Integer b) { a + b }

    def invokeMethod(String name, args) {
        println "Invoke method: $name,args: $args"
    }
}
def test = new TestGroovyInterceptable()
//Invoke method: sum ,args: [2, 3]
test?.sum(2,3)
//Invoke method: power,args: [2, 3]
test?.power(2,3)
////////////////////////////////////////////////////////////////////////
//import groovy.transform.TypeChecked
////类型检测
//@TypeChecked
//Integer testTypeCheck() {
//    //[Static type checking] -
//    //Cannot assign value of type java.lang.String to variable of type java.lang.Integer
//    Integer num = "1"
//    return num
//}
////////////////////////////////////////////////////////////////////////
import groovy.transform.CompileStatic
//静态方法
@CompileStatic
int sum(int x, int y) {
    x + y
}
sum(2,7) //9
////////////////////////////////////////////////////////////////////////
//类
class Student {
    //只读
    final String name = "Jack"
    Integer age = 0
    protected void setAge(Integer age) {
        this.age = age
    }
    def info(){
        "$name,$age,$tag"
    }
    //动态类型
    def tag
}
def stu = new Student()
stu.setAge(33)
stu.tag = [1,2,3]
println stu.info() //Jack,33,[1, 2, 3]
////////////////////////////////////////////////////////////////////////
//JSON处理
import groovy.json.JsonOutput
def strJSON = JsonOutput.toJson([stu])
println(strJSON) //[{"age":33,"tag":[1,2,3],"name":"Jack"}]
////////////////////////////////////////////////////////////////////////
import groovy.json.JsonSlurper
def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText('{ "name": "Jack", "xh" : "123"}')
println(object.name)
println(object.xh)