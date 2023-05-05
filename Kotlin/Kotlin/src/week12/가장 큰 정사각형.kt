package week12

private var length = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val board = Array(n) { CharArray(m) }
    repeat(n) {
        board[it] = readLine().toCharArray()
    }
    val dp = Array(n) { IntArray(m) }
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (board[i][j] == '1') {
                dp[i][j] = 1
                length = 1
            }
        }
    }
    for (i in 1 until n) {
        for (j in 1 until m) {
            if (board[i - 1][j] != '0' && board[i][j - 1] != '0'
                && board[i - 1][j - 1] != '0' && board[i][j] != '0') {
                dp[i][j] = dp[i - 1][j].coerceAtMost(dp[i][j - 1]).coerceAtMost(dp[i - 1][j - 1]) + 1
                length = length.coerceAtLeast(dp[i][j])
            }
        }
    }
    println(length * length)
}