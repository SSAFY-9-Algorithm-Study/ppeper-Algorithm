package week29

class `불량 사용자` {

    private var size = 0
    private var length = 0
    private lateinit var userId: Array<String>
    private lateinit var visitedUserId: BooleanArray
    private lateinit var bannedId: Array<String>
    private lateinit var availableList: HashSet<String>

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        size = banned_id.size
        length = banned_id.joinToString("").length
        userId = user_id
        bannedId = banned_id
        visitedUserId = BooleanArray(user_id.size)
        availableList = HashSet()
        getAvailableBannedId(0, mutableListOf())
        return availableList.size
    }

    private fun getAvailableBannedId(start: Int, list: MutableList<String>) {
        // 불량아이디 목록을 다 확인
        if (start == size) {
            val str = list.sorted().joinToString("")
            if (str.length == length) availableList.add(str)
            return
        }

        for (i in userId.indices) {
            if (visitedUserId[i]) continue
            for (j in start until size) {
                // 유저 아이디와 일치하는지 확인
                if (check(userId[i], bannedId[j])) {
                    visitedUserId[i] = true
                    list.add(userId[i])
                    getAvailableBannedId(j + 1, list)
                    list.remove(userId[i])
                    visitedUserId[i] = false
                }
            }
        }
        return
    }

    private fun check(userId: String, bannedId: String): Boolean {
        if (bannedId.length != userId.length) return false
        for (i in bannedId.indices) {
            if (bannedId[i] == '*') continue
            if (bannedId[i] != userId[i]) return false
        }
        return true
    }
}

fun main() {
    val s = `불량 사용자`()
    println(
        s.solution(
            arrayOf(
                "frodo", "fradi", "crodo", "abc123", "frodoc"
            ),
            arrayOf(
                "fr*d*", "*rodo", "******", "******"
            )
        )
    )
}