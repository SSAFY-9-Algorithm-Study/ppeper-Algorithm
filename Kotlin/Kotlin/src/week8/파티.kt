package week8

import java.util.LinkedList
import java.util.PriorityQueue

private var answer = Int.MIN_VALUE
private const val INF = Int.MAX_VALUE
data class Road(val dest: Int, val cost: Int)
private lateinit var graph: Array<LinkedList<Road>>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, x) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { LinkedList() }
    val dist = Array(n + 1) { IntArray(n + 1) { INF } }
    val comeBackDist = IntArray(n + 1) { INF }
    repeat(m) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        graph[from].add(Road(to, cost))
    }
    // x로 부터 각 학생까지 돌아오는 최단거리
    dijkstra(x, comeBackDist)
    // 각 시작점부터 최단 거리 구하기
    for (start in 1..n) {
        dijkstra(start, dist[start])
    }
    for (start in 1..n) {
        // 오고 가는데 가장 오래 거리는 학생 소요시간
        answer = answer.coerceAtLeast(dist[start][x] + comeBackDist[start])
    }
    println(answer)
}

private fun dijkstra(start: Int, dist: IntArray) {
    dist[start] = 0
    val pq = PriorityQueue<Road>(compareBy { it.cost })
    pq.offer(Road(start, 0))
    while (pq.isNotEmpty()) {
        val (dest, cost) = pq.poll()
        if (dist[dest] < cost) continue
        for (adj in graph[dest]) {
            val (nextNode, nextDist) = adj
            if (nextDist + cost < dist[nextNode]) {
                dist[nextNode] = nextDist + cost
                pq.offer(Road(nextNode, nextDist + cost))
            }
        }
    }
}
