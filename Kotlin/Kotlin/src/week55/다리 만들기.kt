package week55

import java.util.*

const val SET_BOARD = 1
const val SET_BRIDGE = 2
private var answer = Int.MAX_VALUE
private lateinit var graph: Array<IntArray>
private lateinit var visited: Array<IntArray>
private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    graph = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    var number = 2
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (graph[i][j] == 1) {
                bfs(i, j, number++, SET_BOARD)
            }
        }
    }
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (graph[i][j] != 0) {
                visited = Array(n) { IntArray(n) { -1 } }
                bfs(i, j, graph[i][j], SET_BRIDGE)
            }
        }
    }
    println(answer)
}

private fun bfs(i: Int, j: Int, number: Int, type: Int) {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(Pair(i, j))
    when (type) {
        SET_BOARD -> {
            graph[i][j] = number

            while (queue.isNotEmpty()) {
                val (x, y) = queue.poll()
                for (i in 0 until 4) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    if (nx !in graph.indices || ny !in graph.indices) continue
                    if (graph[nx][ny] == 1) {
                        queue.add(Pair(nx, ny))
                        graph[nx][ny] = number
                    }
                }
            }
        }
        SET_BRIDGE -> {
            visited[i][j] = 0

            while (queue.isNotEmpty()) {
                val (x, y) = queue.poll()
                for (i in 0 until 4) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    if (nx !in graph.indices || ny !in graph.indices) continue
                    if (visited[nx][ny] != -1) continue
                    if (graph[nx][ny] == number) continue
                    if (graph[nx][ny] == 0) {
                        queue.add(Pair(nx, ny))
                        visited[nx][ny] = visited[x][y] + 1
                    } else {
                        // 다른 지역 도착
                        answer = answer.coerceAtMost(visited[x][y])
                    }
                }
            }
        }
    }
}
