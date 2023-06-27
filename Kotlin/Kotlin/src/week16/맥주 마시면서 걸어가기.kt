package week16

import java.util.*
import kotlin.math.abs

data class Position(
    var x: Int,
    var y: Int
)
fun main() = with(System.`in`.bufferedReader()) {
    val testCase = readLine().toInt()
    val answer = ArrayList<String>()
    // 테이스 케이스 수
    for (i in 0 until testCase) {
        val count =  readLine().toInt()
        val graph = Array(count + 2) { LinkedList<Int>() }
        val visited = BooleanArray(count + 2)
        // 좌표들
        val list = ArrayList<Position>()
        for (j in 0 until count + 2) {
            val input = readLine().split(" ").map { it.toInt() }
            list.add(Position(input[0], input[1]))
        }
        // 맨허튼 거리가 1000이하는 갈 수 있음
        for (a in list.indices) {
            for (b in a until list.size) {
                if (abs(list[a].x - list[b].x) + abs(list[a].y - list[b].y) <= 1000) {
                    graph[a].add(b)
                    graph[b].add(a)
                }
            }
        }
        // 패스티벌 이동
        val queue = LinkedList<Int>()
        queue.offer(0)
        while (queue.isNotEmpty()) {
            val v = queue.poll()
            for (adj in graph[v]) {
                if (!visited[adj]) {
                    visited[adj] = true
                    queue.offer(adj)
                }
            }
        }
        // 마지막 -> 패스티벌을 방문했으면 happy
        if (visited[count + 1]) answer.add("happy") else answer.add("sad")
    }
    answer.forEach {
        println(it)
    }
}