package boj.최단거리

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val dp = IntArray(n + 2)
    val time = IntArray(n + 1)
    val price = IntArray(n + 1)
    for (i in 1..n) {
        val (T, P) = readLine().split(" ").map { it.toInt() }
        time[i] = T
        price[i] = P
    }
    for (day in 1..n) {
        // 현재 날짜에서 일이 걸리는 시간
        if (day + time[day] <= n + 1) {
            val nextDay = day + time[day]
            dp[nextDay] = dp[nextDay].coerceAtLeast(dp[day] + price[day])
        }
        dp[day + 1] = dp[day + 1].coerceAtLeast(dp[day])
    }
    println(dp[n + 1])
}