package week43

private lateinit var house: List<Int>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, c) = readLine().split(" ").map { it.toInt() }
    house = List(n) { readLine().toInt() }.sorted()
    var length = 0
    var start = 1
    // 최대 가능한 공유기간의 거리
    var end = house[n - 1] - house[0]

    while (start <= end) {
        val distance = (start + end) / 2
        var selectedHouse = 1
        var prev = house[0]
        // 설치 가능한지 확인
        for (i in 1 until n) {
            if (distance <= house[i] - prev) {
                selectedHouse++
                prev = house[i]
            }
        }
        if (c <= selectedHouse) {
            length = length.coerceAtLeast(distance)
            start = distance + 1
        } else {
            end = distance - 1
        }
    }
    println(length)
}
