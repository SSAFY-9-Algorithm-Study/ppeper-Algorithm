package week11

import java.util.*
import kotlin.math.max

private lateinit var st: StringTokenizer
private lateinit var inDegree: IntArray
private lateinit var cost: IntArray
private lateinit var graph: Array<LinkedList<Int>>
private lateinit var dist: IntArray
private var sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val T = readLine().toInt()
    repeat(T) {
        st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()
        graph = Array(n + 1) { LinkedList() }
        inDegree = IntArray(n + 1)
        cost = IntArray(n + 1)
        dist = IntArray(n + 1)
        st = StringTokenizer(readLine())
        repeat(n) {
            cost[it + 1] = st.nextToken().toInt()
        }
        repeat(k) {
            st = StringTokenizer(readLine())
            val from = st.nextToken().toInt()
            val to = st.nextToken().toInt()
            graph[from].add(to)
            inDegree[to]++
        }
        val target = readLine().toInt()
        if (inDegree[target] == 0) {
            sb.append("${cost[target]}\n")
        } else {
            search()
            sb.append("${dist[target]}\n")
        }
    }
    println(sb)
}

private fun search() {
    val queue = LinkedList<Int>()
    for (i in 1 until inDegree.size) {
        if (inDegree[i] == 0) {
            queue.add(i)
            // 건물 짓는 cost
            dist[i] = cost[i]
        }
    }
    while (!queue.isEmpty()) {
        val curr = queue.poll()
        for (adj in graph[curr]) {
            // 가장 오래 걸리는 시간
            dist[adj] = max(dist[adj], dist[curr] + cost[adj])
            if (--inDegree[adj] == 0) {
                queue.offer(adj)
            }
        }
    }
}
