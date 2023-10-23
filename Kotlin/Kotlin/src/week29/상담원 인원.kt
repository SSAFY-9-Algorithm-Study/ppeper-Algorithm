package week29

import java.util.*

const val IDLE = 0
const val ONE = 1
class `상담원 인원` {

    private lateinit var pick: IntArray
    private var answer = Int.MAX_VALUE
    private var K = 0
    fun solution(k: Int, n: Int, reqs: Array<IntArray>): Int {
        K = k
        // 각 유형의 상담원의 수
        pick = IntArray(k) { ONE }
        comb(n - k, 0, reqs)
        return answer
    }

    private fun comb(n: Int, start: Int, reqs: Array<IntArray>) {
        if (n == 0) {
            checkTime(reqs)
            return
        }
        for (i in start until pick.size) {
            pick[i]++
            comb(n - 1, i, reqs)
            pick[i]--
        }
    }

    private fun checkTime(reqs: Array<IntArray>) {
        var restTime = 0
        // 상담원 인원만큼 추기화
        val pqList = Array(K) { idx ->
            PriorityQueue<Int>().also { pq ->
                repeat(pick[idx]) { pq.add(IDLE) }
            }
        }
        reqs.forEach { (time, consultTime, type) ->
            val currentConsultant = pqList[type - 1]
            val currentConsultantTime = currentConsultant.poll()
            // 바로 상담 가능
            if (currentConsultantTime == time) {
                currentConsultant.offer(currentConsultantTime + consultTime)
            } else if (currentConsultantTime < time) {
                // 도착시간이 더길때 -> 상담원은 끝나고 대기하고 있었으므로 바로 가능
                // 도착한 시간에서 상담을 진행하면 된다. (time + consultTime)
                currentConsultant.offer(time + consultTime)
            } else {
                // 도착시간이 더 빠를때 -> 상담이 끝낼 때 까지 대기해야함
                restTime += (currentConsultantTime - time)
                currentConsultant.offer(currentConsultantTime + consultTime)
            }
        }
        answer = answer.coerceAtMost(restTime)
    }
}

fun main() {
    val s = `상담원 인원`()
    println(s.solution(2, 3, arrayOf(intArrayOf())))
}