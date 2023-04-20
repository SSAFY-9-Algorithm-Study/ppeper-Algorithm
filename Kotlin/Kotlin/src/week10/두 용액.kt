package week10

import kotlin.math.abs

private var answer = Int.MAX_VALUE
private lateinit var result: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    result = IntArray(2)
    val list = readLine().split(" ").map { it.toInt() }.sorted()
    search(0, n - 1, list)
    println(result[0].toString() + " " + result[1])
}

// 투 포인터의 값의 합했을때
// 1. 음수면 앞쪽을 1 증가
// 2. 양수면 뒤쪽을 -1 감소
private fun search(start: Int, end: Int, list: List<Int>) {
    if (start == end) {
        return
    }
    val diff = list[start] + list[end]
    if (diff < 0) {
        if (abs(diff) < answer) {
            answer = abs(diff)
            result[0] = list[start]
            result[1] = list[end]
        }
        search(start + 1, end, list)
    } else {
        if (abs(diff) < answer) {
            answer = abs(diff)
            result[0] = list[start]
            result[1] = list[end]
        }
        search(start, end - 1, list)
    }
}