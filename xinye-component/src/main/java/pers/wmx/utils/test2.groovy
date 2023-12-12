package pers.wmx.utils

def a = {
    print "32"
}

def method(Closure closure) {
    closure()
}

method(a)  // 跑脚本打印 32
