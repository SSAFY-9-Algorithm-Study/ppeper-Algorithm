package week17

data class Flower(
    val startDay: Int,
    val endDay: Int
)
private val DAY = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
private val RANGE_START = calculateDay(3, 1)
private val RANGE_END = calculateDay(11, 30)
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val flowers = Array(n) { Flower(0, 0) }
    repeat(n) {
        val (sm, sd, em, ed) = readLine().split(" ").map { it.toInt() }
        flowers[it] = Flower(calculateDay(sm, sd), calculateDay(em, ed))
    }
    flowers.sortWith(
        compareBy<Flower> { it.startDay }
            .thenByDescending { it.endDay })

    var start = RANGE_START
    var end = 0
    var count = 0
    var i = 0
    while (start <= RANGE_END) {
        // 가장 범위안에 들면서 길게 피는 꽃
        var startIdx = -1
        for (idx in i until flowers.size) {
            if (start < flowers[idx].startDay) break
            if (end < flowers[idx].endDay) {
                startIdx = idx
                end = flowers[idx].endDay
            }
        }
        // startIdx = -1 -> 존재하지않음
        if (startIdx == -1)  break
        i = startIdx
        start = end
        // 범위 안에 꽃이 피는 구간이 끝남
        count++
    }
    if (RANGE_END < end) println(count) else println(0)
    println(calculateDay(1, 20))
}

private fun calculateDay(month: Int, day: Int): Int {
    var startDay = day
    for (i in 1 until month) {
        startDay += DAY[i]
    }
    return startDay
}
