package week8

import java.lang.StringBuilder

private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    for (i in 0 until n) {
        for (j in 0 until n) {
            drawStar(i, j, n / 3)
        }
        sb.append("\n")
    }
    println(sb)
}

private fun drawStar(i: Int, j: Int, n: Int) {
    if ((i / n) % 3 == 1 && (j / n) % 3 == 1) {
        sb.append(" ")
    } else if (n / 3 == 0) {
        sb.append("*")
    } else {
        drawStar(i, j, n / 3)
    }
}
