package week35

private lateinit var target: IntArray
private lateinit var list: IntArray
private var answer = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    target = IntArray(58)
    list = IntArray(58)
    readLine().forEach {
        target[it - 'A']++
    }
    val s = readLine()
    for (i in 0 until n) {
        list[s[i] - 'A']++
    }
    checkWord()
    for (i in 0 until m - n) {
        list[s[i] - 'A']--
        list[s[i + n] - 'A']++
        checkWord()
    }
    println(answer)
}

private fun checkWord() {
    if (target.contentEquals(list)) answer++
}
