package week37

import java.util.*

private var MAX = 0
private var SIZE = 0
fun main() = with(System.`in`.bufferedReader()) {
    SIZE = readLine().toInt()
    val board = Array(SIZE) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    gameStart(board, 0)
    println(MAX)
}

private fun gameStart(board: Array<IntArray>, move: Int) {
    if (move == 5) {
        MAX = MAX.coerceAtLeast(board.maxOf { it.max() })
        return
    }
    for (key in 0 until 4) {
        // 이동전 보드모양 저장
        val curr = deepClone(board)
        when (key) {
            // 좌
            0 -> {
                for (i in 0 until SIZE) {
                    curr[i] = merge(curr[i])
                }
            }
            // 우
            1 -> {
                for (i in 0 until SIZE) {
                    curr[i] = merge(curr[i].reversedArray()).reversedArray()
                }
            }
            // 위, 아래
            2, 3 -> {
                for (i in 0 until SIZE) {
                    val vertical = IntArray(SIZE)
                    for (j in 0 until SIZE) {
                        vertical[j] = curr[j][i]
                    }
                    if (key == 2) {
                        val list = merge(vertical)
                        for (j in 0 until SIZE) {
                            curr[j][i] = list[j]
                        }
                    } else {
                        val list = merge(vertical.reversedArray()).reversedArray()
                        for (j in 0 until SIZE) {
                            curr[j][i] = list[j]
                        }
                    }
                }
            }
        }
        gameStart(curr, move + 1)
    }
}

private fun merge(list: IntArray): IntArray {
    val filter = list.filter { it != 0 }
    val stack = Stack<Int>()
    val mergeList = IntArray(SIZE) { 0 }
    // 한번에 한번씩만 합쳐짐
    var isMerge = false
    filter.forEach { number ->
        if (stack.isEmpty()) {
            stack.push(number)
        } else {
            val curr = stack.peek()
            // 합칠 수 있는 숫자 인지 확인
            if (curr == number && !isMerge) {
                isMerge = true
                stack.run {
                    pop()
                    push(number * 2)
                }
            } else {
                isMerge = false
                stack.push(number)
            }
        }
    }
    for (i in stack.indices) {
        mergeList[i] = stack[i]
    }
    return mergeList
}

private fun deepClone(board: Array<IntArray>) = Array(SIZE) { board[it].clone() }
