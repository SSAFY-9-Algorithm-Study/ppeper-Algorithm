package week20

import java.util.LinkedList

class `양과 늑대` {
    private lateinit var visited: BooleanArray
    private lateinit var graph: Array<LinkedList<Int>>
    private var maxSheepCount = 1
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        visited = BooleanArray(info.size)
        graph = Array(info.size) { LinkedList() }
        edges.forEach { (parent, child) ->
            graph[parent].add(child)
        }
        visited[0] = true
        dfs(1, 0, info, graph[0].toList())
        return maxSheepCount
    }

    // 양이 늑대보다 많아야 한다.
    // 전에 있던 parent가 방문이 되어있을때 child들을 방문 가능하다
    private fun dfs(sheep: Int, wolf: Int, info: IntArray, available: List<Int>) {
        if (sheep <= wolf) {
            maxSheepCount = maxSheepCount.coerceAtLeast(sheep)
            return
        }
        for (node in available) {
            if (visited[node]) continue
            visited[node] = true
            if (info[node] == 0) {
                dfs(sheep + 1, wolf, info, available + graph[node].toList())
            } else {
                dfs(sheep, wolf + 1, info, available + graph[node].toList())
            }
            visited[node] = false
        }
        maxSheepCount = maxSheepCount.coerceAtLeast(sheep)
    }
}

fun main() {
    val t = `양과 늑대`()
    val info = intArrayOf(0,0,1,1,1,0,1,0,1,0,1,1)
    val edges = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 2),
        intArrayOf(1, 4),
        intArrayOf(0, 8),
        intArrayOf(8, 7),
        intArrayOf(9, 10),
        intArrayOf(9, 11),
        intArrayOf(4, 3),
        intArrayOf(6, 5),
        intArrayOf(4, 6),
        intArrayOf(8, 9)
    )
    println(t.solution(info, edges))
}