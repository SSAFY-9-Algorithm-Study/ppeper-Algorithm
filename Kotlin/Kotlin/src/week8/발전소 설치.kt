package week8

import java.util.*
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

private const val INF = 987654321.0
private lateinit var graph: Array<LinkedList<Pair<Int, Double>>>
private lateinit var dist: DoubleArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, w) = readLine().split(" ").map { it.toInt() }
    dist = DoubleArray(n + 1) { INF }
    graph = Array(n + 1) { LinkedList<Pair<Int, Double>>() }
    val m = readLine().toDouble()
    val nodeList = Array(n) { readLine().split(" ").map { it.toInt() } }
    // 연결되어 있는 노드
    repeat(w) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(Pair(b , 0.0))
        graph[b].add(Pair(a , 0.0))
    }
    // 각 노드끼리 거리 init
    for (i in nodeList.indices) {
        val from = nodeList[i]
        for (j in i + 1 until nodeList.size) {
            val to = nodeList[j]
            // 제한 길이: m
            if (distance(from, to) <= m) {
                graph[i + 1].add(Pair(j + 1, distance(from, to)))
                graph[j + 1].add(Pair(i + 1, distance(from, to)))
            }
        }
    }
    // 최단 거리 구하기
    val pq = PriorityQueue<Pair<Int, Double>>(compareBy { it.second })
    // 시작점
    pq.offer(Pair(1, 0.0))
    dist[1] = 0.0
    while (pq.isNotEmpty()) {
        val (node, weight) = pq.poll()
        // 거쳐
        if (dist[node] < weight) continue
        for ((nextNode, nextWight) in graph[node]) {
            // 거쳐가는 것이 더 빠를때
            if (weight + nextWight <  dist[nextNode]) {
                dist[nextNode] = weight + nextWight
                pq.offer(Pair(nextNode, weight + nextWight))
            }
        }
    }
    println((dist[n] * 1000).toInt())
}

private fun distance(from: List<Int>, to: List<Int>): Double {
    return sqrt(abs(from[0] - to[0]).toDouble().pow(2.0) + abs(from[1] - to[1]).toDouble().pow(2.0))
}
