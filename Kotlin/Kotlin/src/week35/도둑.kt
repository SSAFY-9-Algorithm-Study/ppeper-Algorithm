package week35

private var answer = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val test = readLine().toInt()
    repeat(test) {
        val (n, m, k) = readLine().split(" ").map { it.toInt() }
        val list = readLine().split(" ").map { it.toInt() }
        var sum = list.slice(0 until m).sum()
        var count = 0
        for (i in 0 until n) {
            val end = (i + m) % n
            sum -= list[i]
            sum += list[end]
            if (sum < k) count++
            if (n == m) break
        }
        answer.append("$count\n")
    }
    println(answer)
}