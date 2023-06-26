package week16

import java.util.*

private lateinit var graph: Array<LinkedList<Int>>
private lateinit var visited: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    repeat(readLine().toInt()) {
        val (v, e) = readLine().split(" ").map { it.toInt() }
        graph = Array(v + 1) { LinkedList() }
        // 2개로 나뉨 -> 1,2로 두개를 표현
        visited = IntArray(v + 1)
        repeat(e) {
            val (a, b) = readLine().split(" ").map { it.toInt() }
            graph[a].add(b)
            graph[b].add(a)
        }
        // dfs로 판단
        (1..v).forEach {
            if (visited[it] == 0) {
                dfs(it)
            }
        }
        if (isBinaryGraph(v)) sb.append("YES\n") else sb.append("NO\n")
    }
    println(sb)
}

private fun isBinaryGraph(v: Int): Boolean {
    for (node in 1..v) {
        for (adjNode in graph[node]) {
            if (visited[node] == visited[adjNode]) {
                return false
            }
        }
    }
    return true
}

private fun dfs(node: Int) {
    for (adjNode in graph[node]) {
        if (visited[adjNode] == 0) {
            visited[adjNode] = if (visited[node] == 1) 2 else 1
            dfs(adjNode)
        }
    }
}