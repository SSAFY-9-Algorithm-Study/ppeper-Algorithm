package week48

private lateinit var target: String
private lateinit var answer: HashSet<String>
fun main() = with(System.`in`.bufferedReader()) {
    target = readLine()
    answer = HashSet()
    // 양 옆 붙여가며 숫자 만들기
    for (i in target.indices) {
        makeWord(i, i, target[i].toString(), target[i].toString())
    }
    println(answer.size)
}

private fun makeWord(start: Int, end: Int, current: String, route: String) {
    if (start == 0 && end == target.length - 1) {
        answer.add(route)
        return
    }
    if (0 < start) {
        makeWord(start - 1, end, target[start - 1] + current, "$route ${target[start - 1] + current}")
    }
    if (end < target.length - 1) {
        makeWord(start, end + 1, current + target[end + 1], "$route ${current + target[end + 1]}")
    }
}
