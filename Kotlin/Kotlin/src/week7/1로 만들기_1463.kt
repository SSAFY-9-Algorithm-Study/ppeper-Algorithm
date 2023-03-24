package boj.최단거리

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val dp = IntArray(n + 3)
    dp[1] = 0
    dp[2] = 1
    dp[3] = 1
    for (i in 4..n) {
        dp[i] = dp[i - 1] + 1
        if (i % 3 == 0) {
            dp[i] = dp[i].coerceAtMost(dp[i / 3] + 1)
        }
        if (i % 2 == 0) {
            dp[i] = dp[i].coerceAtMost(dp[i / 2] + 1)
        }
    }
    println(dp[n])
}