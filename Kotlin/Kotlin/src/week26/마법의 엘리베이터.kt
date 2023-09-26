package week26

const val N = 10
const val MID = 5
class `마법의 엘리베이터` {
    fun solution(storey: Int): Int {
        return search(storey)
    }

    private fun search(storey: Int): Int {
        var number = storey
        var count = 0
        while (0 < number) {
            val remain = number % N
            number /= N
            when (remain) {
                in 0..4 -> count += remain
                5 -> {
                    count += if (MID <= number % N) {
                        number++
                        N - remain
                    } else {
                        remain
                    }
                }
                else -> {
                    count += (N - remain)
                    number++
                }
            }
        }
        return count
    }
}