package week44

private lateinit var list: MutableList<Int>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, l) = readLine().split(" ").map { it.toInt() }
    list = mutableListOf(l)
    if (n != 0) {
        val input = readLine().split(" ").map { it.toInt() }
        input.forEach {
            list.add(it)
        }
    }
    list.sort()
    var start = 1
    var end = l - 1
    var answer = l - 1

    while (start <= end) {
        val mid = (start + end) / 2
        if (m < count(mid)) {
            start = mid + 1
        } else {
            answer = answer.coerceAtMost(mid)
            end = mid - 1
        }
    }
    println(answer)
}

private fun count(length: Int): Int {
    var prev = 0
    var count = 0
    for (position in list) {
        var build = (position - prev) / length
        if ((position - prev) % length == 0) build -= 1
        count += build
        prev = position
    }
    return count
}