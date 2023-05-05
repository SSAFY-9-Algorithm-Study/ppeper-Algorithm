package week12

import java.util.*

private val dx = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
private val dy = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
private lateinit var map: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private lateinit var sands: LinkedList<Pair<Int, Int>>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    map = Array(n) { CharArray(m) }
    visited = Array(n) { BooleanArray(m) }
    sands = LinkedList()
    repeat(n) {
        map[it] = readLine().toCharArray()
    }
    var time = 0
    initSand()
    while (sands.isNotEmpty()) {
        destroySand()
        time++
    }
    println(time)
}

private fun destroySand() {
    val size = sands.size
    for (i in 0 until size) {
        val (x, y) = sands[i]
        map[x][y] = '.'
    }

    repeat(size) {
        val (x, y) = sands.poll()
        // 주변에 방문처리 안된애들중 가능성 있는 sand 확인
        for (i in 0 until 8) {
            val cx = x + dx[i]
            val cy = y + dy[i]
            if (cx < 0 || map.size <= cx || cy < 0 || map[0].size <= cy) continue

            if (map[cx][cy] != '.' && !visited[cx][cy]) {
                if (checkSandDestroy(cx, cy)) {
                    visited[cx][cy] = true
                    sands.add(Pair(cx, cy))
                }
            }
        }
    }
}

private fun initSand() {
    for (i in map.indices) {
        for (j in map[0].indices) {
            if (map[i][j] == '9') continue
            if (map[i][j] != '.') {
                if (checkSandDestroy(i, j)) {
                    visited[i][j] = true
                    sands.add(Pair(i, j))
                }
            }
        }
    }
}

private fun checkSandDestroy(i: Int, j: Int): Boolean {
    var count = 0
    for (k in 0 until 8) {
        val cx = i + dx[k]
        val cy = j + dy[k]
        if (cx < 0 || map.size <= cx || cy < 0 || map[0].size <= cy) continue
        if (map[cx][cy] == '.') count++
    }
    return map[i][j] - '0' <= count
}