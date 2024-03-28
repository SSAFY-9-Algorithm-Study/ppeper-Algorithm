package week47

private var answer = ""
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    makeNumber(n, "")
    println(answer)
}

private fun makeNumber(n: Int, number: String) {
    if (answer.isNotEmpty()) return
    if (number.length == n) {
        answer = number
        return
    }
    for (num in 1..3) {
        if (isValidNumber(number + num)) {
            makeNumber(n, number + num)
        }
    }
}

private fun isValidNumber(number: String): Boolean {
    for (i in 1..number.length / 2) {
        val left = number.substring(number.length - 2 * i, number.length - i)
        val right = number.substring(number.length - i, number.length)
        if (left == right) return false
    }
    return true
}
