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

    @Tag("Medium")
    @Tag("BinarySearch")
    @ParameterizedTest
    @MethodSource("searchMatrixProvider")
    @DisplayName("""You are given an m x n integer matrix matrix with the following two properties:
        Each row is sorted in non-decreasing order.
        The first integer of each row is greater than the last integer of the previous row.
        Given an integer target, return true if target is in matrix or false otherwise.
        You must write a solution in O(log(m * n)) time complexity.
    """)
    fun searchMatrix(matrix: Array<IntArray>, target: Int, expected: Boolean) {
        val firstCol = matrix.map { it[0] }
        val (idx, value) = binarySearch(firstCol.toIntArray(), target)
        if (value == target) {
            Assertions.assertThat(value == target).isEqualTo(expected)
            return
        }

        val (_, value2) = binarySearch(matrix[idx], target)
        if (value2 == target) {
            Assertions.assertThat(value2 == target).isEqualTo(expected)
            return
        }

        Assertions.assertThat(value2 == target).isEqualTo(expected)
    }

    fun binarySearch(array: IntArray, target: Int): Pair<Int, Int> {
        var start = 0
        var end = array.size - 1
        var mid:Int
        while (start <= end) {
            mid = (start + end) / 2
            if (array[mid] == target) {
                return mid to array[mid]
            }
            if (array[mid] > target) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        mid = (start + end) / 2
        return mid to array[mid]
    }

    @Tag("Medium")
    @Tag("SlideWindow")
    @ParameterizedTest
    @MethodSource("lengthOfLongestSubstringProvider")
    @DisplayName("""Given a string s, find the length of the longest substring without repeating characters.""")
    fun lengthOfLongestSubstring(s: String, expected: Int) {
        var longest = 0
        var l = 0
        val charSet = HashSet<Char>()

        for (r in s.indices) {
            while (charSet.contains(s[r])) {
                charSet.remove(s[l])
                l += 1
            }
            charSet.add(s[r])
            longest = max(longest, r - l + 1)
        }

        Assertions.assertThat(longest).isEqualTo(expected)
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

        @JvmStatic
        fun searchMatrixProvider(): Stream<Arguments> =
            Stream.of(
                arguments(
                    arrayOf(
                        intArrayOf(1,3,5,7),
                        intArrayOf(10,11,16,20),
                        intArrayOf(23,30,34,60)
                    ),
                    3,
                    true
                ),
                arguments(
                    arrayOf(
                        intArrayOf(1,3,5,7),
                        intArrayOf(10,11,16,20),
                        intArrayOf(23,30,34,60)
                    ),
                    13,
                    false
                ),
                arguments(
                    arrayOf(
                        intArrayOf(1,3,5,7),
                        intArrayOf(10,11,16,20),
                        intArrayOf(23,30,34,50)
                    ),
                    11,
                    true
                )
            )

        @JvmStatic
        fun lengthOfLongestSubstringProvider(): Stream<Arguments> =
            Stream.of(
                arguments("abcabcbb", 3),
                arguments("bbbbb", 1),
                arguments("pwwkew", 3),
                arguments(" ", 1),
            )
    }

}