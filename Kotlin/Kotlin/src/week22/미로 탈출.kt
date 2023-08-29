package week22

import java.util.*

data class Point(
    val x: Int,
    val y: Int,
    var time: Int
)
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private lateinit var graph: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private var startX = 0
private var startY = 0
private var leverX = 0
private var leverY = 0
private var distLever = 0
private var distExit = 0
class `미로 탈출` {
    fun solution(maps: Array<String>): Int {
        initGraph(maps)
        distLever = bfs(startX, startY, 'L')
        if (distLever == -1) return -1
        visited = Array(maps.size) { BooleanArray(maps[0].length) }
        distExit = bfs(leverX, leverY, 'E')
        return if (distExit == -1) -1 else distLever + distExit
    }

    private fun bfs(x: Int, y: Int, target: Char): Int {
        val queue = LinkedList<Point>()
        queue.add(Point(x, y, 0))
        visited[x][y] = true
        while (queue.isNotEmpty()) {
            val (currX, currY, time) = queue.poll()
            if (graph[currX][currY] == target) return time

            for (i in 0 until 4) {
                val nx = currX + dx[i]
                val ny = currY + dy[i]
                if (nx !in graph.indices || ny !in graph[0].indices) continue
                if (graph[nx][ny] == 'X') continue
                if (visited[nx][ny]) continue
                queue.offer(Point(nx, ny, time + 1))
                visited[nx][ny] = true
            }
        }
        return -1
    }

    private fun initGraph(maps: Array<String>) {
        val row = maps.size
        val col = maps[0].length
        graph = Array(row) { CharArray(col) }
        visited = Array(row) { BooleanArray(col) }
        for (i in 0 until row) {
            for (j in 0 until col) {
                if (maps[i][j] == 'L') {
                    leverX = i
                    leverY = j
                } else if (maps[i][j] == 'S') {
                    startX = i
                    startY = j
                }
                graph[i][j] = maps[i][j]
            }
        }
    }
}