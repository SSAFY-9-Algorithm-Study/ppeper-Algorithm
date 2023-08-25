package week22

private lateinit var postOrder: StringBuilder
private lateinit var preOrder: List<Int>
private lateinit var inOrder: List<Int>
private var index = 0
fun main() = with(System.`in`.bufferedReader()) {
    val test = readLine().toInt()
    val answer = StringBuilder()
    repeat(test) {
        val size = readLine().toInt()
        postOrder = StringBuilder()
        preOrder = readLine().trim().split(" ").map { it.toInt() }
        inOrder = readLine().trim().split(" ").map { it.toInt() }
        index = 0
        getPostOrder(0, size - 1)
        answer.append("$postOrder\n")
    }
    println(answer)
}

// PostOrder -> 왼쪽 오른쪽 가운데 순서
private fun getPostOrder(start: Int, end: Int) {
    if (end < start) return
    // PreOrder -> 가운데 왼쪽 오른쪽
    val root = preOrder[index++]
    // InOrder -> 왼쪽 가운데 오른쪽
    val leftIndex = inOrder.indexOf(root)
    getPostOrder(start, leftIndex - 1)
    getPostOrder(leftIndex + 1, end)
    postOrder.append("$root ")
}
