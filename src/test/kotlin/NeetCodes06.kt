import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max

class NeetCodes06 {

    @Tag("Medium")
    @Tag("Greedy")
    @ParameterizedTest
    @MethodSource("canJumpProvider")
    @DisplayName("""You are given an integer array nums. You are initially positioned at the array's first index, 
        and each element in the array represents your maximum jump length at that position.
        Return true if you can reach the last index, or false otherwise.
    """)
    fun canJump(nums: IntArray, expected: Boolean) {
        var cur = nums.lastIndex
        while (cur > 0) {
            val curLast = cur
            for (prev in 0..< curLast) {
                if (prev + nums[prev] >= curLast) {
                    cur = minOf(prev, cur)
                }
            }
            if (nums[cur] == 0 || cur == curLast) break
        }
        Assertions.assertThat(cur == 0).isEqualTo(expected)
    }

    @Tag("Medium")
    @Tag("2D_DynamicProgramming")
    @ParameterizedTest
    @MethodSource("longestCommonSubsequenceProvider")
    @DisplayName("""Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
        A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
        For example, "ace" is a subsequence of "abcde".
        A common subsequence of two strings is a subsequence that is common to both strings.
    """)
    fun longestCommonSubsequence(text1: String, text2: String, expected: Int) {
        val dp = Array(text1.length + 1) { IntArray(text2.length + 1) { 0 } }

        for (i in 1..text1.length) {
            for (j in 1..text2.length) {
                if (text1[i - 1] == text2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }

        Assertions.assertThat(dp[text1.length][text2.length]).isEqualTo(expected)
    }


    companion object {
        @JvmStatic
        fun canJumpProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(2,3,1,1,4), true),
                arguments(intArrayOf(3,2,1,0,4), false),
                arguments(intArrayOf(2,0), true),
                arguments(intArrayOf(2,0,0), true),
                arguments(intArrayOf(3,0,8,2,0,0,1), true),
                arguments(intArrayOf(4,2,0,0,1,1,4,4,4,0,4,0), true),
                arguments(intArrayOf(1,2,3), true)
            )

        @JvmStatic
        fun longestCommonSubsequenceProvider(): Stream<Arguments> =
            Stream.of(
                arguments("abcde", "ace", 3),
                arguments("abc", "abc", 3),
                arguments("abc", "def", 0),
                arguments("bl", "yby", 1),
                arguments("a", "n", 0),
                arguments("ezupkr", "ubmrapg", 2)
            )
    }

}