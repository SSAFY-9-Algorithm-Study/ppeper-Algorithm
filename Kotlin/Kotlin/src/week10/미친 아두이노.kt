package week10

import java.util.*
import kotlin.math.abs

data class Point(
    var x: Int,
    var y: Int,
)
private val dx = intArrayOf(0, 1, 1, 1, 0, 0, 0, -1, -1, -1)
private val dy = intArrayOf(0, -1, 0, 1, -1, 0, 1, -1, 0, 1)
private lateinit var graph: Array<CharArray>
private lateinit var visited: Array<IntArray>
private lateinit var arduinos: LinkedList<Point>
fun main() = with(System.`in`.bufferedReader()) {
    val (r, c) = readLine().split(" ").map { it.toInt() }
    var startX = 0
    var startY = 0
    graph = Array(r) { CharArray(c) }
    arduinos = LinkedList()
    for (i in 0 until r) {
        val input = readLine()
        for (j in input.toCharArray().indices) {
            graph[i][j] = input[j]
            if (input[j] == 'I') {
                startX = i
                startY = j
            } else if (input[j] == 'R') {
                arduinos.add(Point(i, j))
            }
        }
    }
    val move = readLine()
    var time = 0
    // 이동하기
    for (i in move.indices) {
        val dir = move[i] - '0'
        if (dir != 5) {
            graph[startX][startY] = '.'
            startX += dx[dir]
            startY += dy[dir]
            // 미친 아두이노 만나면 게임 종료
            if (graph[startX][startY] == 'R') {
                time = i + 1
                break
            }
        }
        graph[startX][startY] = 'I'
        if (!validMoveArduino(startX, startY)) {
            time = i + 1
            break
        }
        // 같은 아두이노 폭발
        checkVisited()
    }
    if (time == 0) {
        for (i in graph.indices) {
            for (j in graph[0].indices) {
                print("${graph[i][j]}")
            }
            println()
        }
    } else {
        println("kraj $time")
    }
}

fun checkVisited() {
    arduinos.clear()
    for (i in graph.indices) {
        for (j in graph[0].indices) {
            // 아두이노가 안겹친 애들만 살아 남음
            if (visited[i][j] == 1) {
                arduinos.add(Point(i, j))
            } else if (1 < visited[i][j]) {
                graph[i][j] = '.'
            }
        }
    }
}

private fun validMoveArduino(startX: Int, startY: Int): Boolean {
    visited = Array(graph.size) { IntArray(graph[0].size) }
    for (arduino in arduinos) {
        val dir = findDir(arduino, startX, startY)
        // 전에 위치 초기화
        if (visited[arduino.x][arduino.y] == 0) {
            graph[arduino.x][arduino.y] = '.'
        }
        arduino.x += dx[dir]
        arduino.y += dy[dir]
        if (graph[arduino.x][arduino.y] == 'I') {
            return false
        }
        // 아두이노 위치함
        visited[arduino.x][arduino.y]++
        graph[arduino.x][arduino.y] = 'R'
    }
    return true
}

private fun findDir(arduino: Point, startX: Int, startY: Int): Int {
    var dir = 0
    val (x, y) = arduino
    var diff = distance(x, y, startX, startY)
    for (i in 1 until dx.size) {
        val cx = x + dx[i]
        val cy = y + dy[i]
        if (cx < 0 || graph.size <= cx || cy < 0 || graph[0].size <= cy) continue

        val dist = distance(startX, startY, cx, cy)
        // 더 가까운 거리
        if (dist < diff) {
            diff = dist
            dir = i
        }
    }
    return dir
}

private fun distance(x: Int, y: Int, x1: Int, y1: Int): Int {
    return abs(x - x1) + abs(y - y1)
}
