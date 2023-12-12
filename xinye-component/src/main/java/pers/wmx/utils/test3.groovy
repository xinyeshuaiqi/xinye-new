package pers.wmx.utils

def a = {
    v -> println("${v}")
}

def method(Closure closure, def val) {
    closure(val)
}

method(a, "666")  // 跑脚本打印 666
