package week38

private var answer = 0
private lateinit var building: List<Int>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    building = readLine().split(" ").map { it.toInt() }
    for (i in building.indices) {
        checkBuilding(i)
    }
    println(answer)
}

private fun checkBuilding(start: Int) {
    var count = 0
    var temp = 0.0
    for (i in start - 1 downTo 0) {
        val inclination = ((building[start] - building[i]).toDouble() / (start - i))
        // 기울기가 감소해야함
        if (i == start - 1 || inclination < temp) {
            count++
            temp = inclination
        }
    }
    for (j in start + 1 until building.size) {
        val inclination = ((building[start] - building[j]).toDouble() / (start - j))
        // 기울기가 증가해야함
        if (j == start + 1 || temp < inclination) {
            count++
            temp = inclination
        }
    }
    answer = answer.coerceAtLeast(count)
}

