package week33

private lateinit var list: List<Int>
private var max = 0
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    list = readLine().split(" ").map { it.toInt() }.sorted()
    max = readLine().toInt()
    var start = 0
    var end = list[n - 1]
    while (start <= end) {
        val upper = (start + end) / 2
        val sum = calculateSum(upper)
        if (0 <= sum) {
            start = upper + 1
        } else {
            end = upper - 1
        }
    }
    println(end)
}

private fun calculateSum(upper: Int): Int {
    var temp = max
    for (value in list) {
        temp -= if (upper < value) upper else value
    }
    return temp
}
