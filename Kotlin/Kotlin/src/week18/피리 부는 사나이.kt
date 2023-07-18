package week18

// 위, 아래, 왼쪽, 오른쪽
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var graph: Array<CharArray>
// 하나의 위치: ex (1, 2) -> 1 * m + 2
private lateinit var parent: IntArray
private var N = 0
private var M = 0

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    N = n
    M = m
    graph = Array(n) { CharArray(m) }
    parent = IntArray(n * m) { it }
    repeat(n) { graph[it] = readLine().toCharArray() }
    for (i in 0 until n) {
        for (j in 0 until m) {
            dfs(i, j, graph[i][j])
        }
    }
    println(parent.distinct().count())
}
private fun dfs(i: Int, j: Int, c: Char) {
    val number = i * M + j
    val dir = c.toDirection()
    val nx = i + dx[dir]
    val ny = j + dy[dir]
    if (nx !in 0 until N || ny !in 0 until M) return
    val nextNumber = nx * M + ny
    // 사이클이 형성되면 리턴
    if (find(number) == find(nextNumber)) return
    union(number, nextNumber)
    dfs(nx, ny, graph[nx][ny])
}

private fun find(number: Int): Int {
    if (parent[number] != number) {
        parent[number] = find(parent[number])
    }
    return parent[number]
}

private fun union(a: Int, b: Int) {
    val parentA = find(a)
    val parentB = find(b)
    if (parentA < parentB) parent[parentB] = parentA
    else parent[parentA] = parentB
}

private fun Char.toDirection(): Int {
    return when (this) {
        'U' -> { 0 }
        'D' -> { 1 }
        'L' -> { 2 }
        'R' -> { 3 }
        else -> { -1 }
    }
}