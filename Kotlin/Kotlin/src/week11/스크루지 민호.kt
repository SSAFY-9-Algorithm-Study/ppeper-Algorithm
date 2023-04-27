package week11

import java.util.*

private lateinit var graph: Array<LinkedList<Int>>
private lateinit var visited: BooleanArray
private var answer = Integer.MIN_VALUE
private var max = Integer.MIN_VALUE
private var leaf = 0
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    graph = Array(n + 1) { LinkedList() }
    visited = BooleanArray(n + 1)
    repeat(n - 1) {
        val st = StringTokenizer(readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        graph[x].add(y)
        graph[y].add(x)
    }
    visited[1] = true
    dfs(1, 0)
    max = Integer.MIN_VALUE
    visited.fill(false)
    // 가장멀리있는 지점에서의 dfs
    dfs(leaf, 0)
    println((answer + 1) / 2)
}

private fun dfs(start: Int, dist: Int): Int {
    for (nextNode in graph[start]) {
        if (!visited[nextNode]) {
            visited[nextNode] = true
            dfs(nextNode, dist + 1)
        }
    }
    // 더 멀리있는 노드
    if (max < dist) {
        max = dist
        leaf = start
        answer = answer.coerceAtLeast(max)
    }
    return start
}
