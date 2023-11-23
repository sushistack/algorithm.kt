import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class NeetCodes02 {

    @Tag("Easy")
    @Tag("BinarySearch")
    @ParameterizedTest
    @MethodSource("binarySearchProvider")
    @DisplayName(
        "Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1." +
        "You must write an algorithm with O(log n) runtime complexity."
    )
    fun binarySearch(nums: IntArray, target: Int, expected: Int) {
        var start = 0
        var end = nums.lastIndex
        var mid: Int

        var answer = -1
        while(start <= end) {
            mid = (start + end) / 2
            if (nums[mid] == target) {
                answer = mid
                break
            } else if (nums[mid] < target) {
                start = mid + 1
            } else {
                end = mid - 1
            }
        }

        Assertions.assertThat(answer).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun binarySearchProvider(): Stream<Arguments> =
            Stream.of(
                Arguments.arguments(listOf(-1,0,3,5,9,12).toIntArray(), 9, 4),
                Arguments.arguments(listOf(-1,0,3,5,9,12).toIntArray(), 2, -1)
            )
    }
}