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

class NeetCodes04 {

    @Tag("Medium")
    @Tag("ArrayHashing")
    @ParameterizedTest
    @MethodSource("longestConsecutiveProvider")
    @DisplayName("""Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
    You must write an algorithm that runs in O(n) time.
    """)
    fun longestConsecutive(nums: IntArray, expected: Int) {
        val hashSet = nums.toSet()
        var maxLen = 0
        var len = 0
        for (num in nums) {
            if (!hashSet.contains(num - 1)) {
                len = 0
                while(hashSet.contains(num + len)) {
                    len++
                }
            }
            maxLen = max(maxLen, len)
        }

        Assertions.assertThat(maxLen).isEqualTo(expected)
    }

    @Tag("Medium")
    @Tag("TwoPointer")
    @ParameterizedTest
    @MethodSource("twoSumProvider")
    @DisplayName("""Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
        Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
        The tests are generated such that there is exactly one solution. You may not use the same element twice.
        Your solution must use only constant extra space.
    """)
    fun twoSum(numbers: IntArray, target: Int, expected: IntArray) {
        val answer = IntArray(2) { 0 }
        for (i in numbers.indices) {
            for (j in i + 1 until numbers.size) {
                if (numbers[i] + numbers[j] == target) {
                    answer[0] = min(i, j) + 1
                    answer[1] = max(i, j) + 1
                    break
                }
            }
        }
        Assertions.assertThat(answer).isEqualTo(expected)
    }

    companion object {

        @JvmStatic
        fun longestConsecutiveProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(100,4,200,1,3,2), 4),
                arguments(intArrayOf(0,3,7,2,5,8,4,6,0,1), 9),
                arguments(intArrayOf(9,1,4,7,3,-1,0,5,8,-1,6), 7)
            )

        @JvmStatic
        fun twoSumProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(2,7,11,15), 9, intArrayOf(1,2)),
                arguments(intArrayOf(2,3,4), 6, intArrayOf(1,3)),
                arguments(intArrayOf(-1,0), -1, intArrayOf(1,2))
            )
    }

}