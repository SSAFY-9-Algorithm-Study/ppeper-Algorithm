package week41

private lateinit var graph: List<Int>
private lateinit var visited: BooleanArray
private lateinit var pick: IntArray
private var n = 0
private var answer = 0
fun main() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    graph = readLine().split(" ").map { it.toInt() }
    pick = IntArray(n)
    visited = BooleanArray(n)
    val max =  graph.max()
    if (50 < max) {
        println(0)
    } else if (max == 50) {
        println(1)
    } else {
        permutation(0)
        println(answer)
    }
}

private fun permutation(r: Int) {
    if (r == n) {
        checkLine()
        return
    }
    for (i in 0 until n) {
        if (!visited[i]) {
            visited[i] = true
            pick[r] = graph[i]
            permutation(r + 1)
            visited[i] = false
        }
    }
}

private fun checkLine() {
    var count = 0
    for (i in pick.indices) {
        var sum = pick.slice(0..i).sum()
        for (j in 0 until i) {
            sum -= pick[j]
            if (sum == 50) {
                count++
                break
            }
            if (sum < 50) break
        }
    }
    answer = answer.coerceAtLeast(count)
}
