package week23

import java.util.Deque
import java.util.LinkedList

private var dx = intArrayOf(0, 1, 0, -1)    // 우하좌상
private var dy = intArrayOf(1, 0, -1, 0)
private lateinit var board: Array<IntArray>
private lateinit var action: LinkedList<Pair<Int, String>>
private var DIRECTION = 0
private var TIME = 0
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val k = readLine().toInt()
    action = LinkedList()
    board = Array(n) { IntArray(n) }
    repeat(k) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        board[x - 1][y - 1] = 10
    }
    // 이동 경로
    val move = readLine().toInt()
    val snake: Deque<Pair<Int, Int>> = LinkedList()
    repeat(move) {
        val (time, op) = readLine().split(" ")
        action.add(Pair(time.toInt(), op))
    }
    // 처음 시작
    snake.offer(Pair(0, 0))
    board[0][0] = 1
    while (true) {
        TIME++
        val x = snake.last.first + dx[DIRECTION % 4]
        val y = snake.last.second + dy[DIRECTION % 4]
        // 자기자신에 부딪치거나, 밖으로 나가면 종료
        if ((x !in board.indices || y !in board.indices) || board[x][y] == 1) break
        // 사과 먹음
        if (board[x][y] == 10) {
            snake.offer(Pair(x, y))
            board[x][y] = 1
        } else {
            val poll = snake.poll()
            board[poll.first][poll.second] = 0
            snake.offer(Pair(x, y))
            board[x][y] = 1
        }
        // 이동
        if (action.isNotEmpty()) {
            // 회전할 시간
            if (action.peek().first == TIME) {
                DIRECTION += if (action.poll().second == "D") {
                    1
                } else {
                    3
                }
            }
        }
    }
    println(TIME)
}