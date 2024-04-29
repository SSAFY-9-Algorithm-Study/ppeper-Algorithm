package week53

private var C = 0
private lateinit var dp: IntArray
private lateinit var list: List<List<Int>>
fun main() = with(System.`in`.bufferedReader()) {
    val (c, n) = readLine().split(" ").map { it.toInt() }
    C = c
    list = List(n) { readLine().split(" ").map { it.toInt() } }
    dp = IntArray(c + 101) { Int.MAX_VALUE }
    search(0)
    println(dp[0])
}

private fun search(customer: Int): Int {
    if (dp[customer] != Int.MAX_VALUE) return dp[customer]
    if (C <= customer) return 0
    for ((cost, count) in list) {
        dp[customer] = dp[customer].coerceAtMost(cost + search(customer + count))
    }
    return dp[customer]
}
