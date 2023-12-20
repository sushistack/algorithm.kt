import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

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
    }

}