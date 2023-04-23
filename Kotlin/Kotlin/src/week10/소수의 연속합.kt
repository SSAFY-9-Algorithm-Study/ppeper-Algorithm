package week10

import kotlin.math.sqrt

private lateinit var prime: BooleanArray
private lateinit var primeSum: ArrayList<Int>
fun main() = with(System.`in`.bufferedReader()) {
    var answer = 0
    val n = readLine().toInt()
    prime = BooleanArray(n + 1) { true }
    primeSum = ArrayList()
    checkPrime(n)
    initPrimeSum(n)
    // 구간합이 n인것 구하기
    var start = 0
    var end = 0
    while (start < primeSum.size && end < primeSum.size) {
        val sum = primeSum[end] - primeSum[start]
        if (sum == n) answer++
        if (sum < n) {
            end++
        } else {
            start++
        }
    }
    println(answer)
}

private fun initPrimeSum(n: Int) {
    primeSum.add(0)
    var prev = 0
    for (i in 2..n) {
        if (prime[i]) {
            primeSum.add(prev + i)
            prev += i
        }
    }
}

private fun checkPrime(n: Int) {
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (prime[i]) {
            for (j in i * 2..n step i) {
                prime[j] = false
            }
        }
    }
}