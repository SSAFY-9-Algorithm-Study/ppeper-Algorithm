package week53

import kotlin.math.max

data class Data(
    val day: Int = 0,
    val cost: Int = 0
)
private lateinit var dp: IntArray
private lateinit var list: Array<Data>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    dp = IntArray(n + 1)
    list = Array(n) { Data() }
    repeat(n) {
        val (day, cost) = readLine().split(" ").map { it.toInt() }
        list[it] = Data(day, cost)
    }
    for (i in 0 until n) {
        if (1 <= i) {
            dp[i] = max(dp[i], dp[i - 1])
        }
        val day = i + list[i].day
        if (day in 1..n) {
            dp[day] = max(dp[day], dp[i] + list[i].cost)
        }

    }
    println(max(dp[n], dp[n - 1]))
}