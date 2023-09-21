package week26

import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()){
    var answer = 0
    val n = readLine().toInt()
    val cards = PriorityQueue<Int>()
    repeat(n) {
        cards.add(readLine().toInt())
    }
    // 작은것 부터 넣어야 가장 작게 나온다
    while (true) {
        if (cards.size == 1) break
        val sum = cards.poll() + cards.poll()
        answer += sum
        cards.add(sum)
    }
    println(answer)
}