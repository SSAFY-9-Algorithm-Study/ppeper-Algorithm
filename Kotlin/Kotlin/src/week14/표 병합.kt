package week14

const val EMPTY = "EMPTY"
const val UPDATE = "UPDATE"
const val MERGE = "MERGE"
const val UNMERGE = "UNMERGE"
const val PRINT = "PRINT"
data class Position(
    var r: Int,
    var c: Int
)
class `표 병합` {
    private lateinit var board: Array<Array<String>>
    private lateinit var state: Array<Array<Position>>

    fun solution(commands: Array<String>): Array<String> {
        val answer = ArrayList<String>()
        board = Array(51) { Array(51) { EMPTY } }
        state = Array(51) { Array(51) { Position(0, 0) } }
        initState()
        commands.forEach { command ->
            val oper = command.split(" ")
            when (oper[0]) {
                UPDATE -> {
                    // ex) "UPDATE r c value"
                    if (oper.size == 4) {
                        val r = oper[1].toInt()
                        val c = oper[2].toInt()
                        val value = oper[3]
                        board[state[r][c].r][state[r][c].c] = value
                    } else { // ex) "UPDATE value1 value2"
                        val value1 = oper[1]
                        val value2 = oper[2]
                        updateValue(value1, value2)
                    }

                }
                MERGE -> {
                    val r1 = oper[1].toInt()
                    val c1 = oper[2].toInt()
                    val r2 = oper[3].toInt()
                    val c2 = oper[4].toInt()
                    // 현재 표에 따라 좌표
                    val sr1 = state[r1][c1].r
                    val sc1 = state[r1][c1].c
                    val sr2 = state[r2][c2].r
                    val sc2 = state[r2][c2].c
                    // 앞에가 비어있으면 뒤에껄로 값 할당
                    if (board[sr1][sc1] == EMPTY) {
                        board[sr1][sc1] = board[sr2][sc2]
                    }
                    merge(sr1, sc1, sr2, sc2)
                }
                UNMERGE -> {
                    val r = oper[1].toInt()
                    val c = oper[2].toInt()
                    // 현재 표에 따라 좌표
                    val sr = state[r][c].r
                    val sc = state[r][c].c
                    val buffer = board[sr][sc]
                    unmerge(sr, sc)
                    // r, c는 그대로
                    board[r][c] = buffer
                }
                PRINT -> {
                    val r = oper[1].toInt()
                    val c = oper[2].toInt()
                    answer.add(board[state[r][c].r][state[r][c].c])
                }
            }
        }
        return answer.toTypedArray()
    }

    private fun merge(r1: Int, c1: Int, r2: Int, c2: Int) {
        for (i in 1..50) {
            for (j in 1..50) {
                if (state[i][j].r == r2 && state[i][j].c == c2) {
                    state[i][j].r = r1
                    state[i][j].c = c1
                }
            }
        }
    }

    private fun unmerge(sr: Int, sc: Int) {
        for (i in 1..50) {
            for (j in 1..50) {
                if (state[i][j].r == sr && state[i][j].c == sc) {
                    state[i][j] = Position(i, j)
                    board[i][j] = EMPTY
                }
            }
        }
    }

    private fun updateValue(value1: String, value2: String) {
        for (i in 1..50) {
            for (j in 1..50) {
                if (board[i][j] == value1) board[i][j] = value2
            }
        }
    }

    private fun initState() {
        for (i in 1..50) {
            for (j in 1..50) {
                state[i][j] = Position(i, j)
            }
        }
    }
}

fun main() {
    val s = `표 병합`()
    println(s.solution(arrayOf("UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4")).toList())
}