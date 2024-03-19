package week45

// 동서남북
private val dx = intArrayOf(0, 0, -1, 1)
private val dy = intArrayOf(1, -1, 0, 0)
private lateinit var visited: Array<BooleanArray>
private lateinit var p: List<Double> // 방향마다 이동할 확률
private var N = 0
private var answer = 0.0
fun main() = with(System.`in`.bufferedReader()) {
    val input = readLine().split(" ").map { it.toInt() }
    N = input[0]
    p = input.takeLast(4).map { it * 0.01 }
    visited = Array(2 * N + 1) { BooleanArray(2 * N + 1) }.apply {
        this[N][N] = true
    }
    robotMove(N, N, 0, 1.0)
    println(answer)
}

private fun robotMove(x: Int, y: Int, move: Int, percent: Double) {
    if (move == N) {
        answer += percent
        return
    }
    for (i in 0 until 4) {
        val nx = x + dx[i]
        val ny = y + dy[i]

        if (visited[nx][ny]) continue
        if (nx !in visited.indices || ny !in visited.indices) continue
        visited[nx][ny] = true
        robotMove(nx, ny, move + 1, percent * p[i])
        visited[nx][ny] = false
    }
}
