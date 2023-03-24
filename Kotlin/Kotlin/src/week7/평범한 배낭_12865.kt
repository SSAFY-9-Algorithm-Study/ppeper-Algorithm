package boj.최단거리

private lateinit var dp: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    dp = Array(n + 1) { IntArray(k + 1) }
    for (i in 1..n) {
        val (w, v) = readLine().split(" ").map { it.toInt() }
        for (weight in 1..k) {
            // 가방에 넣지 못함 -> 전까지 넣었던 무게
            if (weight < w) {
                dp[i][weight] = dp[i - 1][weight]
            } else {
                dp[i][weight] = dp[i - 1][weight].coerceAtLeast(dp[i - 1][weight - w] + v)
            }
        }
    }
    println(dp[n][k])
}