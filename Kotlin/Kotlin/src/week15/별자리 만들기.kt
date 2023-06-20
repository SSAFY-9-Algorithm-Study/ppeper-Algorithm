package week15

import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

private lateinit var parent: IntArray
data class Star(
    val from: Int,
    val to: Int,
    val dist: Double
): Comparable<Star> {
    override fun compareTo(other: Star): Int {
        return if (0 < this.dist - other.dist) 1
        else -1
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    var answer = 0.0
    val n = readLine().toInt()
    val pq = PriorityQueue<Star>()
    parent = IntArray(n + 1) { it }
    val list = ArrayList<Pair<Double, Double>>()
    repeat(n) {
        val (x, y) = readLine().split(" ").map { it.toDouble() }
        list.add(Pair(x, y))
    }
    for (i in 0 until list.size - 1) {
        for (j in i + 1 until list.size) {
            val dist = distance(list[i], list[j])
            pq.offer(Star(i + 1, j + 1, dist))
        }
    }
    while (pq.isNotEmpty()) {
        val (from, to, dist) = pq.poll()
        if (findParent(from) != findParent(to)) {
            unionNode(from, to)
            answer += dist
        }
    }
    println(String.format("%.2f", answer))
}

private fun unionNode(from: Int, to: Int) {
    val parentFrom = findParent(from)
    val parentTo = findParent(to)
    if (parentFrom < parentTo) parent[parentTo] = parentFrom
    else parent[parentFrom] = parentTo
}

private fun findParent(from: Int): Int {
    if (parent[from] != from) {
        parent[from] = findParent(parent[from])
    }
    return parent[from]
}

private fun distance(from: Pair<Double, Double>, to: Pair<Double, Double>): Double {
    return sqrt((from.first - to.first).pow(2.0) + (from.second - to.second).pow(2.0))
}

