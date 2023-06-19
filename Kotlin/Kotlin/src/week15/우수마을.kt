package week15

import java.util.*
import kotlin.math.max

private lateinit var cost: IntArray
private lateinit var tree: Array<LinkedList<Int>>
private lateinit var visited: BooleanArray
private lateinit var dp: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    visited = BooleanArray(n + 1)
    dp = Array(2) { IntArray(n + 1) }
    tree = Array(n + 1) { LinkedList() }
    cost = readLine().split(" ").map { it.toInt() }.toIntArray()
    repeat(n - 1) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        tree[a].add(b)
        tree[b].add(a)
    }
    dfs(1)
    println(max(dp[0][1], dp[1][1]))
}

private fun dfs(start: Int) {
    visited[start] = true
    // 기본 우수마을일때 비용
    dp[1][start] = cost[start - 1]
    for (child in tree[start]) {
        if (visited[child]) continue
        dfs(child)
        dp[0][start] += max(dp[0][child], dp[1][child])
        dp[1][start] += dp[0][child]
    }
}
