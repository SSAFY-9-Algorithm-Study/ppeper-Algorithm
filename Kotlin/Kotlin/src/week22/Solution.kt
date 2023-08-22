package week22

// 억억단을 외우자
class Solution {
    fun solution(e: Int, starts: IntArray): IntArray {
        val answer = IntArray(starts.size)
        // 개수들을 저장해둘 배열
        val count = IntArray(e + 1)
        // 약수의 개수
        for (i in 1..e) {
            for (j in i..e step i) {
                count[j] += 1
            }
        }
        val sortedList = count.zip(count.indices).sortedWith(compareByDescending<Pair<Int, Int>> { it.first }.thenBy { it.second })
        for (k in starts.indices) {
            for((_, number) in sortedList) {
                if (number in starts[k]..e) {
                    answer[k] = number
                    break
                }
            }
        }
        return answer
    }
}

fun main() {
    val s = Solution()
    println(s.solution(8, intArrayOf(1,3,7)))
}