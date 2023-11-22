import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

class NeetCodes {

    @ParameterizedTest
    @MethodSource("topKFrequentProvider")
    @DisplayName("Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.")
    fun topKFrequent(nums: IntArray, k: Int, expected: IntArray) {
        val map = mutableMapOf<Int, Int>()
        nums.forEach { map[it] = (map[it] ?: 0) + 1 }

        val a = map.map { it.key to it.value }
            .sortedByDescending { it.second }
            .subList(0, k)
            .map { it.first }
            .toIntArray()

        Assertions.assertThat(a).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("twoSumProvider")
    @DisplayName(
        "Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.\n" +
        "You may assume that each input would have exactly one solution, and you may not use the same element twice.\n" +
        "You can return the answer in any order."
    )
    fun twoSum(nums: IntArray, target: Int, expected: IntArray) {
        val answer = IntArray(2)
        for (i in nums.indices) {
            for (j in nums.indices) {
                if (nums[i] + nums[j] == target && i != j) {
                    answer[0] = min(i.toDouble(), j.toDouble()).toInt()
                    answer[1] = max(i.toDouble(), j.toDouble()).toInt()
                    break
                }
            }
        }
        Assertions.assertThat(answer).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("isPalindromeProvider")
    @DisplayName(
        "A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.\n" +
        "Given a string s, return true if it is a palindrome, or false otherwise."
    )
    fun isPalindrome(s: String, expected: Boolean) {
        val words = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        val str = s.filter { it in words }.lowercase()

        var answer = true
        if (str.isEmpty()) answer = false
        else {
            for (i in 0..(str.length / 2)) {
                if (str[i] != str[str.length - i - 1]) {
                    answer = false
                }
            }
        }
        Assertions.assertThat(answer).isEqualTo(expected)
    }


    companion object {
        @JvmStatic
        fun topKFrequentProvider(): Stream<Arguments> =
            Stream.of(
                arguments(arrayOf(1,1,1,2,2,3).toIntArray(), 2, arrayOf(1,2).toIntArray()),
                arguments(arrayOf(1).toIntArray(), 1, arrayOf(1).toIntArray()),
            )

        @JvmStatic
        fun twoSumProvider(): Stream<Arguments> =
            Stream.of(
                arguments(arrayOf(2,7,11,15).toIntArray(), 9, arrayOf(0,1).toIntArray()),
                arguments(arrayOf(3,2,4).toIntArray(), 6, arrayOf(1,2).toIntArray()),
                arguments(arrayOf(3,3).toIntArray(), 6, arrayOf(0,1).toIntArray()),
            )

        @JvmStatic
        fun isPalindromeProvider(): Stream<Arguments> =
            Stream.of(
                arguments("A man, a plan, a canal: Panama", true),
                arguments("race a car", false),
                arguments("0P", false)
            )
    }
}