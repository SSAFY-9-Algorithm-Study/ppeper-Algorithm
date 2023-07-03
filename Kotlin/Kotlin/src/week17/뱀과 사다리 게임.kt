package week17

import java.util.*

private lateinit var option: IntArray
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    option = IntArray(101)
    visited = BooleanArray(101)
    repeat(n) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        option[a] = b
    }
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        option[a] = b
    }
    println(bfs())
}

private fun bfs(): Int {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.offer(Pair(1, 0))
    visited[1] = true
    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        for (dice in 1..6) {
            val nx = if (option[curr.first + dice] == 0) curr.first + dice else option[curr.first + dice]
            if (100 <= nx) {
                return curr.second + 1
            }
            if (visited[nx]) continue
            visited[nx] = true
            queue.offer(Pair(nx, curr.second + 1))
        }
    }
    return -1
}
