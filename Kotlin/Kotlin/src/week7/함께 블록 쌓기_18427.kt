package boj.최단거리

fun main() = with(System.`in`.bufferedReader()) {
    val (n,m,h) = readLine().split(" ").map { it.toInt() }
    val student = Array(n) {
        val input = readLine().split(" ").map { it.toInt() }
        IntArray(input.size + 1).apply {
            for (i in 1 until this.size) {
                this[i] = input[i - 1]
            }
        }
    }
    val dp = Array(n) { IntArray(1001) }
    for (i in student[0].indices) {
        dp[0][student[0][i]]++
    }
    for (i in 1 until n) {
        for (j in student[i].indices) {
            for (k in 0..h) {
                if (k - student[i][j] >= 0) {
                    dp[i][k] = (dp[i][k] + dp[i - 1][k - student[i][j]]) % 10007
                }
            }
        }
    }
    println(dp[n - 1][h])
}