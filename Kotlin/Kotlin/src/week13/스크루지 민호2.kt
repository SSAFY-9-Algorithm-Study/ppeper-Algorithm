package week13

import java.lang.Integer.min
import java.util.*

private lateinit var tree: Array<LinkedList<Int>>
private lateinit var visited: BooleanArray
private lateinit var dp: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    tree = Array(n + 1) { LinkedList() }
    visited = BooleanArray(n + 1)
    dp = Array(2) { IntArray(n + 1) }
    repeat(n - 1) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        tree[a].add(b)
        tree[b].add(a)
    }
    dfs(1)
    println(min(dp[0][1], dp[1][1]))
}

private fun dfs(start: Int) {
    visited[start] = true
    dp[1][start] = 1
    for (child in tree[start]) {
        if (visited[child]) continue
        dfs(child)
        // 리프노드 도착
        dp[0][start] += dp[1][child]
        dp[1][start] += min(dp[0][child], dp[1][child])
    }
}
