package week32

class `테이블 해시 함수` {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int) =
        data.sortedWith(
            compareBy<IntArray> { it[col - 1] }
                .thenByDescending { it[0] }
        ).mapIndexed { index, array ->
            array.sumOf { it % (index + 1) }
        }.slice(row_begin - 1 until row_end)
            .reduce { acc, i -> acc xor i }
}