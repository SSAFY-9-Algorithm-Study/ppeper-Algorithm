package week13

private lateinit var depth: ArrayList<Int>
private lateinit var list: ArrayList<Pair<Int, Int>>
private lateinit var holes: ArrayList<Pair<Int, Int>>
private lateinit var out: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    depth = ArrayList()
    list = ArrayList()
    holes = ArrayList()
    readLine()
    repeat(n - 1) {
        val (y, x) = readLine().split(" ").map { it.toInt() }
        list.add(Pair(x, y))
    }
    val k = readLine().toInt()
    repeat(k) {
        val (y, x) = readLine().split(" ").map { it.toInt() }
        holes.add(Pair(x, y))
    }
    // 물의 깊이 확인하기
    for (i in 0 until list.size - 1) {
        val (x1, y1) = list[i]
        val (x2, y2) = list[i + 1]
        val width = y2 - y1
        for (j in 0 until width) {
            depth.add(x1)
        }
    }
    // 빠져나가는 물의 양
    out = IntArray(depth.size)

    for (hole in holes) {
        var height = hole.first
        val start = hole.second
        // 왼쪽
        for (i in start downTo 0) {
            if (depth[i] < height) {
                height = depth[i]
            }
            out[i] = out[i].coerceAtLeast(height)
        }
        height = hole.first
        // 오른쪽
        for (j in start until out.size) {
            if (depth[j] < height) {
                height = depth[j]
            }
            out[j] = out[j].coerceAtLeast(height)
        }
    }
    var answer = 0
    depth.forEachIndexed { index, depth ->
        answer += depth - out[index]
    }
    println(answer)
}