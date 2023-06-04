package week14

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var answer = 0
    val numbers = readLine().split(" ").map { it.toInt() }.sorted()
    for (i in numbers.indices) {
        val number = numbers[i]
        var start = 0
        var end = n - 1

        while (start < end) {
            val sum = numbers[start] + numbers[end]
            if (start == i) {
                start++
                continue
            }
            if (end == i) {
                end--
                continue
            }
            if (sum == number) {
                answer++
                break
            } else if (sum < number) {
                start++
            } else {
                end--
            }
        }
    }
    println(answer)
}