package week24

import java.util.LinkedList

// 1 ~ 9 까지의 자연수가 적혀있거나 or X
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private lateinit var graph: Array<IntArray>
private lateinit var answer: MutableList<Int>
class `무인도 여행` {
    fun solution(maps: Array<String>): IntArray {
        answer = mutableListOf<Int>()
        graph = Array(maps.size) { IntArray(maps[0].length) }
        initMap(maps)
        checkMap()
        return if (answer.isEmpty()) intArrayOf(-1) else answer.sorted().toIntArray()
    }

    private fun initMap(maps: Array<String>) {
        for (i in maps.indices) {
            for (j in maps[0].indices) {
                if (maps[i][j] == 'X') {
                    graph[i][j] = -1
                } else {
                    graph[i][j] = maps[i][j] - '0'
                }
            }
        }
    }

    private fun checkMap() {
        for (i in graph.indices) {
            for (j in graph[0].indices) {
                if (graph[i][j] != -1) {
                    bfs(i, j)
                }
            }
        }
    }

    private fun bfs(i: Int, j: Int) {
        val queue = LinkedList<Pair<Int, Int>>()
        var food = 0
        queue.add(Pair(i, j))
        food += graph[i][j]
        graph[i][j] = -1
        while (queue.isNotEmpty()) {
            val (x, y) = queue.poll()
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx !in graph.indices || ny !in graph[0].indices) continue
                if (graph[nx][ny] != -1) {
                    food += graph[nx][ny]
                    graph[nx][ny] = -1
                    queue.add(Pair(nx, ny))
                }
            }
        }
        answer.add(food)
    }
}