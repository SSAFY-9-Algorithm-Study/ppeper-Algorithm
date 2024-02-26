package week42

private lateinit var list: List<Int>
private lateinit var numbers: HashSet<Int>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var answer = 0L
    numbers = HashSet()
    list = readLine().split(" ").map { it.toInt() }
    var end = 0
    for (i in list.indices) {
        while (end < n && !numbers.contains(list[end])) {
            numbers.add(list[end++])
        }
        numbers.remove(list[i])
        answer += end - i
    }
    println(answer)
}