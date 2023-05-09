package week12

private lateinit var parent: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    parent = IntArray(n + 1) { it }
    repeat(n - 2) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        union(a, b)
    }
    parent.map {
        find(it)
    }.filter { it != 0 }.toHashSet().forEach {
        print("$it ")
    }
}

private fun union(a: Int, b: Int) {
    val pa = find(a)
    val pb = find(b)
    if (pa < pb) parent[pb] = pa
    else parent[pa] = pb
}

private fun find(node: Int): Int {
    if (parent[node] != node) {
        parent[node] = find(parent[node])
    }
    return parent[node]
}
