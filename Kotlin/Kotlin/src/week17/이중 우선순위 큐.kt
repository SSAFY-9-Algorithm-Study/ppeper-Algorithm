package week17

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    val sb = StringBuilder()
    repeat(t) {
        val n = readLine().toInt()
        val map = TreeMap<Int, Int>()
        repeat(n) {
            val (op, number) = readLine().split(" ")
            when (op) {
                "I" -> {
                    map[number.toInt()] = map.getOrDefault(number.toInt(), 0) + 1
                }
                "D" -> {
                    if (map.isNotEmpty()) {
                        if (number.toInt() == 1) {
                            val count = map[map.lastKey()]
                            if (count == 1) {
                                map.remove(map.lastKey())
                            } else {
                                if (count != null) {
                                    map[map.lastKey()] = count - 1
                                }
                            }
                        } else {
                            val count = map[map.firstKey()]
                            if (count == 1) {
                                map.remove(map.firstKey())
                            } else {
                                if (count != null) {
                                    map[map.firstKey()] = count - 1
                                }
                            }
                        }
                    }
                }
            }
        }
        if (map.isEmpty()) {
            sb.append("EMPTY\n")
        } else {
            sb.append("${map.lastKey()} ${map.firstKey()}\n")
        }
    }
    println(sb)
}