package week51

import java.util.PriorityQueue

data class GasStation(
    val position: Int,
    val gas: Int
)

private lateinit var gasStation: PriorityQueue<GasStation>
private lateinit var amountOfGas: PriorityQueue<Int>
private var count = 0

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    gasStation = PriorityQueue(compareBy { it.position })
    amountOfGas = PriorityQueue(reverseOrder<Int>())
    repeat(n) {
        val input = readLine().split(" ").map { it.toInt() }
        gasStation.add(GasStation(input[0], input[1]))
    }
    val (L, P) = readLine().split(" ").map { it.toInt() }
    var currentGas = P
    while (gasStation.isNotEmpty()) {
        val (pos, gas) = gasStation.poll()
        // 갈 수 있는 주유소
        if (pos <= currentGas) amountOfGas.add(gas)
        else {
            // 갈 수 있을만큼 가스를 채워줌
            while (amountOfGas.isNotEmpty()) {
                currentGas += amountOfGas.poll()
                count++
                if (pos <= currentGas) {
                    amountOfGas.add(gas)
                    break
                }
            }
        }
    }
    while (amountOfGas.isNotEmpty()) {
        if (L <= currentGas) break
        currentGas += amountOfGas.poll()
        count++
    }
    if (L <= currentGas) println(count) else println(-1)
}