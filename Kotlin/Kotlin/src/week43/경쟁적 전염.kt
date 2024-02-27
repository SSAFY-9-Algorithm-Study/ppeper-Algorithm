package week43

import java.util.*

data class Virus(
    val x: Int,
    val y: Int,
    val type: Int
) : Comparable<Virus> {
    override fun compareTo(other: Virus) = this.type - other.type
}
private lateinit var graph: Array<IntArray>
private lateinit var virus: PriorityQueue<Virus>
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    virus = PriorityQueue()
    graph = Array(n) { IntArray(n) }
    for (i in 0 until n) {
        val input = readLine().split(" ").map { it.toInt() }.toIntArray()
        graph[i] = input
        for (j in input.indices) {
            if (input[j] != 0) virus.offer(Virus(i, j, input[j]))
        }
    }
    val (s, x, y) = readLine().split(" ").map { it.toInt() }
    repeat(s) { search() }
    println(graph[x - 1][y - 1])
}

private fun search() {
    val newVirus = PriorityQueue<Virus>()
    while (virus.isNotEmpty()) {
        val (x, y, type) = virus.poll()
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx !in graph.indices || ny !in graph.indices) continue
            if (graph[nx][ny] != 0) continue
            graph[nx][ny] = type
            newVirus.offer(Virus(nx, ny, type))
        }
    }
    virus = newVirus
}
