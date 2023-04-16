package week8

private const val MOD = 1000000007
private lateinit var dp: Array<Array<LongArray>>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, l, r) = readLine().split(" ").map { it.toInt() }
    dp = Array(n + 1) { Array(n + 1) { LongArray(n + 1) } }
    // 빌딩 하나만 두었을때
    dp[1][1][1] = 1
    for (count in 2..n) {
        for (left in 1..n) {
            for (right in 1..n) {
                dp[count][left][right] =
                    (dp[count - 1][left - 1][right] +
                            dp[count - 1][left][right - 1] +
                            dp[count - 1][left][right] * (count - 2)) % MOD
            }
        }
    }
    println(dp[n][l][r])
}
