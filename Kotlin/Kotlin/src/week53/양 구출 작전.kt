package week53

import java.util.*

data class Node(
    val idx: Int,
    val type: String = "",
    val count: Long = 0,
)
private lateinit var tree: Array<LinkedList<Node>>
private lateinit var visited: BooleanArray
private lateinit var escape: LongArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    tree = Array(n + 1) { LinkedList() }
    visited = BooleanArray(n + 1)
    escape = LongArray(n + 1)
    for (i in 2..n) {
        val (type, count, bridge) = readLine().split(" ")
        tree[i].add(Node(bridge.toInt(), type, count.toLong()))
        tree[bridge.toInt()].add(Node(i, type, count.toLong()))
    }
    dfs(Node(1))
    println(escape[1])
}

private fun dfs(node: Node): Long {
    visited[node.idx] = true
    for (next in tree[node.idx]) {
        if (visited[next.idx]) continue
        escape[node.idx] += dfs(next)
    }
    if (node.type == "S") escape[node.idx] += node.count else escape[node.idx] -= node.count
    return if (0 < escape[node.idx]) escape[node.idx] else 0
}
