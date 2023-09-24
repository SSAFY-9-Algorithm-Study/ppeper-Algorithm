package week26

private lateinit var graph: Array<IntArray>
private const val INF = 987654321
private var answer = INF
fun main() = with(System.`in`.bufferedReader()){
    val (v, e) = readLine().split(" ").map { it.toInt() }
    graph = Array(v + 1) { IntArray(v + 1) { INF } }
    repeat(e) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        graph[a][b] = c
    }
    for (k in 1..v) {
        for (i in 1..v) {
            for (j in 1..v) {
                graph[i][j] = graph[i][j].coerceAtMost(graph[i][k] + graph[k][j])
            }
        }
    }
    // 사이클 확인
    for (i in 1..v) {
        for (j in 1..v) {
            if (graph[i][j] != INF && graph[j][i] != INF) {
                answer = answer.coerceAtMost(graph[i][j] + graph[j][i])            }
        }
    }
    if (answer == INF) println(-1) else println(answer)
}