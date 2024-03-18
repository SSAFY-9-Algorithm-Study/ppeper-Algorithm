package week45

private lateinit var board: Array<IntArray>
private lateinit var sum: Array<LongArray>
private var answer = 0L
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    board = Array(n) { readLine().toCharArray().map { it - '0' }.toIntArray() }
    sum = Array(n + 1) { LongArray(m + 1) }
    for (i in 1..n) {
        for (j in 1..m) {
            sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + board[i - 1][j - 1]
        }
    }
    // 가로 세줄
    for (i in 1 until n - 1) {
        for (j in i + 1 until n) {
            val one = calculateSum(1, 1, i, m)
            val two = calculateSum(i + 1, 1, j, m)
            val three = calculateSum(j + 1, 1, n, m)
            answer = answer.coerceAtLeast(one * two * three)
        }
    }
    // 세로 세줄
    for (i in 1 until m - 1) {
        for (j in i + 1 until m) {
            val one = calculateSum(1, 1, n, i)
            val two = calculateSum(1, i + 1, n, j)
            val three = calculateSum(1, j + 1, n, m)
            answer = answer.coerceAtLeast(one * two * three)
        }
    }
    // 상단 가로 아래 세로
    for (i in 1 until n) {
        for (j in 1 until m) {
            val one = calculateSum(1, 1, i, m)
            val two = calculateSum(i + 1, 1, n, j)
            val three = calculateSum(i + 1, j + 1, n, m)
            answer = answer.coerceAtLeast(one * two * three)
        }
    }
    // 상단 세로 하단 가로
    for (i in 1 until n) {
        for (j in 1 until m) {
            val one = calculateSum(1, 1, i, j)
            val two = calculateSum(1, j + 1, i, m)
            val three = calculateSum(i + 1, 1, n, m)
            answer = answer.coerceAtLeast(one * two * three)
        }
    }
    // 왼쪽 세로 오른쪽 가로
    for (i in 1 until m) {
        for (j in 1 until n) {
            val one = calculateSum(1, 1, n, i)
            val two = calculateSum(1, i + 1, j, m)
            val three = calculateSum(j + 1, i + 1, n, m)
            answer = answer.coerceAtLeast(one * two * three)
        }
    }
    // 왼쪽 가로 오른쪽 세로
    for (i in 1 until n) {
        for (j in 1 until m) {
            val one = calculateSum(1, 1, i, j)
            val two = calculateSum(i + 1, 1, n, j)
            val three = calculateSum(1, j + 1, n, m)
            answer = answer.coerceAtLeast(one * two * three)
        }
    }
    println(answer)
}

private fun calculateSum(fromX: Int, fromY: Int, toX: Int, toY: Int): Long {
    return sum[toX][toY] - sum[fromX - 1][toY] - sum[toX][fromY - 1] + sum[fromX - 1][fromY - 1]
}
