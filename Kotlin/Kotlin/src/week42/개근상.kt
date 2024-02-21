package week42

private var MOD = 1_000_000
private lateinit var dp: Array<Array<IntArray>>
private var n = 0
fun main() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    dp = Array(n) { Array(2) { IntArray(3) { -1 } } }
    println(search(0, 0, 0))
}

private fun search(day: Int, late: Int, absent: Int): Int {
    if (late == 2) return 0
    if (absent == 3) return 0
    if (day == n) return 1
    if (dp[day][late][absent] != -1) return dp[day][late][absent]
    var temp = 0
    // 모든 경우 탐색
    temp = search(day + 1, late, 0) % MOD
    temp = (temp + search(day + 1, late + 1, 0)) % MOD
    temp = (temp + search(day + 1, late, absent + 1)) % MOD
    return temp.also { dp[day][late][absent] = it }
}
