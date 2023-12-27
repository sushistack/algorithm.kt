import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class NeetCodes07 {

    @Tag("Medium")
    @Tag("ArrayHashing")
    @ParameterizedTest
    @MethodSource("groupAnagramsProvider")
    @DisplayName("Given an array of strings strs, group the anagrams together. You can return the answer in any order.")
    fun groupAnagrams(strs: Array<String>, expected: List<List<String>>) {
        val anagramMap = mutableMapOf<String, MutableList<String>>()

        for (str in strs) {
            if (anagramMap.isEmpty()) {
                anagramMap[str] = mutableListOf(str)
            } else {
                var isAdded = false
                for (entry in anagramMap.entries) {
                    if (isAnagrams(entry.key, str)) {
                        entry.value.add(str)
                        isAdded = true
                        break
                    }
                }
                if (!isAdded) {
                    anagramMap[str] = mutableListOf(str)
                }
            }
        }

        anagramMap.values.map { it.toList() }
        // Assertions.assertThat(anagramMap.values).isEqualTo(expected)
    }

    // n * n * log n = tle
    // n * n 으로 끝내야 함
    @Tag("Medium")
    @Tag("TwoPointer")
    @ParameterizedTest
    @MethodSource("threeSumProvider")
    @DisplayName("""
        Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
        Notice that the solution set must not contain duplicate triplets.
    """)
    fun threeSum(nums: IntArray, expected: List<List<Int>>) {
        val sorted = nums.sorted()
        val answer = mutableListOf<List<Int>>()
        for (i in sorted.indices) {
            for (j in sorted.indices) {
                if (i == j) continue
                val subSum = sorted[i] + sorted[j]
                val idx = binarySearch(sorted.toIntArray(), -subSum)
                if (idx != -1 && idx != i && idx != j) {
                    answer.add(listOf(sorted[i], sorted[j], sorted[idx]).sorted())
                }
            }
        }

        Assertions.assertThat(answer.distinct()).isEqualTo(expected)
    }

    private fun binarySearch(array: IntArray, target: Int): Int {
        var start = 0
        var end = array.size - 1

        while (start <= end) {
            val mid = (start + end) / 2
            if (array[mid] == target) {
                return mid
            } else if (array[mid] < target) {
                start = mid + 1
            } else {
                end = mid - 1
            }
        }
        return -1
    }

    private fun isAnagrams(str1: String, str2: String): Boolean {
        if (str1.length != str2.length) return false
        val anagrams = mutableMapOf<Char, Int>()
        for (s in str1) {
            if (anagrams.containsKey(s)) {
                anagrams[s] = anagrams[s]!! + 1
            } else {
                anagrams[s] = 1
            }
        }
        for (s in str2) {
            if (anagrams.containsKey(s)) {
                anagrams[s] = anagrams[s]!! - 1
            } else {
                anagrams[s] = -1
            }
        }
        return anagrams.values.all { it == 0 }
    }

    companion object {
        @JvmStatic
        fun groupAnagramsProvider(): Stream<Arguments> =
            Stream.of(
                arguments(
                    arrayOf("eat","tea","tan","ate","nat","bat"),
                    listOf(listOf("bat"), listOf("nat","tan"), listOf("ate","eat","tea"))
                ),
                arguments(
                    arrayOf(""),
                    listOf(listOf(""))
                ),
                arguments(
                    arrayOf("a"),
                    listOf(listOf("a"))
                ),
                arguments(
                    arrayOf("", "b"),
                    listOf(listOf(""), listOf("b"))
                ),
                arguments(
                    arrayOf("hhhhu","tttti","tttit","hhhuh","hhuhh","tittt"),
                    listOf(listOf("tittt","tttit","tttti"), listOf("hhhhu","hhhuh","hhuhh"))
                ),
            )

        @JvmStatic
        fun threeSumProvider(): Stream<Arguments> =
            Stream.of(
                arguments(
                    intArrayOf(-1,0,1,2,-1,-4),
                    listOf(listOf(-1,-1,2), listOf(-1,0,1))
                ),
                arguments(
                    intArrayOf(0,1,1),
                    listOf<List<String>>()
                ),
                arguments(
                    intArrayOf(0,0,0),
                    listOf(listOf(0,0,0))
                ),
            )
    }
}