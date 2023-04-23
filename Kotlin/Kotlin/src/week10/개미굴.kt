package week10

import java.util.*

private lateinit var st: StringTokenizer
data class Node(val child: TreeMap<String, Node>)
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val tree = Node(TreeMap())
    repeat(n) {
        var curr = tree
        st = StringTokenizer(readLine())
        val count = st.nextToken().toInt()
        repeat(count) {
            val node = st.nextToken()
            // 자식 추가
            if (!curr.child.containsKey(node)) {
                curr.child[node] = Node(TreeMap())
            }
            curr = curr.child[node]!!
        }
    }
    printNode(tree, "")
}

private fun printNode(tree: Node, floor: String) {
    for (child in tree.child) {
        println("$floor${child.key}")
        tree.child[child.key]?.let {
            printNode(it, "$floor--")
        }
    }
}