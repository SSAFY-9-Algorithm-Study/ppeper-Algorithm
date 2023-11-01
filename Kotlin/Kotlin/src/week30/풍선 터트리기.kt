package week30

class `풍선 터트리기` {
    fun solution(a: IntArray): Int {
        var front = 1000000000
        var end = 1000000000
        val check = IntArray(a.size)
        for (i in a.indices) {
            if (a[i] < front) {
                front = a[i]
                check[i] = 1
            }
            if (a[a.size - i - 1] < end) {
                end = a[a.size - i - 1]
                check[a.size - i - 1] = 1
            }
        }
        return check.sum()
    }
}