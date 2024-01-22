package week35

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val word = readLine()
    var length = 0
    val alpha = IntArray(26)
    var answer = n
    var start = 0
    var end = 0
    alpha[word[start] - 'a']++
    length++
    while (true) {
        end++
        if (end == word.length) break
        val pos = word[end] - 'a'
        alpha[pos]++
        if (alpha[pos] == 1) length++
        while (n < length) {
            val index = word[start] - 'a'
            alpha[index]--
            if (alpha[index] == 0) length--
            start++
        }
        answer = answer.coerceAtLeast(end - start + 1)
    }
    println(answer)
}