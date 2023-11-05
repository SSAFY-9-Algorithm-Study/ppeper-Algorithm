package week31
import java.util.*
import kotlin.collections.ArrayList

class 순위 {
    private lateinit var wins: Array<ArrayList<Int>>
    private lateinit var loses: Array<ArrayList<Int>>
    fun solution(n: Int, results: Array<IntArray>): Int {
        var answer = 0
        wins = Array(n) { ArrayList() }
        loses = Array(n) { ArrayList() }
        // 토너먼트의 이기고 진 결과
        results.forEach {
            wins[it[0] - 1].add(it[1] - 1)
            loses[it[1] - 1].add(it[0] - 1)
        }
        // 이긴 사람 + 진 사람
        for (i in 0 until n) {
            val wins = bfs(i, n, wins)
            val loses = bfs(i, n, loses)
            if (wins + loses == n - 1) {
                answer++
            }
        }
        return answer
    }
    private fun bfs(start: Int, n: Int, list: Array<ArrayList<Int>>): Int {
        var count = 0
        val visited = BooleanArray(n)
        val q: Queue<Int> = LinkedList()
        q.offer(start)
        // bfs
        while (q.isNotEmpty()) {
            val player = q.poll()
            visited[player] = true
            // player 리스트
            for (node in list[player]) {
                if (!visited[node]) {
                    visited[node] = true
                    q.offer(node)
                    count++
                }
            }
        }
        return count
    }
}