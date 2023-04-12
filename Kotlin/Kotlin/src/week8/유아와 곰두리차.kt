package week8

import java.util.*

private const val MOD = 1000000007L
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n + 1) { LinkedList<Int>() }
    val dp = Array(7) { LongArray(n + 1) }
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
        dp[0][a]++
        dp[0][b]++
    }
    for (i in 1 until 7) {
        for (j in 1..n) {
            // 갈 수 있는 경로
            for (adj in graph[j]) {
                dp[i][adj] += dp[i - 1][j]
                dp[i][adj] %= MOD
            }
        }
    }
    println(dp[6].sum() % MOD)
}