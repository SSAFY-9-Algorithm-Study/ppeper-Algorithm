package week40

private lateinit var point: List<Int>
private var from = 0
private var to = 0
private var answer = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (m, n, l) = readLine().split(" ").map { it.toInt() }
    point = readLine().split(" ").map { it.toInt() }.sorted()
    for (i in 0 until n) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        // 이미
        if (l < y) continue
        from = x - (l - y)
        to = x + (l - y)
        if (checkPoint(0, m - 1)) answer++
    }
    println(answer)
}

private fun checkPoint(start: Int, end: Int): Boolean {
    if (end < start) return false
    val mid = (start + end) / 2
    if (point[mid] in from..to) return true
    return if (point[mid] < from) {
        checkPoint(mid + 1, end)
    } else {
        checkPoint(start, mid - 1)
    }
}
