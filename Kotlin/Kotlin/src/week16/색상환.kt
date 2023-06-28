package week16

const val MOD = 1_000_000_003
private lateinit var dp: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val k = readLine().toInt()
    dp = Array(n + 1) { IntArray(k + 1) }
    for (i in 0 until n) {
        dp[i][1] = i
        dp[i][0] = 1
    }
    for (i in 2..n) {
        for (j in 2..k) {
            dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD
        }
    }
    println((dp[n - 1][k] + dp[n - 3][k - 1]) % MOD)
}