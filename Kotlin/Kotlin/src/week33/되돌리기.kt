package week33

import java.util.LinkedList

const val TYPE = "type"
const val UNDO = "undo"
private lateinit var input: LinkedList<Node>
data class Node(
    val text: String = "",
    val time: Int = 0
)
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    input = LinkedList<Node>().apply {
        add(Node("", 0))
    }
    repeat(n) {
        val (op, ch, time) = readLine().split(" ")
        when (op) {
            TYPE -> {
                val current = input.last
                input.add(Node(current.text + ch, time.toInt()))
            }
            UNDO -> {
                // 뒤로 이동해야할 시간
                val moveTime = time.toInt() - ch.toInt() - 1
                if (moveTime < 0) {
                    input.add(Node("", time.toInt()))
                } else {
                    val backUp = moveTime.getBackupDataOrNull()
                    backUp?.let {
                        input.add(Node(it.text, time.toInt()))
                    } ?: input.add(Node(input.findLast { it.time < moveTime }!!.text, time.toInt())) // 존재하지 않으면 최근에 넣었던 값으로 쭉
                }
            }
        }
    }
    println(input.last.text)
}

private fun Int.getBackupDataOrNull(): Node? {
    return input.find { it.time == this }
}