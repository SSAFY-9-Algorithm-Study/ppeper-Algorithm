package week33

private lateinit var words: ArrayList<String>
private lateinit var prefix: ArrayList<String>
private var answer = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    words = ArrayList<String>().also { list ->
        repeat(n) { list.add(readLine()) }
    }
    prefix = ArrayList<String>().also { list ->
        repeat(m) { list.add(readLine()) }
    }
    words.sort()
    prefix.sort()
    for (pref in prefix) {
        var start = 0
        var end = n
        while (start < end) {
            val mid = (start + end) / 2
            if (words[mid].substring(0, pref.length) < pref) {
                start = mid + 1
            } else if (pref < words[mid].substring(0, pref.length)) {
                end = mid
            } else {
                answer++
                break
            }
        }
    }
    println(answer)
}