package week15

import java.util.*

data class Computer(
    val number: Int,
    val time: Int
)
private lateinit var graph: Array<LinkedList<Computer>>
private lateinit var time: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val testCase = readLine().toInt()
    repeat(testCase) {
        val (n, d, c) = readLine().split(" ").map { it.toInt() }
        graph = Array(n + 1) { LinkedList() }
        time = IntArray(n + 1) { Int.MAX_VALUE }
        repeat(d) {
            val (a, b, s) = readLine().split(" ").map { it.toInt() }
            graph[b].add(Computer(a, s))
        }
        dijkstra(c)
        println("${time.count { it != Int.MAX_VALUE }} ${time.filterNot { it == Int.MAX_VALUE }.max()}")
    }
}

private fun dijkstra(start: Int) {
    time[start] = 0
    val pq = PriorityQueue<Computer>(compareBy { it.time })
    pq.add(Computer(start, 0))
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        if (time[curr.number] < curr.time) continue
        // 인접 노드들
        for (adjNode in graph[curr.number]) {
            val nextTime = adjNode.time + curr.time
            if (nextTime < time[adjNode.number]) {
                // 감염되는 시간 최대로 업데이트
                time[adjNode.number] = nextTime
                pq.add(Computer(adjNode.number, nextTime))
            }
        }
    }
}
