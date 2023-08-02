package week20

private var answer = ""
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    if (n == 1) println("m")
    else if (n <= 3) println("o")
    else {
        mooGame(n)
        println(answer)
    }
}

private fun mooGame(n: Int) {
    var length = 3
    var k = 0
    while (length <= n) {
        length = length * 2 + k++ + 4
    }
    // m o..o 길이
    val midLength = k + 3
    // 중간에 추가되는 moo보다 뒤
    val midOver = length - (length - midLength) / 2
    if (midOver <= n) {
        mooGame(n - midOver)
    } else if (n - 1 == (length - midLength) / 2) {
        answer = "m"
    } else {
        answer = "o"
    }
}
