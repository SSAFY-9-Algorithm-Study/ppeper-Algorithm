package week10

private lateinit var graph: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(m) { IntArray(n) }
    visited = Array(m) { BooleanArray(n) }
    for (i in 0 until m) {
        val input = readLine().toCharArray()
        for (j in input.indices) {
            graph[i][j] = input[j] - '0'
        }
    }
    val deque = ArrayDeque<Triple<Int, Int, Int>>()
    deque.addFirst(Triple(0, 0, 0))
    visited[0][0] = true
    println(checkMap(deque))
}

private fun checkMap(deque: ArrayDeque<Triple<Int, Int, Int>>): Int {
    while (deque.isNotEmpty()) {
        val (x, y, weight) = deque.removeFirst()
        if (x == graph.size - 1 && y == graph[0].size - 1) return weight

        for (i in 0 until 4) {
            val dx = x + dx[i]
            val dy = y + dy[i]
            if (dx in graph.indices && dy in graph[0].indices && !visited[dx][dy]) {
                if (graph[dx][dy] == 1) {
                    deque.addLast(Triple(dx, dy, weight + 1))
                } else {
                    deque.addFirst(Triple(dx, dy, weight))
                }
                visited[dx][dy] = true
            }
        }
    }
    return 0
}