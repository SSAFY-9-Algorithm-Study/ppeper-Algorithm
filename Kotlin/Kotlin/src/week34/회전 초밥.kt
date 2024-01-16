package week34

private lateinit var list: IntArray
private lateinit var piece: IntArray
private var count = 0
private var answer = 0
private var C = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, d, k, c) = readLine().split(" ").map { it.toInt() }
    C = c
    list = IntArray(n) { readLine().toInt() }
    piece = IntArray(d + 1)
    for (i in 0 until k) {
        if (piece[list[i]] == 0) {
            count++
        }
        piece[list[i]]++
    }
    // 연속된 초밥의 개수 구하기
    update()
    var lastIndex = k
    // k의 크기만큼 앞에 초밥 빼고 뒤에 초밥 더하기
    for (i in list.indices) {
        if (piece[list[i]] == 1) count--
        piece[list[i]]--
        if (piece[list[lastIndex]] == 0) count++
        piece[list[lastIndex]]++
        lastIndex = (lastIndex + 1) % n
        // 최대 개수로 업데이트
        update()
    }
    println(answer)
}

private fun update() {
    answer = if (piece[C] == 0) {
        answer.coerceAtLeast(count + 1)
    } else {
        answer.coerceAtLeast(count)
    }
}
