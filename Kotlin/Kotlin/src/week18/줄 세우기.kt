package week18

import java.lang.StringBuilder
import java.util.LinkedList

private lateinit var graph: Array<LinkedList<Int>>
private lateinit var inDegree: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { LinkedList() }
    inDegree = IntArray(n + 1)
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        inDegree[b]++
    }
    val queue = LinkedList<Int>()
    for (i in 1 until inDegree.size) {
        if (inDegree[i] == 0) queue.add(i)
    }
    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        sb.append("$curr ")
        for (nextStudent in graph[curr]) {
            if (--inDegree[nextStudent] == 0) {
                queue.add(nextStudent)
            }
        }
    }
    println(sb)
}