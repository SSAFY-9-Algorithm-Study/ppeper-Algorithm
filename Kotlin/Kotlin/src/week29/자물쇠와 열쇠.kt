package week29

class `자물쇠와 열쇠` {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val start = key.size - 1
        var copyKey = key

        for (i in 0 until lock.size * 2 - 1) {
            for (j in 0 until lock.size * 2 - 1) {
                repeat(4) {
                    val expandLock = Array(58) { IntArray(58) }
                    // lock 위치 초기화
                    for (r in lock.indices) {
                        for (c in lock.indices) {
                            expandLock[r + start][c + start] = lock[r][c]
                        }
                    }
                    for (y in i until i + key.size) {
                        for (x in j until j + key.size) {
                            expandLock[y][x] += copyKey[y - i][x - j]
                        }
                    }
                    if (check(expandLock, lock, start)) { return true }
                    copyKey = rotate90(copyKey)
                }
            }
        }

        return false
    }

    private fun check(key: Array<IntArray>, lock: Array<IntArray>, start: Int): Boolean {
        for (i in lock.indices) {
            for (j in lock.indices) {
                if (key[i + start][j + start] == 0 || key[i + start][j + start] == 2) return false
            }
        }
        return true
    }

    private fun rotate90(key: Array<IntArray>): Array<IntArray> {
        val rot = Array(key.size) { IntArray(key.size) }

        for (i in key.indices) {
            for (j in key.indices) {
                rot[i][j] = key[key.size - 1 - j][i]
            }
        }
        return rot
    }
}