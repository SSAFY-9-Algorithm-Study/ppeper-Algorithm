package week30

class `징검다리 건너기` {
    fun solution(stones: IntArray, k: Int): Int {
        var min = 0
        var max = 200_000_000
        while (min < max) {
            val mid = (min + max) / 2
            if (jumpBridge(k, mid, stones)) {
                min = mid + 1
            } else {
                max = mid
            }
        }
        return min
    }

    // 연속된 다리를 건널 수 있는지 확인
    private fun jumpBridge(k: Int, member: Int, stones: IntArray): Boolean {
        var count = 0
        for (stone in stones) {
            // 해당 인원은 건널수 없다
            if (stone - member <= 0) count++
            else count = 0
            if (count == k) return false
        }
        return true
    }
}