package week17

import kotlin.math.max

private lateinit var dp: Array<Array<IntArray>>
private lateinit var list: ArrayList<List<Int>>
fun main() = with(System.`in`.bufferedReader()) {
    list = ArrayList()
    while (true) {
        val input = readLine()
        if (input.isNullOrBlank()) break
        list.add(input.trim().split(" ").map { it.toInt() })
    }
    // dp[i][w][b]: i번째 선수를 선택할때 w와 b 일때의 최대 닶
    dp = Array(list.size + 1) { Array(16) { IntArray(16) } }
    for (i in 1..list.size) {
        for (w in 0..15) {
            for (b in 0..15) {
                // 1. 선택을 할때, 2. 백으로 선택, 3. 흑으로 선택
                val selectedWhite = if (0 < w) dp[i - 1][w - 1][b] + list[i - 1][0] else 0
                val selectedBlack = if (0 < b) dp[i - 1][w][b - 1] + list[i - 1][1] else 0
                dp[i][w][b] = max(dp[i - 1][w][b], max(selectedWhite, selectedBlack))
            }
        }
    }
    println(dp[list.size][15][15])
}