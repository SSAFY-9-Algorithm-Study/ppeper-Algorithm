package week12

class Solution {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val answer = ArrayList<Int>()
        val genresIndexMap = HashMap<String, ArrayList<Int>>()
        genres.forEachIndexed { index, genre ->
            val list = genresIndexMap.getOrDefault(genre, ArrayList()).apply {
                add(index)
            }
            genresIndexMap[genre] = list
        }
        // 노래들의 합 저장
        val sumOfGenresMap = HashMap<String, Int>()
        genres.forEachIndexed { index, genre ->
            sumOfGenresMap[genre] = sumOfGenresMap.getOrDefault(genre, 0) + plays[index]
        }
        sumOfGenresMap.entries.sortedByDescending { it.value }
            .forEach { entry ->
                // 단일 노래의 play수가 큰 순서대로
                val list = genresIndexMap[entry.key]?.sortedByDescending {
                    plays[it]
                }?.take(2)

                list?.forEach {
                    answer.add(it)
                }
            }
        return answer.toIntArray()
    }
}

fun main() {
    val s = Solution()
    val genres = arrayOf("classic", "pop", "classic", "classic", "pop")
    val plays = intArrayOf(500, 600, 150, 800, 2500)
    println(s.solution(genres, plays).toList())
}