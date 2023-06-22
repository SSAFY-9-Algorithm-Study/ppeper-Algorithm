package week16

import java.util.*

private lateinit var graph: Array<CharArray>
private lateinit var copy: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private val dx = intArrayOf(-1, 0, 1, 0, 0, -1, 1, -1, 1)
private val dy = intArrayOf(0, -1, 0, 1, 0, 1, -1, -1, 1)
fun main() = with(System.`in`.bufferedReader()) {
    graph = Array(8) { CharArray(8) }
    copy = Array(8) { CharArray(8) }
    repeat(8) {
        graph[it] = readLine().toCharArray()
    }
    println(bfs())
}

private fun bfs(): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(7, 0))
    while (queue.isNotEmpty()) {
        val size = queue.size
        visited = Array(8) { BooleanArray(8) }
        for (i in 0 until size) {
            val (x, y) = queue.poll()
            if (graph[x][y] == '#') continue
            if (x == 0 && y == 7) return 1
            for (i in 0 until 9) {
                val cx = x + dx[i]
                val cy = y + dy[i]
                if (cx !in 0 until 8 || cy !in 0 until 8) continue
                if (visited[cx][cy]) continue
                if (graph[cx][cy] == '#') continue
                queue.offer(Pair(cx, cy))
                visited[cx][cy] = true
            }
        }
        wallMove()
    }
    return 0
}

private fun wallMove() {
    for (i in 7 downTo 0) {
        for (j in 0 until 8) {
            if (graph[i][j] == '#') {
                graph[i][j] = '.'
                if (i == 7) continue
                else graph[i + 1][j] = '#'
            }
        }
    }
}
