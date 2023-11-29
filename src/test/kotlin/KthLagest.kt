import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import java.util.*

@Tag("Easy")
@Tag("Heap")
@DisplayName("""
        You are part of a university admissions office and need to keep track of the kth highest test score from applicants in real-time. This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.
        You are tasked to implement a class which, for a given integer k, maintains a stream of test scores and continuously returns the kth highest test score after a new score has been submitted. More specifically, we are looking for the kth highest score in the sorted list of all scores.
    """)

class KthLagest(k: Int, nums: IntArray) {
    private val minHeap = PriorityQueue<Int>()
    private val maxSize = k

    init {
        for (num in nums) {
            minHeap.add(num)
        }
        while (minHeap.size > maxSize) {
            minHeap.poll()
        }
    }

    fun add(`val`: Int): Int {
        minHeap.offer(`val`)

        while (minHeap.size > maxSize) {
            minHeap.poll()
        }

        return minHeap.peek()
    }

}