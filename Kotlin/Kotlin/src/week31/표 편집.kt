package week31

import java.util.*
// 표의 행을 선택, 삭제, 복구하는 프로그램을 작성
// 정확성 O, 효율성 X
data class DeleteUser (
    val index: Int,
    val number: Int
)
const val UP = "U"
const val DOWN = "D"
const val ROLLBACK = "Z"
const val REMOVE = "C"
const val DELETED = "X"
const val SURVIVE = "O"
private lateinit var array: LinkedList<Int>
private lateinit var deleted: Stack<DeleteUser>
private lateinit var answer: Array<String>
private var currentUserNumber = 0
private var currentUserIndex = 0
class `표 편집` {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        answer = Array(n) { DELETED }
        deleted = Stack()
        initInput(n, k)
        runCommand(cmd)
        return answer.joinToString("")
    }

    private fun runCommand(cmd: Array<String>) {
        cmd.forEach {
            runOperation(it)
        }
        for (number in array) {
            answer[number] = SURVIVE
        }
    }

    private fun initInput(n: Int, k: Int) {
        currentUserNumber = k
        currentUserIndex = k
        array = LinkedList<Int>().apply {
            repeat(n) { number ->
                add(number)
            }
        }
    }

    // 수행 함수 로직
    private fun runOperation(cmd: String) {
        val oper = cmd.split(" ")
        when (oper[0]) {
            UP -> {
                currentUserIndex -= oper[1].toInt()
                currentUserNumber = array[currentUserIndex]
            }
            DOWN -> {
                currentUserIndex += oper[1].toInt()
                currentUserNumber = array[currentUserIndex]
            }
            REMOVE -> {
                // 지워지는 칸이 마지막 index는 바로 위 선택
                if (currentUserIndex == array.lastIndex) {
                    val number = array.last
                    deleted.push(DeleteUser(array.lastIndex, number))
                    array.removeLast()
                    currentUserIndex = array.lastIndex
                    currentUserNumber = array[currentUserIndex]
                } else {
                    val number = array[currentUserIndex]
                    deleted.push(DeleteUser(currentUserIndex, number))
                    array.removeAt(currentUserIndex)
                    currentUserNumber = array[currentUserIndex]
                }
            }
            ROLLBACK -> {
                val rollBackUser = deleted.pop()
                array.add(rollBackUser.index, rollBackUser.number)
                // 복구되는 유저가 위쪽에 있음
                if (rollBackUser.index <= currentUserIndex) {
                    currentUserIndex += 1
                }
                currentUserNumber = array[currentUserIndex]
            }
            else -> return
        }
    }
}

fun main() {
    val s = `표 편집`()
    println(s.solution(8, 2, arrayOf("D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C")))
}