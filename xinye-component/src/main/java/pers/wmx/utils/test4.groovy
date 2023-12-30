def tableName = "red_pack_123";

// 正则匹配
if (tableName ==~ /red_pack_\d+/) {
    println(true);
}

// 前缀匹配
if (tableName.startsWith("red_pack_")) {
    println(true);
}

// contains
if (tableName.contains("red_pack")) {
    println(true);
}

// 判断两个字符串是否相等，可以直接用 ==
def num1 = "123";
def num2 = "123";
if (num1 == num2) {
    println(true);
}

// 转换为int/long
def numStr = "123";
println(numStr.toInteger());
println(numStr.toLong());