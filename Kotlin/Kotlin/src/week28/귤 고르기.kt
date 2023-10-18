package week28

class `귤 고르기` {
    fun solution(k: Int, tangerine: IntArray): Int {
        var answer = 0
        val sortedTangerine = tangerine
            .groupBy { it }
            .values.map { it.count() }
            .sortedDescending()
        var remain = k
        for (tgr in sortedTangerine) {
            if (remain <= tgr) {
                answer++
                break
            } else {
                remain -= tgr
            }
            answer++
        }
        return answer
    }
}