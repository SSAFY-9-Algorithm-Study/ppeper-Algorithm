package week52

fun main() = with(System.`in`.bufferedReader()) {
    val (x, y) = readLine().split(" ").map { it.toLong() }
    val diff = y - x
    var n = 0L
    while (n * n <= diff) { // 1, 2..n..2, 1 최대의 수
        n++
    }
    n -= 1
    var answer = 1 + (n - 1) * 2
    var remain = diff - n * n
    while (0 < remain) {
        answer += (remain / n)
        remain %= n
        n--
    }
    if (diff == 0L) println(0) else println(answer)
}

