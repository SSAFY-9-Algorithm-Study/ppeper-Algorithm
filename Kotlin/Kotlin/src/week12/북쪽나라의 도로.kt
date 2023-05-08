package week12

import java.util.*

data class Node(val to: Int, val dist: Int)
private lateinit var graph: Array<LinkedList<Node>>
private lateinit var visited: BooleanArray
private var max = Integer.MIN_VALUE
private var leaf = 0
fun main() = with(System.`in`.bufferedReader()) {
    graph = Array(10001) { LinkedList() }
    visited = BooleanArray(10001)
    while (true) {
        val input = readLine()
        if (input == null || input.isEmpty()) break
        val (from, to, dist) = input.split(" ").map { it.toInt() }
        graph[from].add(Node(to, dist))
        graph[to].add(Node(from, dist))
    }
    visited[1] = true
    dfs(1, 0)
    visited.fill(false)
    max = Integer.MIN_VALUE
    dfs(leaf, 0)
    println(max)
}

private fun dfs(start: Int, distance: Int) {
    for (nextNode in graph[start]) {
        if (!visited[nextNode.to]) {
            visited[nextNode.to] = true
            val distSum = distance + nextNode.dist
            dfs(nextNode.to, distSum)
        }
    }
    // 더 멀리있는 노드
    if (max < distance) {
        max = distance
        leaf = start
    }
}
