package week17

private lateinit var list: MutableList<Int>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    list = readLine().split(" ").map { it.toInt() }.toMutableList()
    val count = readLine().toInt()
    sortList(0, count)
    println(list.joinToString(" "))
}

private fun sortList(start: Int, count: Int) {
    if (count <= 0 || start == list.size - 1) return
    var max = -1
    var maxIndex = start
    val range = if (start + count < list.size) start + count else list.size - 1
    for (i in start..range) {
        if (max < list[i]) {
            max = list[i]
            maxIndex = i
        }
    }
    if (maxIndex != start) {
        var temp = maxIndex
        while (start < temp) {
            list[temp] = list[temp - 1].also {
                list[temp - 1] = list[temp]
            }
            temp--
        }
    }
    sortList(start + 1, count - (maxIndex - start))
}
