package week51

import java.util.*

private lateinit var firstQueue: PriorityQueue<Int>
private lateinit var secondQueue: PriorityQueue<Int>
private val answer = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    firstQueue = PriorityQueue(reverseOrder<Int>())
    secondQueue = PriorityQueue()
    repeat(n) {
        val number = readLine().toInt()
        if (firstQueue.size <= secondQueue.size) {
            firstQueue.add(number)
        } else {
            secondQueue.add(number)
        }
        while (firstQueue.isNotEmpty() && secondQueue.isNotEmpty()) {
            if (firstQueue.peek() <= secondQueue.peek()) break
            val a = firstQueue.poll()
            val b = secondQueue.poll()
            firstQueue.add(b)
            secondQueue.add(a)
        }
        answer.append(firstQueue.peek()).append("\n")
    }
    println(answer)
}
