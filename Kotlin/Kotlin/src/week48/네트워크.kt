package week48

class `네트워크` {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0
        for (i in computers.indices) {
            if (computers[i][i] != 0) {
                dfs(i, computers)
                answer++
            }
        }
        return answer
    }

    private fun dfs(i: Int, computers: Array<IntArray>) {
        // 탐색 완료
        if (computers[i][i] == 0) {
            return
        }
        // 이웃한 네트워크 확인
        val adjList = mutableListOf<Int>()
        computers[i].forEachIndexed { index, v ->
            if (v == 1) adjList.add(index)
        }
        // 탐색 완료된 이웃한 네트워크
        adjList.forEach {
            computers[i][it] = 0
            computers[it][i] = 0
        }
        // 이웃한 네트워크 탐색
        adjList.forEach {
            dfs(it, computers)
        }
    }
}