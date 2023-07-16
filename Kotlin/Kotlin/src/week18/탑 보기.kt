package week18

import java.util.*
import kotlin.math.abs

data class Building(
    val number: Int = 0,
    val height: Int = 0
)

data class Data(
    var count: Int = 0,
    var number: Int = 0,
    var distance: Int = 987654321
) {
    override fun toString(): String {
        return if (count == 0) "0" else "$count ${number + 1}"
    }
}

private lateinit var buildings: List<Building>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    buildings = readLine().split(" ")
        .mapIndexed { index, height ->
            Building(
                number = index, height = height.toInt()
            )
        }
    val answer = Array(n) { Data() }
    val stack = Stack<Building>()
    for (i in buildings.indices) {
        // 왼쪽으로 볼 수 있는 건물
        while (stack.isNotEmpty() && stack.peek().height <= buildings[i].height) {
            stack.pop()
        }
        answer[i].count += stack.size
        if (stack.isNotEmpty()) {
            val num = stack.peek().number
            val dist = abs(i - num)
            // 더 가까운 건물로 업데이트
            if (dist < answer[i].distance) {
                answer[i].run {
                    distance = dist
                    number = num
                }
            }
        }
        stack.push(buildings[i])
    }
    stack.clear()
    for (i in n - 1 downTo 0) {
        // 오른쪽으로 볼 수 있는 건물들
        while (stack.isNotEmpty() && stack.peek().height <= buildings[i].height) {
            stack.pop()
        }
        answer[i].count += stack.size
        if (stack.isNotEmpty()) {
            val num = stack.peek().number
            val dist = abs(i - num)
            // 더 가까운 건물로 업데이트
            if (dist < answer[i].distance) {
                answer[i].run {
                    distance = dist
                    number = num
                }
            }
        }
        stack.push(buildings[i])
    }
    answer.forEach {
        println(it)
    }
}