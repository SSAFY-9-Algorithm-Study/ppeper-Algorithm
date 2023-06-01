package week14

private lateinit var board: Array<IntArray>
private val dx = intArrayOf(-1, 0, -1)
private val dy = intArrayOf(0, -1, -1)
private var answer = 0
private var N = 0
private var M = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    N = n
    M = m
    board = Array(n) { IntArray(m) }
    search(0)
    println(answer)
}

private fun search(i: Int) {
    if (i == N * M) {
        answer++
        return
    }
    val row = i / M
    val col = i % M
    // 4개가 모이는지 확인
    if (!isSquare(row, col)) {
        board[row][col] = 1
        search(i + 1)
        board[row][col] = 0
    }
    search(i + 1)
}

private fun isSquare(row: Int, col: Int): Boolean {
    var count = 0
    for (i in 0 until 3) {
        val dx = row + dx[i]
        val dy = col + dy[i]
        if (dx !in 0 until N || dy !in 0 until M) return false
        if (board[dx][dy] == 1) count++
    }
    // 현재 좌표에 두면 4개로 모임
    if (count == 3) return true
    return false
}
