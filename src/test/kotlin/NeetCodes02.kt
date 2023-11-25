import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
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

    @Tag("Easy")
    @Tag("LinkedList")
    @ParameterizedTest
    @MethodSource("reverseListProvider")
    @DisplayName("Given the beginning of a singly linked list head, reverse the list, and return the new beginning of the list.")
    fun reverseList(head: ListNode?) {
        var prevNode: ListNode? = null
        var curNode = head
        var nextNode: ListNode?
        while (curNode != null) {
            nextNode = curNode.next
            curNode.next = prevNode

            prevNode = curNode
            curNode = nextNode
        }

        println(prevNode)
    }

    @Tag("Easy")
    @Tag("LinkedList")
    @ParameterizedTest
    @MethodSource("invertTreeProvider")
    @DisplayName("Given the root of a binary tree, invert the tree, and return its root.")
    fun invertTree(root: TreeNode?) {
        swap(root)
    }

    private fun swap(node: TreeNode?) {
        if (node == null) return

        val temp = node.left
        node.left = node.right
        node.right = temp

        swap(node.left)
        swap(node.right)
    }

    companion object {
        @JvmStatic
        fun binarySearchProvider(): Stream<Arguments> =
            Stream.of(
                arguments(listOf(-1,0,3,5,9,12).toIntArray(), 9, 4),
                arguments(listOf(-1,0,3,5,9,12).toIntArray(), 2, -1)
            )

        @JvmStatic
        fun reverseListProvider(): Stream<Arguments> {
            val listNode5 = ListNode(5)
            val listNode4 = ListNode(4).also { it.next = listNode5 }
            val listNode3 = ListNode(3).also { it.next = listNode4 }
            val listNode2 = ListNode(2).also { it.next = listNode3 }
            val listNode1 = ListNode(1).also { it.next = listNode2 }

            return Stream.of(
                Arguments.of(listNode1)
            )
        }

        @JvmStatic
        fun invertTreeProvider(): Stream<Arguments> {
            // root = [4,2,7,1,3,6,9]
            // 4,7,2,9,6,3,1
            var root = TreeNode(4)
            root.left = TreeNode(2)
            root.right = TreeNode(7)
            root.left!!.left = TreeNode(1)
            root.left!!.right = TreeNode(3)
            root.right!!.left = TreeNode(6)
            root.right!!.right = TreeNode(9)

            return Stream.of(
                arguments(root)
            )
        }
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

}

