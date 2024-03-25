package week46

private lateinit var answer: ArrayList<String>
private lateinit var alphabet: HashMap<Char, Int>
fun main() = with(System.`in`.bufferedReader()) {
    answer = ArrayList()
    val n = readLine().toInt()
    repeat(n) {
        val input = readLine()
        alphabet = HashMap()
        input.forEach {
            alphabet[it] = alphabet.getOrDefault(it, 0) + 1
        }
        val array = input.toCharArray().distinct().sorted()
        makeWord(array, "", input.length)
    }
    answer.forEach {
        println(it)
    }
}

private fun makeWord(list: List<Char>, s: String, length: Int) {
    if (s.length == length) {
        answer.add(s)
        return
    }
    for (c in list) {
        if (alphabet[c] == 0) continue
        alphabet[c] = alphabet[c]!! - 1
        makeWord(list, s + c, length)
        alphabet[c] = alphabet[c]!! + 1
    }
}
