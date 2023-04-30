package week11

import java.util.*

private lateinit var board: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private lateinit var destroy: Array<BooleanArray>
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private var check = false
fun main() = with(System.`in`.bufferedReader()) {
    var answer = 0
    board = Array(12) { CharArray(6) }
    repeat(12) {
        board[it] = readLine().toCharArray()
    }
    while (true) {
        check = false
        visited = Array(12) { BooleanArray(6) }
        destroy = Array(12) { BooleanArray(6) }
        for (i in board.indices) {
            for (j in board[0].indices) {
                if (board[i][j] != '.')  {
                    visited[i][j] = true
                    startGame(i, j)
                }

            }
        }
        destroyBlock()
        moveBlock()
        if (!check) break
        answer++
    }
    println(answer)
}

private fun moveBlock() {
    for (col in board[0].indices) {
        val list = LinkedList<Char>()
        for (i in board.size - 1 downTo 0) {
            if (board[i][col] != '.') list.add(board[i][col])
        }
        for (i in board.size - 1 downTo 0) {
            // board 업데이트
            if (list.isNotEmpty()) {
                board[i][col] = list.poll()
            } else {
                board[i][col] = '.'
            }
        }
    }
}

private fun destroyBlock() {
    for (i in board.indices) {
        for (j in board[0].indices) {
            if (destroy[i][j]) {
                board[i][j] = '.'
            }
        }
    }
}

private fun startGame(i: Int, j: Int) {
    val target = board[i][j]
    val queue = LinkedList<Pair<Int, Int>>()
    val list = ArrayList<Pair<Int, Int>>()
    queue.offer(Pair(i, j))
    list.add(Pair(i, j))
    while (!queue.isEmpty()) {
        val (x, y) = queue.poll()
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx < 0 || board.size <= nx || ny < 0 || board[0].size <= ny) continue
            if (visited[nx][ny]) continue
            if (board[nx][ny] == target) {
                visited[nx][ny] = true
                queue.offer(Pair(nx, ny))
                list.add(Pair(nx, ny))
            }
        }
    }
    // 4개 이상인 블록만 삭제
    if (4 <= list.size) {
        check = true
        list.forEach {
            destroy[it.first][it.second] = true
        }
    }
}
