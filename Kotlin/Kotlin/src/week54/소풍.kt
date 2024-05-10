package week54

import java.util.LinkedList

private var K = 0
private var N = 0
private var isFriend = false
private var answer = StringBuilder()
private lateinit var list: IntArray
private lateinit var visited: BooleanArray
private lateinit var relation: Array<BooleanArray>
private lateinit var graph: Array<LinkedList<Int>>
fun main() = with(System.`in`.bufferedReader()) {
    val (k, n, f) = readLine().split(" ").map { it.toInt() }
    N = n
    K = k
    list = IntArray(k)
    visited = BooleanArray(n + 1)
    graph = Array(n + 1) { LinkedList() }
    relation = Array(n + 1) { BooleanArray(n + 1) }
    repeat(f) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        relation[a][b] = true
        relation[b][a] = true
        graph[a].add(b)
        graph[b].add(a)
    }
    findFriends(1, 0)
    if (isFriend) println(answer) else println(-1)
}

private fun checkFriends(): Boolean {
    for (i in list.indices) {
        for (j in i + 1 until list.size) {
            if (!relation[list[i]][list[j]]) return false
        }
    }
    return true
}

private fun findFriends(idx: Int, pick: Int) {
    if (isFriend) return
    if (pick == K) {
        if (checkFriends()) {
            isFriend = true
            list.forEach {
                answer.append("$it\n")
            }
        }
        return
    }
    if (N < idx) return
    if (graph[idx].size < K - 1) {
        findFriends(idx + 1, pick)
    } else {
        list[pick] = idx
        findFriends(idx + 1, pick + 1)
        findFriends(idx + 1, pick)
    }
}
