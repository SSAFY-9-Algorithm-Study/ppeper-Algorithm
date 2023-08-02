package week20


const val MAX = 987654321
private lateinit var board: Array<IntArray>
private lateinit var parent: IntArray
private var sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    board = Array(n + 1) { IntArray(n + 1) }
    for (i in 1..n) {
        for (j in 1..n) {
            if (i == j) continue
            board[i][j] = MAX
        }
    }
    parent = IntArray(n + 1) { it }
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        board[a][b] = 1
        board[b][a] = 1
        if (findParent(a) != findParent(b)) {
            union(a, b)
        }
    }
    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                board[i][j] = board[i][j].coerceAtMost(board[i][k] + board[k][j])
            }
        }
    }
    val unionList = parent.map { findParent(it) }.toList()
    // 크기
    sb.append("${unionList.distinct().count() - 1}\n")
    // 그룹 리스트
    val groupList = unionList.indices
        .groupBy { unionList[it] }
        .filterNot { it.key == 0 }
        .values
    val list = ArrayList<Int>()
    for (group in groupList) {
        var minDistMember = 0
        var maxValue = MAX
        for (i in group) {
            var dist = 0
            for (j in 1..n) {
                if (board[i][j] == MAX) continue
                dist = dist.coerceAtLeast(board[i][j])
            }
            // 더 거리가 짧은 인원으로 지정
            if (dist < maxValue) {
                minDistMember = i
                maxValue = dist
            }
        }
        list.add(minDistMember)
    }
    list.sorted().forEach {
        sb.append("$it\n")
    }
    println(sb)
}

private fun union(a: Int, b: Int) {
    val parentA = findParent(a)
    val parentB = findParent(b)
    if (parentA < parentB) parent[parentB] = parentA
    else parent[parentA] = parentB
}

private fun findParent(node: Int): Int {
    if (parent[node] != node) {
        parent[node] = findParent(parent[node])
    }
    return parent[node]
}