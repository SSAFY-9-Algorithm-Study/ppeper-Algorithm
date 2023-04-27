package week11

import java.util.PriorityQueue
import java.util.StringTokenizer

private lateinit var parent: IntArray
data class Node(
    val from: Int,
    val to: Int,
    val weight: Int
): Comparable<Node> {
    override fun compareTo(other: Node) = this.weight - other.weight
}
fun main()  = with(System.`in`.bufferedReader()) {
    var weightSum = 0
    val pq = PriorityQueue<Node>()
    val v = readLine().toInt()
    // 처음 부모는 자기자신으로 초기화
    parent = IntArray(v + 1) { it }
    val e = readLine().toInt()
    for (i in 0 until e) {
        val st = StringTokenizer(readLine())
        pq.offer(Node(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt()))
    }
    while (pq.isNotEmpty()) {
        val (from, to, weight) = pq.poll()
        if (find(from) != find(to)) {
            unionParent(from, to)
            weightSum += weight
        }
    }
    println(weightSum)
}

private fun find(node: Int): Int {
    if (parent[node] != node) {
        parent[node] = find(parent[node])
    }
    return parent[node]
}

private fun unionParent(a: Int, b: Int) {
    val pa = find(a)
    val pb = find(b)
    if (pa < pb) parent[pb] = pa
    else parent[pa] = pb
}