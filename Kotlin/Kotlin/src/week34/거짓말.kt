package week34

import java.util.*

private lateinit var parent: IntArray
private lateinit var party: Array<LinkedList<Int>>
private lateinit var truth: HashSet<Int>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    parent = IntArray(n + 1) { it }
    party = Array(m + 1) { LinkedList() }
    val knowTruth = readLine().split(" ").map { it.toInt() }
    truth = knowTruth.subList(1, knowTruth.size).toHashSet()
    repeat(m) {
        val input = readLine().split(" ").map { it.toInt() }
        for (i in 1 until input.size) {
            party[it + 1].add(input[i])
        }
    }
    for (i in 1 until party.size) {
        for (member in party[i]) {
            // 참석한 인원들은 같은 집합
            union(party[i][0], member)
        }
    }
    var answer = 0
    // 파티의 수 찾기
    for (i in 1 until party.size) {
        var flag = true
        loop@for (member in party[i]) {
            for (know in truth) {
                if (getParent(member) == getParent(know)) {
                    // 거짓말이 들통난다
                    flag = false
                    break@loop
                }
            }
        }
        if (flag) answer++
    }
    println(answer)
}

private fun union(a: Int, b: Int) {
    val pa = getParent(a)
    val pb = getParent(b)
    if (pa < pb) parent[pb] = pa
    else parent[pa] = pb
}

private fun getParent(node: Int): Int {
    if (parent[node] != node) {
        parent[node] = getParent(parent[node])
    }
    return parent[node]
}