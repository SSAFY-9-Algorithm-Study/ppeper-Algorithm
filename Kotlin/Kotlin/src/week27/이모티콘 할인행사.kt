package week27

class `이모티콘 할인행사` {
    private val answer = IntArray(2)
    private val RATE = intArrayOf(10, 20, 30, 40)
    private lateinit var pick: IntArray

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        pick = IntArray(emoticons.size)
        // 중복조합으로 모든 이모티콘에 대해서 모든 할인율 구하기
        emoticons_sales_rate(0, emoticons.size, users, emoticons)
        return answer
    }

    private fun emoticons_sales_rate(n: Int, r: Int, users: Array<IntArray>, emoticons: IntArray) {
        if (n == r) {
            calculate(users, emoticons)
            return
        }
        for (i in RATE.indices) {
            pick[n] = RATE[i]
            emoticons_sales_rate(n + 1, r, users, emoticons)
        }
    }

    private fun calculate(users: Array<IntArray>, emoticons: IntArray) {
        var plusUsers = 0
        var totalSum = 0
        users.forEach { (rate, overPrice) ->
            var sum = 0
            // 이모티콘 계산
            emoticons.forEachIndexed { index, emoticon ->
                // 할인율을 넘어야 구매
                if (rate <= pick[index]) {
                    val price = emoticon * (100 - pick[index]) / 100
                    sum += price
                }
            }
            // 이모티콘 플러스 가입 여부 -> 10000원이상 시
            if (overPrice <= sum) {
                plusUsers++
            } else {
                totalSum += sum
            }
        }
        if (answer[0] == plusUsers) {
            // 판매액을 가장 크게 업데이트
            answer[1] = answer[1].coerceAtLeast(totalSum)
        } else if (answer[0] < plusUsers) {
            // 우선 순위에 따라 플러스 가입여부가 많은 쪽으로 업데이트
            answer[0] = plusUsers
            answer[1] = totalSum
        }
    }
}