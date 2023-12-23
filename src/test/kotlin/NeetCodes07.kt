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
    }
}