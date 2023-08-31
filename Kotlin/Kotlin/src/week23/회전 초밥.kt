package week23

private lateinit var list: IntArray
private lateinit var pieces: IntArray
private var answer = 0
private var count = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, d, k, c) = readLine().split(" ").map { it.toInt() }
    list = IntArray(n) { readLine().toInt() }
    // 초밥들
    pieces = IntArray(d + 1)
    for (i in 0 until k) {
        if (pieces[list[i]] == 0) {
            count++
        }
        pieces[list[i]]++
    }
    answer = if (pieces[c] == 0) {
        answer.coerceAtLeast(count + 1)
    } else {
        answer.coerceAtLeast(count)
    }
    var lastIndex = k
    for (i in list.indices) {
        if (pieces[list[i]] == 1) {
            count--
        }
        pieces[list[i]]--
        if (pieces[list[lastIndex]] == 0) {
            count++
        }
        pieces[list[lastIndex]]++
        lastIndex = (lastIndex + 1) % n
        answer = if (pieces[c] == 0) {
            answer.coerceAtLeast(count + 1)
        } else {
            answer.coerceAtLeast(count)
        }
    }
    println(answer)
}