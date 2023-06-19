package week15

import java.util.*

data class Node (
    val to: Int,
    val cost: Int
)

private lateinit var graph: Array<LinkedList<Node>>
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, t) = readLine().split(" ").map { it.toInt() }
    graph = Array( n + 1) { LinkedList() }
    visited = BooleanArray(n + 1)
    repeat(m) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        graph[from].add(Node(to, cost))
        graph[to].add(Node(from, cost))
    }
    val pq = PriorityQueue<Node>(compareBy { it.cost })
    pq.add(Node(1, 0))
    var cost = 0
    var count = 0
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        if (visited[curr.to]) continue
        visited[curr.to] = true
        cost += curr.cost + count * t
        if (cost != 0) count++
        for (adjNode in graph[curr.to]) {
            if (visited[adjNode.to]) continue
            pq.add(adjNode)
        }
    }
    println(cost)
}