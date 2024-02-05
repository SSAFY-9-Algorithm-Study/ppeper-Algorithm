package week37

private var sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val list = Array(n) { readLine() }
    list.forEach {
        sb.append("${checkWord(0, it.length - 1, false, it)}\n")
    }
    println(sb)
}

private fun checkWord(start: Int, end: Int, isDeleted: Boolean, word: String): Int {
    while (start < end) {
        if (word[start] == word[end]) {
            return checkWord(start + 1, end - 1, isDeleted, word)
        } else {
            if (isDeleted) return 2
            if (checkWord(start + 1, end, isDeleted = true, word) == 0 ||
                checkWord(start, end - 1, isDeleted = true, word) == 0) {
                return 1
            }
            return 2
        }
    }
    // 다 성공하였다면 회문
    return 0
}
