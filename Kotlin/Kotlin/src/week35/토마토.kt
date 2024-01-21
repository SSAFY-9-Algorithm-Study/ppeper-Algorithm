package week35

import java.util.*

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private lateinit var tomato: Array<IntArray>
private lateinit var list: LinkedList<Pair<Int, Int>>
// 안익은 토마토
private var TOMATO = 0
private var DAY = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (m, n) = readLine().split(" ").map { it.toInt() }
    list = LinkedList()
    tomato = Array(n) { IntArray(m) }
    repeat(n) {
        readLine().split(" ").map { it.toInt() }.forEachIndexed { index, input ->
            tomato[it][index] = input
            if (input == 0) TOMATO++
            else if (input == 1) list.offer(Pair(it, index))
        }
    }
    dayPass()
    if (TOMATO == 0) println(DAY) else println(-1)
}

private fun dayPass() {
    while (0 < TOMATO && list.isNotEmpty()) {
        repeat(list.size) {
            val curr = list.poll()
            for (i in 0 until 4) {
                val nx = curr.first + dx[i]
                val ny = curr.second + dy[i]
                if (nx !in tomato.indices || ny !in tomato[0].indices) continue
                if (tomato[nx][ny] == 0) {
                    tomato[nx][ny] = 1
                    TOMATO--
                    list.offer(Pair(nx, ny))
                }
            }
        }
        DAY++
    }
}
