package week50

private lateinit var list: Array<String>
private var N = 0
private const val frontIdx = 2
private const val backIdx = 6
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    N = n
    list = Array(n) { readLine() }
    val k = readLine().toInt()
    repeat(k) {
        val (num, dir) = readLine().split(" ").map { it.toInt() }
        rotateGear(num - 1, dir)
    }
    println(list.count { it[0] == '1' })
}

private fun rotateGear(num: Int, dir: Int) {
    var toLeft = dir
    var toRight = dir
    val candidate = IntArray(N).apply {
        this[num] = dir
    }
    // 뒤에 모든 톱니 확인
    for (i in num downTo 1) {
        if (list[i - 1][frontIdx] == list[i][backIdx]) break
        toLeft = -toLeft
        candidate[i - 1] = toLeft
    }
    // 앞에 톱니바퀴 확인
    for (j in num until N - 1) {
        if (list[j][frontIdx] == list[j + 1][backIdx]) break
        toRight = -toRight
        candidate[j + 1] = toRight
    }
    for ((idx, d) in candidate.withIndex()) {
        if (d == 0) continue
        val gear = list[idx]
        // 시계 방향 -> 맨뒤를 앞으로
        if (d == 1) {
            val back = gear.last()
            list[idx] = back.plus(gear).take(8)
        } else {
            val front = gear.first()
            list[idx] = gear.drop(1).plus(front)
        }
    }
}