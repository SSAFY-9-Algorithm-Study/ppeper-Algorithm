package week22

// 1. 계란을 치면 상대 계란의 무게만큼 깎이게 된다.
// 2. 0이하가 되는 순간 계란은 깨진다
data class Egg(
    var durability: Int = 0,
    var weight: Int = 0
)
private lateinit var list: Array<Egg>
private var max = 0
fun main() = with(System.`in`.bufferedReader()) {
    val size = readLine().toInt()
    list = Array(size) { Egg() }
    repeat(size) {
        val (dura, we) = readLine().split(" ").map { it.toInt() }
        list[it] = Egg(durability = dura, weight = we)
    }
    brokenEgg(0)
    println(max)
}

private fun brokenEgg(start: Int) {
    if (list.size <= start) return
    if (list[start].durability <= 0) {
        brokenEgg(start + 1)
        return
    }
    val currentEgg = list[start]
    for (i in list.indices) {
        if (i == start) continue
        // 깨진 계란
        if (list[i].durability <= 0) continue
        val targetEgg = list[i]
        currentEgg.durability -= targetEgg.weight
        targetEgg.durability -= currentEgg.weight
        brokenEgg(start + 1)
        // 꺠진 계란들
        val count = list.count { it.durability <= 0 }
        max = max.coerceAtLeast(count)
        currentEgg.durability += targetEgg.weight
        targetEgg.durability += currentEgg.weight
    }
}