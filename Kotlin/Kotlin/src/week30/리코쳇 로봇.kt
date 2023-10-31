package week30

import java.util.LinkedList

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private lateinit var graph: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private var answer = -1
data class Robot(
    val x: Int,
    val y: Int,
    val move: Int = 0
)
class `리코쳇 로봇` {
    fun solution(board: Array<String>): Int {
        var startX = 0
        var startY = 0
        graph = board.map { it.toCharArray() }.toTypedArray()
        visited = Array(graph.size) { BooleanArray(graph[0].size) }
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == 'R') {
                    startX = i
                    startY = j
                }
            }
        }
        moveRobot(startX, startY)
        return answer
    }

    private fun moveRobot(i: Int, j: Int) {
        val queue = LinkedList<Robot>()
        queue.add(Robot(i, j))
        visited[i][j] = true
        while (queue.isNotEmpty()) {
            val (x, y, move) = queue.poll()
            if (graph[x][y] == 'G') {
                answer = move
                break
            }
            for (k in 0 until 4) {
                var nx = x + dx[k]
                var ny = y + dy[k]
                if (nx !in graph.indices || ny !in graph[0].indices) continue
                if (visited[nx][ny]) continue
                if (graph[nx][ny] == 'D') continue
                while (nx in graph.indices && ny in graph[0].indices && graph[nx][ny] != 'D') {
                    nx += dx[k]
                    ny += dy[k]
                }
                nx -= dx[k]
                ny -= dy[k]
                if (visited[nx][ny]) continue
                visited[nx][ny] = true
                queue.add(Robot(nx, ny, move + 1))
            }
        }
    }
}