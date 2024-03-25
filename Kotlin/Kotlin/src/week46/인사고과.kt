package week46

class 인사고과 {
    fun solution(scores: Array<IntArray>): Int {
        var answer = 1
        var maxScore = Int.MIN_VALUE
        val targetScore = scores[0][0] + scores[0][1]
        val scoresWithIndexed = scores.mapIndexed { index, array ->
            Pair(index, array)
        }
        // 근무 태도점수 내림차순, 동료 평가 점수 오름 차순
        val sortedList = scoresWithIndexed.sortedWith(compareByDescending<Pair<Int, IntArray>> { it.second[0] }.thenBy { it.second[1] })
        sortedList.forEach { (target, array) ->
            // 인센티브를 받을 수 없음
            if (array[1] < maxScore) {
                if (target == 0) return -1
            } else {
                maxScore = array[1]
                // 완호보다 더 점수가 높음
                if (targetScore < array[0] + array[1]) answer++
            }
        }
        return answer
    }
}