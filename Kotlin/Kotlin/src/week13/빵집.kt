package week13

private var answer = 0
private var check = false
private lateinit var graph: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private val dx = intArrayOf(-1, 0, 1)
private val dy = intArrayOf(1, 1, 1)
fun main() = with(System.`in`.bufferedReader()) {
    val (r, c) = readLine().split(" ").map { it.toInt() }
    graph = Array(r) { CharArray(c) }
    visited = Array(r) { BooleanArray(c) }
    for (i in 0 until r) {
        graph[i] = readLine().toCharArray()
    }
    for (i in 0 until r) {
        check = false
        checkMap(i, 0)
    }
    println(answer)
}

private fun checkMap(i: Int, j: Int) {
    if (j == graph[0].size - 1) {
        check = true
        answer++
        return
    }
    for (k in 0 until 3) {
        val dx = i + dx[k]
        val dy = j + dy[k]
        if (dx < 0 || graph.size <= dx || dy < 0 || graph[0].size <= dy) continue
        if (graph[dx][dy] == '.' && !visited[dx][dy]) {
            visited[dx][dy] = true
            checkMap(dx, dy)
            if (check) return
        }
    }
}
