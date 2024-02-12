package week39

private lateinit var word: Array<String>
private lateinit var match: HashMap<Char, Char>
private var answer = 0
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    word = Array(n) { readLine() }
    for (i in 0 until word.size - 1) {
        for (j in i + 1 until word.size) {
            checkWord(word[i], word[j])
        }
    }
    println(answer)
}

private fun checkWord(source: String, target: String) {
    // 알파벳 확인
    match = HashMap()
    for (i in source.indices) {
        val mapping = match[source[i]]
        mapping?.let {
            if (it != target[i]) return
        } ?: if (match.values.contains(target[i])) return
        else match[source[i]] = target[i]
    }
    answer++
}
