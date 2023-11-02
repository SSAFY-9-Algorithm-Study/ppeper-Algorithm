package week31

const val INF = 3000000
class `합승 택시 요금` {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        var answer = INF
        val dist = Array(n + 1) { IntArray(n + 1) { INF } }.apply {
            for (i in 1..n) {
                this[i][i] = 0
            }
        }
        // 택시 요금
        for ((from, to, cost) in fares) {
            dist[from][to] = cost
            dist[to][from] = cost
        }
        // 각 지점까지의 최소 cost
        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]
                    }
                }
            }
        }
        // 어느 한 곳을 들렸다 갈때의 최소 값
        for (k in 1..n) {
            answer = answer.coerceAtMost(dist[s][k] + dist[k][a] + dist[k][b])
        }
        return answer
    }
}