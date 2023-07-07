package week17

import java.util.StringTokenizer
import kotlin.math.abs

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val st = StringTokenizer(readLine())
    var answer = 0
    var max = 0
    val positive = ArrayList<Int>()
    val negative = ArrayList<Int>()
    for (i in 0 until n) {
        val value = st.nextToken().toInt()
        if (0 < value) positive.add(value) else negative.add(abs(value))
        max = max.coerceAtLeast(abs(value))
    }
    positive.sortDescending()
    negative.sortDescending()
    for (i in positive.indices step m) {
        answer += positive[i] * 2
    }
    for (j in negative.indices step m) {
        answer += negative[j] * 2
    }
    println(answer - max)
}