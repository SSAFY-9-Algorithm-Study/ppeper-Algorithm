package week53

private lateinit var cost: Array<List<Int>>
private lateinit var dp: Array<Array<IntArray>>
private var n = 0
private var answer = Int.MAX_VALUE

fun main() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    dp = Array(n) { Array(3) { IntArray(3) { Int.MAX_VALUE } } }
    cost = Array(n) { readLine().split(" ").map { it.toInt() } }
    for (i in 0 until 3) {
        answer = answer.coerceAtMost(rgb(0, i, i))
    }
    println(answer)
}

private fun rgb(idx: Int, color: Int, firstColor: Int): Int {
    if (idx == n) return 0
    if (dp[idx][color][firstColor] != Int.MAX_VALUE) return dp[idx][color][firstColor]
    for (i in 0 until 3) {
        if (i == color) continue
        if (idx == n - 2 && i == firstColor) continue
        dp[idx][color][firstColor] = dp[idx][color][firstColor].coerceAtMost(rgb(idx + 1, i, firstColor) + cost[idx][color])
    }
    return dp[idx][color][firstColor]
}
