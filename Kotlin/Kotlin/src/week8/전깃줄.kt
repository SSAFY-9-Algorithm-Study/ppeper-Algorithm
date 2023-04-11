package week8

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val line = Array(n) { readLine().split(" ").map { it.toInt() } }
    val dp = IntArray(n)
    // 전깃줄 오름차순 정렬
    line.sortBy { it[0] }
    // 1개는 연결 100프로 가능
    dp[0] = 1
    for (i in 1 until line.size) {
        // 현재 전깃줄을 연결했을때
        dp[i] = 1
        for (j in 0 until i) {
            // 연결 가능
            if (line[j][1] < line[i][1]) {
                dp[i] = dp[i].coerceAtLeast(dp[j] + 1)
            }
        }
    }
    val max = dp.maxOrNull()
    println(n - max!!)
}