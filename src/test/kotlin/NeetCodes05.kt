import com.sun.source.tree.Tree
import net.bytebuddy.implementation.bytecode.ShiftLeft
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*
import java.util.stream.Stream

class NeetCodes05 {

    @Tag("Easy")
    @Tag("Trees")
    @ParameterizedTest
    @MethodSource("maxDepthProvider")
    @DisplayName("""Given the root of a binary tree, return its maximum depth.
        A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
    """)
    fun maxDepth(root: TreeNode?, expected: Int) {
        if (root == null) return

        maxDepth = 0
        depthDfs(root, 0)
        Assertions.assertThat(maxDepth).isEqualTo(expected)
    }

    private fun depthDfs(node: TreeNode?, depth: Int) {
        if (node == null) {
            maxDepth = maxOf(depth, maxDepth)
            return
        }

        depthDfs(node.left, depth + 1)
        depthDfs(node.right, depth + 1)
    }

    // 다른 풀이가 더 효과적
    @Tag("Medium")
    @Tag("BackTracking")
    @ParameterizedTest
    @MethodSource("combinationSumProvider")
    @DisplayName("""Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target.
        You may return the combinations in any order.
        The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the 
        frequency of at least one of the chosen numbers is different.
        The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
    """)
    fun combinationSum(candidates: IntArray, target: Int, expected: List<List<Int>>) {
        combinations = mutableListOf()
        dfs4CombinationSum(intArrayOf(), candidates, target)
        Assertions.assertThat(combinations.map { it.sorted() }.distinct()).isEqualTo(expected)
    }

    private fun dfs4CombinationSum(picked: IntArray, candidates: IntArray, target: Int) {
        if (picked.sum() == target) {
            combinations.add(picked.toList())
        }

        for (candidate in candidates) {
            if (picked.sum() + candidate > target) continue
            val nPicked = picked.toMutableList()
            nPicked.add(candidate)
            dfs4CombinationSum(nPicked.toIntArray(), candidates, target)
        }
    }

    @Tag("Medium")
    @Tag("Trees")
    @ParameterizedTest
    @MethodSource("levelOrderProvider")
    @DisplayName("Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).")
    fun levelOrder(root: TreeNode?, expected: List<List<Int>>) {
        if (root == null) return
        val q = LinkedList<Pair<TreeNode, Int>>()
        q.offer(root to 0)

        val mapByLevel = mutableMapOf<Int, MutableList<Int>>()
        while (q.isNotEmpty()) {
            val (curNode, level) = q.poll()
            val list = mapByLevel[level] ?: mutableListOf()
            list.add(curNode.`val`)
            mapByLevel[level] = list

            curNode.left?.let { q.offer(it to (level + 1)) }
            curNode.right?.let { q.offer(it to (level + 1)) }
        }
        val answer: List<List<Int>> = mapByLevel.entries.sortedBy { it.key }.map { it.value }
        println(answer)
    }

    companion object {
        var maxDepth = 0
        var combinations = mutableListOf<List<Int>>()

        @JvmStatic
        fun maxDepthProvider(): Stream<Arguments> {
            val node1 = TreeNode(7)
            val node2 = TreeNode(15)
            val node3 = TreeNode(20).also {
                it.left = node2
                it.right = node1
            }
            val node4  = TreeNode(9)
            val node5 = TreeNode(3).also {
                it.left = node4
                it.right = node3
            }
            return Stream.of(arguments(node5, 3))
        }

        @JvmStatic
        fun combinationSumProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(2,3,6,7), 7, listOf(listOf(2,2,3), listOf(7))),
                arguments(intArrayOf(2,3,5), 8, listOf(listOf(2,2,2,2), listOf(2,3,3), listOf(3,5))),
                arguments(intArrayOf(2), 1, listOf<List<Int>>()),
            )

        @JvmStatic
        fun levelOrderProvider(): Stream<Arguments> {
            val node1 = TreeNode(7)
            val node2 = TreeNode(15)
            val node3 = TreeNode(20).also {
                it.left = node2
                it.right = node1
            }
            val node4  = TreeNode(9)
            val node5 = TreeNode(3).also {
                it.left = node4
                it.right = node3
            }
            return Stream.of(arguments(node5, listOf(listOf(3), listOf(9, 20), listOf(15, 7))))
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}