import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.Exception
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

    @Tag("Easy")
    @Tag("1D DynamicProgramming")
    @ParameterizedTest
    @MethodSource("climbStairsProvider")
    @DisplayName("""
        You are climbing a staircase. It takes n steps to reach the top.
        Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
    """)
    fun climbStairs(n: Int, expected: Int) {
        if (n == 1) return
        val dp = Array(n + 1) { 0 }
        dp[1] = 1
        dp[2] = 2
        for (i in 3..n) {
            dp[i] = dp[i - 1] + dp[i - 2]
        }
        Assertions.assertThat(dp[n]).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun subsetsProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(1,2,3), listOf(listOf(), listOf(1), listOf(2), listOf(1, 2), listOf(3), listOf(1, 3), listOf(2, 3), listOf(1, 2, 3)))
        )

        @JvmStatic
        fun climbStairsProvider(): Stream<Arguments> =
            Stream.of(
                arguments(2, 2),
                arguments(3, 3)
            )
    }

    class NumberStatus(
        val num: Int,
        var used: Boolean = false
    )
}