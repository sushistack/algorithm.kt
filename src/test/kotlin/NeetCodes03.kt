import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

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

    @Tag("Medium")
    @Tag("Graph")
    @ParameterizedTest
    @MethodSource("numIslandsProvider")
    @DisplayName("""
        Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
        An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
    """)
    fun numIslands(grid: Array<CharArray>, expected: Int) {
        val map = grid.map { r -> r.map { c -> LandOrWater(isLand = c == '1') } }
        var islands = 0
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (dfs2(r, c, map)) {
                    islands++
                }
            }
        }
        Assertions.assertThat(islands).isEqualTo(expected)
    }

    private fun dfs2(r: Int, c: Int, map: List<List<LandOrWater>>): Boolean {
        if (!map[r][c].isLand || map[r][c].visited) return false

        map[r][c].visited = true
        val nexts = listOf((r + 1) to c, (r - 1) to c, r to (c + 1), r to (c - 1))
        for (next in nexts) {
            if (next.first < 0 || next.first >= map.size || next.second < 0 || next.second >= map[r].size) {
                continue
            }

            if (!map[next.first][next.second].isLand || map[next.first][next.second].visited) {
                continue
            }

            dfs2(next.first, next.second, map)
        }

        return true
    }

    class LandOrWater(
        val isLand: Boolean,
        var visited: Boolean = false
    )

    @Tag("Medium")
    @Tag("Interval")
    @ParameterizedTest
    @MethodSource("insertProvider")
    @DisplayName("""
        Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
        An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
    """)
    fun insert(intervals: Array<IntArray>, newInterval: IntArray) {
        val ints = intervals.map { it.toList() }.toMutableList()
        var new = newInterval
        val res = mutableListOf<IntArray>()
        for (i in intervals.indices) {
            if (newInterval[1] < intervals[i][0]) {
                res.add(new)
                res.addAll(intervals.filterIndexed { index, _ -> index >= i })
                /*return*/ res.toTypedArray()
            } else if (ints[i][1] < new[0]) {
                res.add(ints[i].toIntArray())
            } else {
                new = intArrayOf(min(new[0], ints[i][0]), max(new[1], ints[i][1]))
            }
        }
        res.add(new)
        /*return*/ res.toTypedArray()
    }

    @Tag("Medium")
    @Tag("Greedy")
    @ParameterizedTest
    @MethodSource("maxSubArrayProvider")
    @DisplayName("Given an integer array nums, find the subarray with the largest sum, and return its sum.")
    fun maxSubArray(nums: IntArray, expected: Int) {
        var max = nums[0]
        var curSum = 0

        for (num in nums) {
            if (curSum < 0) {
                curSum = 0
            }
            curSum += num
            max = max(max, curSum)
        }

        Assertions.assertThat(max).isEqualTo(expected)
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

        @JvmStatic
        fun numIslandsProvider(): Stream<Arguments> =
            Stream.of(
                arguments(
                    arrayOf(
                        charArrayOf('1','1','1','1','0'),
                        charArrayOf('1','1','0','1','0'),
                        charArrayOf('1','1','0','0','0'),
                        charArrayOf('0','0','0','0','0')
                    ),
                    1
                ),
                arguments(
                    arrayOf(
                        charArrayOf('1','1','0','0','0'),
                        charArrayOf('1','1','0','0','0'),
                        charArrayOf('0','0','1','0','0'),
                        charArrayOf('0','0','0','1','1')
                    ),
                    3
                )
            )

        @JvmStatic
        fun insertProvider(): Stream<Arguments> =
            Stream.of(
                arguments(
                    arrayOf(intArrayOf(1,3), intArrayOf(6,9)),
                    intArrayOf(2, 5),
                    arrayOf(intArrayOf(1,5), intArrayOf(6,9))
                )
            )

        @JvmStatic
        fun maxSubArrayProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(-2,1,-3,4,-1,2,1,-5,4), 6),
                arguments(intArrayOf(1), 1),
                arguments(intArrayOf(5,4,-1,7,8), 23),
            )
    }

    class NumberStatus(
        val num: Int,
        var used: Boolean = false
    )
}