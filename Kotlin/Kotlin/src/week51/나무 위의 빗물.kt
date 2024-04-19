package week51

import java.util.LinkedList

private lateinit var tree: Array<LinkedList<Int>>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, w) = readLine().split(" ").map { it.toInt() }
    tree = Array(n + 1) { LinkedList() }
    repeat(n - 1) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        tree[a].add(b)
        tree[b].add(a)
    }
    var leafs = tree.count { it.size == 1 }
    if (tree[1].size == 1) leafs -= 1
    println(w / leafs.toDouble())
}

