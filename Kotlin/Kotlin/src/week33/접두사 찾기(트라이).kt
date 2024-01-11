package week33

class TrieNode {
    val children: HashMap<Char, TrieNode> = hashMapOf()
    var isTerminate: Boolean = true
}

class Trie {
    private val root = TrieNode()

    fun insert(word: String) {
        var currentNode = root
        word.forEach { ch ->
            currentNode.children.putIfAbsent(ch, TrieNode())
            currentNode = currentNode.children[ch]!!
        }
        currentNode.isTerminate = true
    }

    fun searchPrefix(prefix: String): Boolean {
        var currentNode = root
        prefix.forEach { ch ->
            if (!currentNode.children.containsKey(ch)) {
                return false
            }
            currentNode = currentNode.children[ch]!!
        }
        return true
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val trie = Trie()
    repeat(n) {
        trie.insert(readLine())
    }
    var count = 0
    repeat(m) {
        if (trie.searchPrefix(readLine())) count++
    }
    println(count)
}