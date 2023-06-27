package week16

private lateinit var board: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private var N = 0
private var M = 0
private var answer = 0
private val move = arrayOf(
    intArrayOf(1, 0, 0, -1),
    intArrayOf(1, 0, 0, 1),
    intArrayOf(-1, 0, 0, -1),
    intArrayOf(-1, 0, 0, 1)
)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    N = n
    M = m
    board = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }
    for (i in 0 until n) {
        board[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    calculateMax(0, 0)
    println(answer)
}

private fun calculateMax(idx: Int, sum: Int) {
    if (idx == N * M) {
        answer = answer.coerceAtLeast(sum)
        return
    }
    val i = idx / M
    val j = idx % M
    if (!visited[i][j]) {
        for (k in 0 until 4) {
            val edgeValue = ifIsValidGetWeight(i, j, k)
            if (edgeValue != 0) {
                checkState(i, j, k)
                calculateMax(idx + 1, sum + edgeValue)
                checkState(i, j, k)
            }
        }
    }
    calculateMax(idx + 1, sum)
}

private fun checkState(x: Int, y: Int, dir: Int) {
    visited[x][y] = !visited[x][y]
    for (i in 0 until 4 step 2) {
        val cx = x + move[dir][i]
        val cy = y + move[dir][i + 1]
        visited[cx][cy] = !visited[cx][cy]
    }
}

private fun ifIsValidGetWeight(x: Int, y: Int, dir: Int): Int {
    var sum = 0
    for (i in 0 until 4 step 2) {
        val cx = x + move[dir][i]
        val cy = y + move[dir][i + 1]
        if (cx !in board.indices || cy !in board[0].indices) return 0
        if (visited[cx][cy]) return 0
        sum += board[cx][cy]
    }
    return sum + board[x][y] * 2
}
