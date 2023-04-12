package week8

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private lateinit var visited: Array<BooleanArray>
private lateinit var map: Array<List<Int>>
private lateinit var dp: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val (m, n) = readLine().split(" ").map { it.toInt() }
    map = Array(m) { readLine().split(" ").map { it.toInt() } }
    visited = Array(m) { BooleanArray(n) }
    // 경로 저장
    dp = Array(m) { IntArray(n) }
    println(move(m - 1, n - 1))
}

private fun move(x: Int, y: Int): Int {
    if (x == 0 && y == 0) {
        return 1
    }
    if (!visited[x][y]) {
        visited[x][y] = true
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx in map.indices && ny in map[0].indices) {
                if (map[x][y] < map[nx][ny]) {
                    dp[x][y] += move(nx, ny)
                }
            }
        }
    }
    return dp[x][y]
}
