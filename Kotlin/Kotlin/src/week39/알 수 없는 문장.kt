package week39

private lateinit var letter: String
private lateinit var words: List<String>
private lateinit var dp: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    letter = readLine()
    val n = readLine().toInt()
    words = List(n) { readLine() }
    dp = IntArray(letter.length) { 3000 }
    println(checkLetter(0))
}

private fun checkLetter(n: Int): Int {
    if (n == letter.length) {
        return if (dp[letter.length - 1] == 3000) -1 else dp[letter.length - 1]
    }
    // 부분 문자열 구하기
    val subList = letter.slice(0..n).subList()
    for (i in subList.indices) {
        // 만들 수 있는 문자인지 확인
        for (word in words) {
            if (!subList[i].compare(word)) continue
            val count = subList[i].getDiffCount(word)
            if (i == 0) {
                dp[n] = dp[n].coerceAtMost(count)
            } else {
                dp[n] = dp[n].coerceAtMost(dp[i - 1] + count)
            }
        }
    }
    return checkLetter(n + 1)
}

private fun String.subList(): List<String> {
    return List(this.length) {
        this.slice(it until length)
    }
}

private fun String.compare(target: String): Boolean {
    if (this.length != target.length) return false
    return this.toList().sorted() == target.toList().sorted()
}

private fun String.getDiffCount(word: String): Int {
    var count = 0
    for (i in this.indices) {
        if (this[i] != word[i]) count++
    }
    return count
}
