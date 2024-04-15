package week51

import java.util.PriorityQueue

private var answer = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    repeat(t) {
        val n = readLine().toInt()
        var sum = 0L
        val pq = PriorityQueue(readLine().split( " ").map { it.toLong() })
        while (pq.isNotEmpty() && 1 < pq.size) {
            val buffer = pq.poll() + pq.poll()
            sum += buffer
            pq.add(buffer)
        }
        answer.append("$sum\n")
    }
    println(answer)
}