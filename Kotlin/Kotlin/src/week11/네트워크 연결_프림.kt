package week11

import java.util.*

data class Node1(
    val to: Int,
    val weight: Int
): Comparable<Node1> {
    override fun compareTo(other: Node1) = this.weight - other.weight
}
private lateinit var graph: Array<LinkedList<Node1>>
private lateinit var visited: BooleanArray
fun main()  = with(System.`in`.bufferedReader()) {
    var weightSum = 0
    val pq = PriorityQueue<Node1>()
    val v = readLine().toInt()
    graph = Array(v + 1) { LinkedList() }
    visited = BooleanArray(v + 1)
    val e = readLine().toInt()
    for (i in 0 until e) {
        val st = StringTokenizer(readLine())
        val from = st.nextToken().toInt()
        val to = st.nextToken().toInt()
        val weight = st.nextToken().toInt()
        graph[from].add(Node1(to, weight))
        graph[to].add(Node1(from, weight))
    }
    // 임의의 정점으로 시작
    pq.offer(Node1(1, 0))
    while (pq.isNotEmpty()) {
        val (to, weight) = pq.poll()
        if (visited[to]) continue
        visited[to] = true
        weightSum += weight
        for (nextNode in graph[to]) {
            if (!visited[nextNode.to]) {
                pq.offer(nextNode)
            }
        }
    }
    println(weightSum)
}