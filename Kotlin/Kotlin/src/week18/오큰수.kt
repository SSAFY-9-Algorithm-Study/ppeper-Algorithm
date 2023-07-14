package week18

import java.util.*

private lateinit var list: IntArray
private lateinit var answer: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    list = readLine().split(" ").map { it.toInt() }.toIntArray()
    answer = IntArray(n) { -1 }
    val stack = Stack<Int>()
    for (i in list.indices) {
        while (stack.isNotEmpty() && list[stack.peek()] < list[i]) {
            answer[stack.pop()] = list[i]
        }
        stack.push(i)
    }
    println(answer.joinToString(" "))
}