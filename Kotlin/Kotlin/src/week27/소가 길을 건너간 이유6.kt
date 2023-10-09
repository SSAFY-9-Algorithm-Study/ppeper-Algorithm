package week27

import java.util.LinkedList

private lateinit var connected: Array<Array<Array<BooleanArray>>>
private lateinit var graph: Array<BooleanArray>
private lateinit var cow: LinkedList<Pair<Int, Int>>
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private var answer = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, k, r) = readLine().split(" ").map { it.toInt() }
    connected = Array(n) { Array(n) { Array(n) { BooleanArray(n) } } }
    cow = LinkedList()
    repeat(r) {
        val (r1, c1, r2, c2) = readLine().split(" ").map { it.toInt() }
        connected[r1 - 1][c1 - 1][r2 - 1][c2 - 1] = true
        connected[r2 - 1][c2 - 1][r1 - 1][c1 - 1] = true
    }
    repeat(k) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        cow.add(Pair(x - 1, y - 1))
    }
    while (cow.isNotEmpty()) {
        val curr = cow.poll()
        searchCow(curr)
        for (c in cow) {
            if (!graph[c.first][c.second]) answer++
        }
    }
    println(answer)
}

private fun searchCow(curr: Pair<Int, Int>) {
    // 현재 소와 다리없이 건널 수 있는지 확인
    graph = Array(connected.size) { BooleanArray(connected.size) }
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(curr)
    graph[curr.first][curr.second] = true
    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx !in graph.indices || ny !in graph.indices) continue
            if (graph[nx][ny] || connected[x][y][nx][ny]) continue
            graph[nx][ny] = true
            queue.add(Pair(nx, ny))
        }
    }
}
