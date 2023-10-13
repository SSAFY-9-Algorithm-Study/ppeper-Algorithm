package week28

import java.util.*

class 부대복귀 {
    private var N = 0
    private lateinit var answer: IntArray
    private lateinit var graph: Array<LinkedList<Pair<Int, Int>>>

    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        N = n
        answer = IntArray(sources.size) { -1 }
        graph = Array(n + 1) { LinkedList() }
        initGraph(roads)
        getShortestDistance(sources, destination)
        return answer
    }

    private fun initGraph(roads: Array<IntArray>) {
        roads.forEach { (a, b) ->
            graph[a].add(Pair(b, 1))
            graph[b].add(Pair(a, 1))
        }
    }

    private fun getShortestDistance(sources: IntArray, destination: Int) {
        val distance = IntArray(N + 1) { Int.MAX_VALUE }
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        distance[destination] = 0
        pq.add(Pair(destination, 0))
        while (pq.isNotEmpty()) {
            val curr = pq.poll()
            // 새로 선택된 node보다 거리가 적음 -> 이미 방문함
            if (distance[curr.first] < curr.second) continue
            // 시작노드의 인접 노드
            for (node in graph[curr.first]) {
                val next = node.first
                val nextDist = node.second + curr.second
                if (nextDist < distance[next]) {
                    distance[next] = nextDist
                    pq.add(Pair(next, nextDist))
                }
            }
        }
        for(i in sources.indices) {
            if (distance[sources[i]] != Int.MAX_VALUE) {
                answer[i] = distance[sources[i]]
            }
        }
    }
}