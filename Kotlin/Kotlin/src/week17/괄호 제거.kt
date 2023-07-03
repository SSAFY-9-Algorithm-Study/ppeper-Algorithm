package week17

import java.util.*

private lateinit var answer: HashSet<String>
private lateinit var list: ArrayList<Pair<Int, Int>>
private lateinit var selected: BooleanArray
private lateinit var input: CharArray
fun main() = with(System.`in`.bufferedReader()) {
    input = readLine().toCharArray()
    val stack = Stack<Int>()
    list = ArrayList()
    answer = HashSet<String>()
    for (i in input.indices) {
        if (input[i] == '(') {
            stack.push(i)
        }
        if (input[i] == ')') {
            list.add(Pair(stack.pop(), i))
        }
    }
    selected = BooleanArray(list.size)
    // 선택할 개수
    for (i in 1..list.size) {
        combination(i, 0)
    }
    answer.sorted()
        .forEach { println(it) }
}

private fun combination(count: Int, start: Int) {
    if (count == 0) {
        selected.indices
            .filter { idx -> selected[idx] }
            .also { filter ->
                list.indices.filter { filter.contains(it) }
                    .flatMap { list[it].toList() }
                    .also { filter ->
                        answer.add(
                            input.indices.filterNot {
                                filter.contains(it)
                            }.joinToString("") { input[it].toString() }
                        )
                    }
            }
    }
    for (i in start until list.size) {
        selected[i] = true
        combination(count - 1, i + 1)
        selected[i] = false
    }
}
