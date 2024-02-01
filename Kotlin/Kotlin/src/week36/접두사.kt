package week36

fun main() = with(System.`in`.bufferedReader()) {
    var count = 0
    val n = readLine().toInt()
    val list = List(n) { readLine() }.sortedBy { it.length }.distinct()
    for (i in list.indices) {
        if (!list.filter { it != list[i] }.any { it.startsWith(list[i]) }) count++
    }
    println(count)
}