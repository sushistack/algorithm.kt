import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

class NeetCodes01 {

    @Tag("Medium")
    @Tag("ArrayHashing")
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

    @Tag("Easy")
    @Tag("ArrayHashing")
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

    @Tag("Easy")
    @Tag("TwoPointer")
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


    @Tag("Easy")
    @Tag("Stack")
    @ParameterizedTest
    @MethodSource("isValidParenthesesProvider")
    @DisplayName("""
            Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
            An input string is valid if:
            1. Open brackets must be closed by the same type of brackets.
            2. Open brackets must be closed in the correct order.
            3. Every close bracket has a corresponding open bracket of the same type.
    """)
    fun isValidParentheses(s: String, expected: Boolean) {
        val stack = Stack<Char>()
        s.forEach {
            if (stack.empty()) {
                stack.push(it)
            } else {
                if (stack.peek() == getPartner(it)) {
                    stack.pop()
                } else {
                    stack.push(it)
                }
            }
        }
        Assertions.assertThat(stack.isEmpty()).isEqualTo(expected)
    }

    private fun getPartner(c: Char) = when (c) {
        '}' -> '{'
        ']' -> '['
        ')' -> '('
        else -> ' '
    }

    @Tag("Easy")
    @Tag("SlideWindow")
    @ParameterizedTest
    @MethodSource("maxProfitProvider")
    @DisplayName("""
        You are given an array prices where prices[i] is the price of a given stock on the ith day.
        You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
        Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
    """
    )
    fun maxProfit(prices: IntArray, expected: Int) {
        var maxProfit = 0
        for (i in prices.indices) {
            for (j in i + 1 until prices.size) {
                maxProfit = max(maxProfit, prices[j] - prices[i])
            }
        }
        Assertions.assertThat(maxProfit).isEqualTo(expected)
    }


    companion object {
        @JvmStatic
        fun topKFrequentProvider(): Stream<Arguments> =
            Stream.of(
                Arguments.of(intArrayOf(1,1,1,2,2,3), 2, intArrayOf(1,2)),
                Arguments.of(intArrayOf(1), 1, intArrayOf(1)),
                Arguments.of(intArrayOf(1,2), 2, intArrayOf(1,2)),
            )

        @JvmStatic
        fun twoSumProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(2,7,11,15), 9, intArrayOf(0,1)),
                arguments(intArrayOf(3,2,4), 6, intArrayOf(1,2)),
                arguments(intArrayOf(3,3), 6, intArrayOf(0,1)),
            )

        @JvmStatic
        fun isPalindromeProvider(): Stream<Arguments> =
            Stream.of(
                arguments("A man, a plan, a canal: Panama", true),
                arguments("race a car", false),
                arguments("0P", false)
            )

        @JvmStatic
        fun isValidParenthesesProvider(): Stream<Arguments> =
            Stream.of(
                arguments("()", true),
                arguments("()[]{}", true),
                arguments("(]", false),
                arguments("([])", true)
            )

        @JvmStatic
        fun maxProfitProvider(): Stream<Arguments> =
            Stream.of(
                Arguments.of(intArrayOf(7,1,5,3,6,4), 5),
                Arguments.of(intArrayOf(7,6,4,3,1), 0),
            )
    }
}