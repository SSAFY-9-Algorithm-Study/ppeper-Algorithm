package week13

import java.util.*
import kotlin.collections.ArrayList

data class Machine(val x: Int, val y: Int, val dir: Int)
private var K = 0
private var R = 0
private var C = 0
private lateinit var map: Array<IntArray>
private lateinit var search: ArrayList<Pair<Int, Int>>
private lateinit var machines: ArrayList<Machine>
private lateinit var walls: Array<Array<Array<BooleanArray>>>
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private val move = arrayOf(
    arrayOf(),
    // 오른쪽
    arrayOf(arrayOf(0, 1), arrayOf(-1, 1), arrayOf(1, 1)),
    // 왼쪽
    arrayOf(arrayOf(0, -1), arrayOf(-1, -1), arrayOf(1, -1)),
    // 위쪽
    arrayOf(arrayOf(-1, 0), arrayOf(-1, 1), arrayOf(-1, -1)),
    // 아래
    arrayOf(arrayOf(1, 0), arrayOf(1, 1), arrayOf(1, -1))
)
fun main() = with(System.`in`.bufferedReader()) {
    val (r, c, k) = readLine().split(" ").map { it.toInt() }
    R = r
    C = c
    K = k
    map = Array(r) { IntArray(c) }
    search = ArrayList()
    machines = ArrayList()
    for (i in 0 until r) {
        val input = readLine().split(" ").map { it.toInt() }
        for (j in 0 until c) {
            if (input[j] == 0) continue
            if (input[j] == 5) {
                search.add(Pair(i, j))
            } else {
                machines.add(Machine(i, j, input[j]))
            }
        }
    }
    val w = readLine().toInt()
    walls = Array(r) { Array(c) { Array(r) { BooleanArray(c) } } }
    repeat(w) {
        val (x, y, dir) = readLine().split(" ").map { it.toInt() }
        val nx = x - 1
        val ny = y - 1
        if (dir == 0) {
            walls[nx][ny][nx - 1][ny] = true
            walls[nx - 1][ny][nx][ny] = true
        } else {
            walls[nx][ny][nx][ny + 1] = true
            walls[nx][ny + 1][nx][ny] = true
        }
    }
    var time = 0
    while (time <= 100) {
        time++
        start()
        if (isFinish()) break
    }
    println(time)
}

private fun isFinish(): Boolean {
    return search.none { map[it.first][it.second] < K }
}

private fun start() {
    // 온풍기 시작
    for (machine in machines) {
        wind(machine)
    }
    // 온도 조절
    controlTemperature()
    // 테두리
    controlSide()
}

private fun controlSide() {
    for (i in 0 until R) {
        if (0 < map[i][0]) map[i][0]--
        if (0 < map[i][C - 1]) map[i][C - 1]--
    }
    for (i in 1 until C - 1) {
        if (0 < map[0][i]) map[0][i]--
        if (0 < map[R - 1][i]) map[R - 1][i]--
    }
}

private fun controlTemperature() {
    val copy = Array(R) { IntArray(C) }
    for (i in 0 until R) {
        for (j in 0 until C) {
            for (k in 0 until 4) {
                val nx = i + dx[k]
                val ny = j + dy[k]
                if (checkBoundary(nx, ny)) continue
                if (walls[i][j][nx][ny]) continue
                // 높은곳에서 낮은 곳으로 이동
                val diff = map[i][j] - map[nx][ny]
                if (0 < diff) {
                    copy[i][j] -= diff / 4
                    copy[nx][ny] += diff / 4
                }
            }
        }
    }
    for (i in 0 until R) {
        for (j in 0 until C) {
            map[i][j] += copy[i][j]
        }
    }
}

private fun wind(machine: Machine) {
    var temp = 5
    val queue = LinkedList<Pair<Int, Int>>()
    val visited = Array(R) { BooleanArray(C) }
    val dir = machine.dir
    // 바람이 퍼지는 방향 시작 -> 5의 온도
    val x = machine.x + move[dir][0][0]
    val y = machine.y + move[dir][0][1]
    if (checkBoundary(x, y)) return
    queue.add(Pair(x, y))
    map[x][y] += temp--
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val (x, y) = queue.poll()
            for (j in 0 until 3) {
                val nx = x + move[dir][j][0]
                val ny = y + move[dir][j][1]
                if (checkBoundary(nx, ny)) continue
                if (visited[nx][ny]) continue
                if (checkWall(x, y, nx, ny, dir)) continue
                visited[nx][ny] = true
                map[nx][ny] += temp
                queue.add(Pair(nx, ny))
            }
        }
        temp--
        if (temp == 0) break
    }
}

private fun checkBoundary(nx: Int, ny: Int): Boolean {
    return nx < 0 || R <= nx || ny < 0 || C <= ny
}

// 벽 확인
private fun checkWall(x: Int, y: Int, nx: Int, ny: Int, dir: Int): Boolean {
    return if (2 < dir) {
        // 위 아래
        walls[x][y][x][ny] || walls[x][ny][nx][ny]
    } else {
        // 왼쪽, 오른쪽
        walls[x][y][nx][y] || walls[nx][y][nx][ny]
    }
}
