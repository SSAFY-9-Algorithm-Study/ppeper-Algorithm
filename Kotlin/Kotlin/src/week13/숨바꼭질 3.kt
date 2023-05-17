package week13

private val INF = 200000
fun main() = with(System.`in`.bufferedReader()) {
    val (N, K) = readLine().split(" ").map { it.toInt() }
    println(search(N, K))
}

private fun search(n: Int, k: Int): Int {
    // 0-1 bfs
    val deque = ArrayDeque<Int>()
    val move = IntArray(100001) { INF }
    move[n] = 0
    deque.addFirst(n)
    while (deque.isNotEmpty()) {
        val curr = deque.removeFirst()
        // 순간이동 -> 비용 0
        if (curr * 2 < move.size && move[curr * 2] == INF) {
            move[curr * 2] = move[curr]
            deque.addFirst(curr * 2)
        }
        // +1 or -1 -> 비용 1
        if (curr < 100000 && move[curr + 1] == INF) {
            move[curr + 1] = move[curr] + 1
            deque.addLast(curr + 1)
        }
        if (0 < curr && move[curr - 1] == INF) {
            move[curr - 1] = move[curr] + 1
            deque.addLast(curr - 1)
        }
    }
    return move[k]
}