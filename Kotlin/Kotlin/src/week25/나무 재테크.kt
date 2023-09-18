package week25

import java.util.PriorityQueue

data class Tree(
    val x: Int,
    val y: Int,
    var age: Int
) : Comparable<Tree> {
    override fun compareTo(other: Tree) = this.age - other.age
}
private const val INIT_FOOD = 5
// 모든 방향
private val dx = intArrayOf(1, 1, 1, 0, 0, -1, -1, -1)
private val dy = intArrayOf(0, 1, -1, 1, -1, 0, 1, -1)
private lateinit var amount: Array<IntArray>
private lateinit var tree: PriorityQueue<Tree>
private lateinit var food: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    // k년이 지난 후 살아남은 나무의 수를 출력한다.
    val (n, m, k) = readLine().split(" ").map { it.toInt() }
    tree = PriorityQueue()
    food = Array(n) { IntArray(n) { INIT_FOOD } }
    // 겨울에 추가될 양분
    amount = Array(n) { IntArray(n) }
    // 겨울에 추가될 양분
    for (i in 0 until n) {
        amount[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    // 처음 나무들
    for (j in 0 until m) {
        val (x, y, age) = readLine().split(" ").map { it.toInt() }
        tree.offer(Tree(x - 1, y - 1, age))
    }
    yearLater(k)
    println(tree.size)
}

private fun yearLater(k: Int) {
    var year = k
    while (0 < year) {
        // 봄 -> 나무가 자신의 나이만큼 양분을 먹고, 나이 1 증가
        // 나이가 어린 나무부터 양분을 먹음
        // 땅아 양분이 부족하면 죽는다
        val dead = treeGetOlder()
        // 여름 -> 봄에 죽은 나무가 양분으로 변한다
        // 죽은 나무마다 나이를 2로 나눈 값이 해당 위치에 양분으로 추가
        deadTreeChange(dead)
        // 가을 -> 나무가 번식
        // 번식하는 나무는 나이가 5의 배수 인접한 8개의 칸에 나이가 1인 나무가 생김
        breeding()
        // 겨울
        addAmount()
        year--
    }
}

private fun addAmount() {
    for (i in food.indices) {
        for (j in food.indices) {
            food[i][j] += amount[i][j]
        }
    }
}

private fun breeding() {
    val copy = PriorityQueue(tree)
    while (tree.isNotEmpty()) {
        val (x, y, age) = tree.poll()
        if (age % 5 != 0) continue
        for (i in 0 until 8) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx !in food.indices || ny !in food.indices) continue
            copy.offer(Tree(nx, ny, 1))
        }
    }
    tree = copy
}

private fun deadTreeChange(dead: ArrayList<Tree>) {
    for ((x, y, age) in dead) {
        food[x][y] += age / 2
    }
}

private fun treeGetOlder(): ArrayList<Tree> {
    val dead = ArrayList<Tree>()
    val older = PriorityQueue<Tree>()
    while (tree.isNotEmpty()) {
        val curr = tree.poll()
        // 양분을 먹을 수 있어야 한다.
        if (curr.age <= food[curr.x][curr.y]) {
            food[curr.x][curr.y] -= curr.age
            older.add(Tree(curr.x, curr.y, curr.age + 1))
        } else {
            dead.add(curr)
        }
    }
    tree = older
    return dead
}
