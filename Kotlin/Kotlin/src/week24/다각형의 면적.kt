package week24

import kotlin.math.abs

private lateinit var list: Array<Pair<Long, Long>>
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    list = Array(n + 1) { Pair(0L, 0L) }
    repeat(n) {
        val (x, y) = readLine().split(" ").map { it.toLong() }
        list[it] = Pair(x, y)
    }
    list[n] = list[0]
    // 신발끈 공식
    println(String.format("%.1f", calculate(n)))
}

private fun calculate(n: Int): Double {
    val DIVIDE = 2.0
    var front = 0L
    var back = 0L
    for (i in 0 until n) {
        front += list[i].first * list[i + 1].second
        back += list[i + 1].first * list[i].second
    }
    return abs(front - back) / DIVIDE
}
