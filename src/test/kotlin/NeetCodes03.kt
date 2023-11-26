import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class NeetCodes03 {

    @Tag("Medium")
    @Tag("BackTracking")
    @ParameterizedTest
    @MethodSource("subsetsProvider")
    @DisplayName(
        "Given an integer array nums of unique elements, return all possible subsets (the power set)." +
        "The solution set must not contain duplicate subsets. Return the solution in any order."
    )
    fun subsets(nums: IntArray) {
        val numbers = nums.map { NumberStatus(it) }

        val result = mutableListOf<List<Int>>()
        dfs(0, numbers, result)
        result.forEach { println(it) }
    }

    private fun dfs(index: Int, numbers: List<NumberStatus>, res: MutableList<List<Int>>) {
        if (index == numbers.size) {
            res.add(numbers.filter { it.used }.map { it.num })
            return
        }

        dfs(index + 1, numbers, res)

        numbers[index].used = true
        dfs(index + 1, numbers, res)
        numbers[index].used = false
    }

    companion object {
        @JvmStatic
        fun subsetsProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(1,2,3), listOf(listOf(), listOf(1), listOf(2), listOf(1, 2), listOf(3), listOf(1, 3), listOf(2, 3), listOf(1, 2, 3)))
        )
    }

    class NumberStatus(
        val num: Int,
        var used: Boolean = false
    )
}