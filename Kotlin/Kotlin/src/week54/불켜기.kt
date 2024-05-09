package week54

import java.util.*
data class Node(
    val x: Int,
    val y: Int
)
private var answer = 1
private lateinit var graph: Array<Array<LinkedList<Node>>>
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private lateinit var lightOn: Array<BooleanArray>
private lateinit var visited: Array<BooleanArray>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n) { Array(n) { LinkedList() } }
    visited = Array(n) { BooleanArray(n) }
    lightOn = Array(n) { BooleanArray(n) }
    repeat(m) {
        val (x, y, a, b) = readLine().split( " ").map { it.toInt() }
        graph[x - 1][y - 1].add(Node(a - 1, b - 1))
    }
    visited[0][0] = true
    lightOn[0][0] = true
    search()
    println(answer)
}

private fun search() {
    val q = LinkedList<Node>()
    q.add(Node(0, 0))
    while (q.isNotEmpty()) {
        val (x, y) = q.poll()
        for ((nx, ny) in graph[x][y]) {
            if (lightOn[nx][ny]) continue
            lightOn[nx][ny] = true
            answer++
        }
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx !in graph.indices || ny !in graph.indices) continue
            if (!lightOn[nx][ny] || visited[nx][ny]) continue
            visited[nx][ny] = true
            q.add(Node(nx ,ny))
        }
    }
}
