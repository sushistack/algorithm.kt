import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
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

    fun depthDfs(node: TreeNode?, depth: Int) {
        if (node == null) {
            maxDepth = maxOf(depth, maxDepth)
            return
        }

        depthDfs(node.left, depth + 1)
        depthDfs(node.right, depth + 1)
    }

    companion object {
        var maxDepth = 0

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

    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}