package week28

import java.util.*
import kotlin.math.max

// 출입구, 산봉우리는 n까지 (50000)
// gates, summits 이외는 다 쉼터
// O(n^2) -> 50000 * 50000 불가
data class Course(
    val to: Int,
    val weight: Int
) : Comparable<Course> {
    override fun compareTo(other: Course): Int {
        return if (other.weight == this.weight) {
            this.to - other.to
        } else {
            this.weight - other.weight
        }
    }
}

private lateinit var summitSet: HashSet<Int>
private lateinit var graph: Array<LinkedList<Course>>
private lateinit var visited: BooleanArray
private lateinit var intensity: IntArray
private var N = 0
class `등산코스 정하기` {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        summitSet = summits.toHashSet()
        visited = BooleanArray(N + 1)
        N = n
        initGraph(paths)
        return findRoute(gates, summits.sortedArray())
    }

    private fun initGraph(paths: Array<IntArray>) {
        graph = Array(N + 1) { LinkedList() }
        paths.forEach { (from, to, cost) ->
            graph[from].add(Course(to, cost))
            graph[to].add(Course(from, cost))
        }
    }

    private fun findRoute(gates: IntArray, summits: IntArray): IntArray {
        var currentSummit = Int.MAX_VALUE
        var minWeight = Int.MAX_VALUE
        val pq = PriorityQueue<Course>()
        intensity = IntArray(N + 1) { Int.MAX_VALUE }
        for (gate in gates) {
            pq.add(Course(gate, 0))
            intensity[gate] = 0
        }
        while (pq.isNotEmpty()) {
            val (to, weight) = pq.poll()
            if (intensity[to] < weight) continue

            // 산봉우리 도착
            if (summitSet.contains(to)) continue

            for (nextCourse in graph[to]) {
                // 현재까지의 최대 intensity 와 현재 가고자 하는 등산로의 weight 중 큰값
                val maxWeight = max(nextCourse.weight, intensity[to])
                if (maxWeight < intensity[nextCourse.to]) {
                    intensity[nextCourse.to] = maxWeight
                    pq.add(Course(nextCourse.to, maxWeight))
                }
            }
        }
        for (summit in summits) {
            if (intensity[summit] < minWeight) {
                minWeight = intensity[summit]
                currentSummit = summit
            }
        }
        return intArrayOf(currentSummit, minWeight)
    }
}