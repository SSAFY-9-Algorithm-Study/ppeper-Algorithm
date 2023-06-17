package week15

private lateinit var parent: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    parent = IntArray(n + 1) { it }
    for (i in 1..n) {
        val input = readLine().split(" ").map { it.toInt() }
        for (j in input.indices) {
            if (input[j] == 1) {
                if (find(i) != find(j + 1)) {
                    union(i, j + 1)
                }
            }
        }
    }
    val input = readLine().split(" ").map { it.toInt() }
    var check = true
    val temp = find(input[0])
    for (i in 1 until input.size) {
        if (temp != find(input[i])) {
            check = false
            break
        }
    }
    if (check) println("YES") else println("NO")
}

private fun find(node: Int): Int {
    if (parent[node] != node) {
        parent[node] = find(parent[node])
    }
    return parent[node]
}

private fun union(a: Int, b: Int) {
    val parentA = find(a)
    val parentB = find(b)
    if (parentA < parentB) parent[parentB] = parentA
    else parent[parentA] = parentB
}