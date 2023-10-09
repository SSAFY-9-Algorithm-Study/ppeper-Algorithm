package week27

import java.util.LinkedList

data class Monkey(
    val x: Int = 0,
    val y: Int = 0,
    val move: Int = 0,
    val horseMove: Int = 0
)
private lateinit var graph: Array<IntArray>
private lateinit var visited: Array<Array<BooleanArray>>
private var K = 0
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private val ddx = intArrayOf(-1, -1, 1, 1, -2, -2, 2, 2)
private val ddy = intArrayOf(-2, 2, -2, 2, -1, 1, -1, 1)
fun main() = with(System.`in`.bufferedReader()) {
    val k = readLine().toInt()
    K = k
    val monkey = Monkey()
    val (w, h) = readLine().split(" ").map { it.toInt() }
    graph = Array(h) { IntArray(w) }
    repeat(h) {
        graph[it] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    visited = Array(k + 1) { Array(h) { BooleanArray(w) } }
    println(bfs(monkey))
}

private fun bfs(monkey: Monkey): Int {
    val queue = LinkedList<Monkey>()
    queue.add(monkey)
    visited[0][0][0] = true
    while (queue.isNotEmpty()) {
        val (x, y, move, horseMove) = queue.poll()
        if (x == graph.size - 1 && y == graph[0].size - 1) return move
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx !in graph.indices || ny !in graph[0].indices) continue
            if (visited[horseMove][nx][ny]) continue
            if (graph[nx][ny] == 1) continue
            visited[horseMove][nx][ny] = true
            queue.add(Monkey(nx, ny, move + 1, horseMove))
        }
        if (horseMove < K) {
            for (j in 0 until 8) {
                val nx = x + ddx[j]
                val ny = y + ddy[j]
                if (nx !in graph.indices || ny !in graph[0].indices) continue
                if (visited[horseMove][nx][ny]) continue
                if (graph[nx][ny] == 1) continue
                visited[horseMove + 1][nx][ny] = true
                queue.add(Monkey(nx, ny, move + 1, horseMove + 1))
            }
        }
    }
    return -1
}
