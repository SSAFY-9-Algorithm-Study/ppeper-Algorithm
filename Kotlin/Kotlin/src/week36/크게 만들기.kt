package week36

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val number = readLine()
    val pick = Stack<Char>()
    var count = 0
    for (num in number.indices) {
        // 마지막은 다 넣어주기
        if (count == k) {
            for (c in number.substring(num)) pick.push(c)
            break
        }
        while (pick.isNotEmpty() && pick.peek() < number[num] && count < k) {
            pick.pop()
            count++
        }
        pick.push(number[num])
    }
    // 마지막까지 다 돌았는데 아직 뺀 숫자가 부족함
    while (count < k) {
        pick.pop()
        count++
    }
    println(pick.joinToString("".reversed()))
}