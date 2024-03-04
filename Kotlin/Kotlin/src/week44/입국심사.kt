package week44

private lateinit var times: List<Int>
private var M = 0L
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toLong() }
    M = m
    times = List(n.toInt()) { readLine().toInt() }.sorted()
    var startTime = 1L
    var endTime = times[times.size - 1] * m
    var answer = 0L

    while (startTime <= endTime) {
        val mid = (startTime + endTime) / 2
        if (count(mid)) {
            answer = mid
            endTime = mid - 1
        } else {
            startTime = mid + 1
        }
    }
    println(answer)
}

private fun count(endTime: Long): Boolean {
    var check = 0L
    for (time in times) {
        check += (endTime / time)
        if (M <= check) return true
    }
    return false
}