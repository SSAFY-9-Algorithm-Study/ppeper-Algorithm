package week52

import kotlin.math.max
import kotlin.math.min

private lateinit var number: IntArray
private lateinit var minTree: IntArray
private lateinit var maxTree: IntArray
private val answer = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    number = IntArray(n + 1)
    repeat(n) {
        number[it + 1] = readLine().toInt()
    }

    minTree = IntArray(n * 4)
    maxTree = IntArray(n * 4)
    init(1, n, 1)
    repeat(m) {
        val (start, end) = readLine().split(" ").map { it.toInt() }
        answer.append("${findMin(1, n, 1, start, end)} ${findMax(1, n, 1, start, end)}\n")
    }
    println(answer)
}

private fun init(start: Int, end: Int, node: Int) {
    if (start == end) {
        minTree[node] = number[start]
        maxTree[node] = number[start]
        return
    }
    val mid = (start + end) / 2
    init(start, mid, node * 2)
    init(mid + 1, end,  node * 2 + 1)
    maxTree[node] = max(maxTree[node * 2], maxTree[node * 2 + 1])
    minTree[node] = min(minTree[node * 2], minTree[node * 2 + 1])
}

private fun findMin(start: Int, end: Int, node: Int, left: Int, right: Int): Int {
    if (right < start || end < left) return Int.MAX_VALUE
    if (left <= start && end <= right) {
        return minTree[node]
    }
    val mid = (start + end) / 2
    return min(findMin(start, mid, node * 2, left, right), findMin(mid + 1, end, node * 2 + 1, left, right))
}

private fun findMax(start: Int, end: Int, node: Int, left: Int, right: Int): Int {
    if (right < start || end < left) return Int.MIN_VALUE
    if (left <= start && end <= right) {
        return maxTree[node]
    }
    val mid = (start + end) / 2
    return max(findMax(start, mid, node * 2, left, right), findMax(mid + 1, end, node * 2 + 1, left, right))
}
