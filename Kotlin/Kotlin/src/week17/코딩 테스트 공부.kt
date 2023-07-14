package week17

import kotlin.math.min

class `코딩 테스트 공부` {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        // 최대로 채워야하는 알고력, 코딩력
        val max_alp = problems.maxOf { it[0] }
        val max_cop = problems.maxOf { it[1] }
        if (max_alp <= alp && max_cop <= cop) return 0
        // dp[i][j]: 알고력 i, 코딩력 j 까지 걸리는 최소 시간
        val dp = Array(max_alp + 1) { IntArray(max_cop + 1) { Int.MAX_VALUE} }

        val start_alp = if (max_alp < alp) max_alp else alp
        val start_cop = if (max_cop < cop) max_cop else cop
        // 알고력과 코딩력 가장 작은 곳은 소요시간 0
        dp[start_alp][start_cop] = 0
        for (i in start_alp..max_alp) {
            for (j in start_cop..max_cop) {
                // 1올리는데 드는 시간
                if (i + 1 < dp.size) {
                    dp[i + 1][j] = dp[i + 1][j].coerceAtMost(dp[i][j] + 1)

                }
                if (j + 1 < dp[0].size) {
                    dp[i][j + 1] = dp[i][j + 1].coerceAtMost(dp[i][j] + 1)
                }
                for (problem in problems) {
                    val (alp_req, cop_req, alp_rwd, cop_rwd, cost) = problem
                    // 현재 문제의 필요한 알고력, 코딩력이 충분함
                    if (alp_req <= i && cop_req <= j) {
                        val x = min(i + alp_rwd, max_alp)
                        val y = min(j + cop_rwd, max_cop)
                        dp[x][y] = dp[x][y].coerceAtMost(dp[i][j] + cost)
                    }
                }
            }
        }
        return dp[max_alp][max_cop]
    }
}

fun main() {
    val test = `코딩 테스트 공부`()
    val alp = 0
    val cop = 0
    val problems = arrayOf(
        intArrayOf(0, 0, 2, 1, 2),
        intArrayOf(4, 5, 3, 1, 2),
        intArrayOf(4, 11, 4, 0 , 2),
        intArrayOf(10, 4, 0, 4, 2)
    )
    println(test.solution(alp, cop, problems))
}