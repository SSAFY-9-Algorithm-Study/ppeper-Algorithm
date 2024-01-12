package week34

fun main() = with(System.`in`.bufferedReader()) {
    var maxLength = 0
    // 분기를 타는 길이
    var quarter = 0
    val n = readLine().toInt()
    val cnt = readLine().split(" ").map { it.toInt() }
    for (count in cnt) {
        maxLength++
        if (count == 1) {
            // 분기를 탓을때의 길이와 비교 -> 1개 노드 이후에 다시 분기를 탔을때가 클 수가 있음
            maxLength = maxLength.coerceAtLeast(quarter + 1)
            quarter = 0
        } else {
            quarter += 2
        }
    }
    println(maxOf(maxLength, quarter))
}