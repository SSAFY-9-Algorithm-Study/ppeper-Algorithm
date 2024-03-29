package week47

private lateinit var numbers: List<Int>
private var answer = Int.MAX_VALUE
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    numbers = readLine().split(" ").map { it.toInt() }
    for (i in 0 until numbers.size - 1) {
        for (j in i + 1 until numbers.size) {
            val diff = (numbers[j] - numbers[i])
            if (diff % (j - i) == 0) {
                val a = diff / (j - i)
                var count = 0
                for (k in 0 until n) {
                    if (numbers[k] != numbers[i] + (k - i) * a) count++
                }
                answer = answer.coerceAtMost(count)
            }
        }
    }
    println(answer)
}

