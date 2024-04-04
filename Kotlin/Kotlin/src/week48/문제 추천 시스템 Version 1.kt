package week48

import java.util.PriorityQueue

data class Problem(
    val number: Int,
    val level: Int
)
const val ADD = "add"
const val RECOMMEND = "recommend"
const val SOLVED = "solved"
private lateinit var problemHard: PriorityQueue<Problem>
private lateinit var problemEasy: PriorityQueue<Problem>
private lateinit var problemLevel: HashMap<Int, Int>
private val answer = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    problemLevel = HashMap()
    problemHard = PriorityQueue(compareByDescending<Problem> { it.level }.thenByDescending { it.number })
    problemEasy = PriorityQueue(compareBy<Problem> { it.level }.thenBy { it.number })
    repeat(n) {
        val (number, level) = readLine().split(" ").map { it.toInt() }
        problemHard.offer(Problem(number, level))
        problemEasy.offer(Problem(number, level))
        problemLevel[number] = level
    }
    // 명령들
    repeat(readLine().toInt()) {
        val input = readLine().split(" ")
        when (input[0]) {
            ADD -> {
                val number = input[1].toInt()
                val level = input[2].toInt()
                problemHard.offer(Problem(number, level))
                problemEasy.offer(Problem(number, level))
                problemLevel[number] = level
            }
            RECOMMEND -> {
                val op = input[1].toInt()
                if (op == 1) {
                    while (true) {
                        val (number, level) = problemHard.peek()
                        if (problemLevel[number] != level) problemHard.poll()
                        else  {
                            answer.append("$number\n")
                            break
                        }
                    }
                } else {
                    while (true) {
                        val (number, level) = problemEasy.peek()
                        if (problemLevel[number] != level) problemEasy.poll()
                        else  {
                            answer.append("$number\n")
                            break
                        }
                    }
                }
            }
            SOLVED -> {
                val number = input[1].toInt()
                // 문제 풀었다고 지정
                problemLevel[number] = 0
            }
        }
    }
    println(answer)
}