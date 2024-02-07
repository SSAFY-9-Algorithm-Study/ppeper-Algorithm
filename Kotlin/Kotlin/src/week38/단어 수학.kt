package week38

import kotlin.math.pow

private lateinit var alphabet: HashMap<Char, Int>
private lateinit var wordToNumber: MutableList<Int>
private lateinit var words: Array<String>
private var number = 9
private var answer = 0
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    alphabet = HashMap()
    wordToNumber = mutableListOf()
    words = Array(n) { readLine() }
    words.forEach { word ->
        word.calculateWeight()
    }
    alphabet.keys.sortedByDescending { alphabet[it] }.map {
        alphabet[it] = number--
    }
    getNumber()
    println(answer)
}

private fun getNumber() {
    words.forEach {
        val number = it.map {
            alphabet[it]
        }
        answer += number.joinToString("").toInt()
    }
}

private fun String.calculateWeight() {
    this.forEachIndexed { index, c ->
        alphabet[c] = alphabet.getOrDefault(c, 0) + 10.0.pow((this.length - index - 1).toDouble()).toInt()
    }
}
