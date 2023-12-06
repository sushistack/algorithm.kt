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
import kotlin.math.pow

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

    companion object {

        @JvmStatic
        fun longestConsecutiveProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(100,4,200,1,3,2), 4),
                arguments(intArrayOf(0,3,7,2,5,8,4,6,0,1), 9),
                arguments(intArrayOf(9,1,4,7,3,-1,0,5,8,-1,6), 7)
            )
    }

}