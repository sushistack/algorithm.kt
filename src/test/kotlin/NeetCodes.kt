import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

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

    companion object {
        @JvmStatic
        fun topKFrequentProvider(): Stream<Arguments> =
            Stream.of(
                arguments(arrayOf(1,1,1,2,2,3).toIntArray(), 2, arrayOf(1,2).toIntArray()),
                arguments(arrayOf(1).toIntArray(), 1, arrayOf(1).toIntArray()),
            )
    }


}