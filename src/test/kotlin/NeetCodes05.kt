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

    @Tag("Easy")
    @Tag("1D DynamicProgramming")
    @ParameterizedTest
    @MethodSource("minCostClimbingStairsProvider")
    @DisplayName("""You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
        You can either start from the step with index 0, or the step with index 1.
        Return the minimum cost to reach the top of the floor.
    """)
    fun minCostClimbingStairs(cost: IntArray, expected: Int) {
        if (cost.size <= 2) {
            Assertions.assertThat(cost.min()).isEqualTo(expected)
            return
        }

        val dp = IntArray(1007) { 0 }
        dp[0] = cost[0]
        dp[1] = cost[1]

        for (i in 2..cost.lastIndex) {
            dp[i] = minOf(dp[i - 1], dp[i - 2]) + cost[i]
        }
        dp[cost.size] = minOf(dp[cost.lastIndex], dp[cost.lastIndex - 1])

        Assertions.assertThat(dp[cost.size]).isEqualTo(expected)
    }

    @Tag("Medium")
    @Tag("Graph")
    @ParameterizedTest
    @MethodSource("maxAreaOfIslandProvider")
    @DisplayName("""You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
        The area of an island is the number of cells with a value 1 in the island.
        Return the maximum area of an island in grid. If there is no island, return 0.
    """)
    fun maxAreaOfIsland(grid: Array<IntArray>, expected: Int) {
        var maxLand = 0
        val visited = grid.map { it.map { false }.toMutableList() }
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                maxLand = maxOf(maxLand, bfs(grid, r, c, visited))
            }
        }
        Assertions.assertThat(maxLand).isEqualTo(expected)
    }

    private fun bfs(grid: Array<IntArray>, r: Int, c: Int, visited: List<MutableList<Boolean>>): Int {
        if (grid[r][c] == 0 || visited[r][c]) return 0
        val q = LinkedList<Point>()
        q.offer(Point(r, c))
        visited[r][c] = true

        var land = 0
        val nexts = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        while (q.isNotEmpty()) {
            val cur = q.poll()
            land += 1

            for (next in nexts) {
                val nr = cur.r + next.first
                var nc = cur.c + next.second
                if (nr < 0 || nr > grid.lastIndex || nc < 0 || nc > grid[0].lastIndex) continue
                if (grid[nr][nc] == 0 || visited[nr][nc]) continue

                visited[nr][nc] = true
                q.offer(Point(nr, nc))
            }
        }
        return land
    }

    data class Point(val r: Int, val c: Int)

    @Tag("Easy")
    @Tag("Heap")
    @ParameterizedTest
    @MethodSource("lastStoneWeightProvider")
    @DisplayName("""You are given an array of integers stones where stones[i] is the weight of the ith stone.
        We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together.
        Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:
        If x == y, both stones are destroyed, and
        If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
        At the end of the game, there is at most one stone left. 
        Return the weight of the last remaining stone. If there are no stones left, return 0.
    """)
    fun lastStoneWeight(stones: IntArray, expected: Int) {
        val pq = PriorityQueue<Int> { o1, o2 -> o2 - o1 }
        for (i in stones.indices) {
            pq.add(stones[i])
        }

        var answer = 0
        var cur: Int
        var next: Int
        while (pq.isNotEmpty()) {
            cur = pq.poll()
            if (pq.isEmpty()) {
                answer = cur
                break
            }
            next = pq.poll()
            pq.add(cur - next)
        }

        Assertions.assertThat(answer).isEqualTo(expected)
    }

    @Tag("Medium")
    @Tag("Interval")
    @ParameterizedTest
    @MethodSource("mergeProvider")
    @DisplayName("""Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
        and return an array of the non-overlapping intervals that cover all the intervals in the input
    """)
    fun merge(intervals: Array<IntArray>, expected: Array<IntArray>) {
        val sorted = intervals.sortedBy { it[0] }.map { Range(it[0], it[1]) }

        for (i in 0..<sorted.size - 1) {
            val cur = sorted[i]
            val next = sorted[i + 1]
            if (cur.end >= next.start) {
                val min = minOf(cur.start, next.start)
                val max = maxOf(cur.end, next.end)

                cur.start = min
                cur.end = min
                next.start = min
                next.end = max
                cur.remove = true
            }
        }
        val answer = sorted
            .filter { range -> !range.remove }
            .map { intArrayOf(it.start, it.end) }
            .toTypedArray()

        Assertions.assertThat(answer).isEqualTo(expected)
    }

    data class Range(var start: Int, var end: Int, var remove: Boolean = false)

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

        @JvmStatic
        fun minCostClimbingStairsProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(10,15,20), 15),
                arguments(intArrayOf(1,100,1,1,1,100,1,1,100,1), 6)
            )

        @JvmStatic
        fun maxAreaOfIslandProvider(): Stream<Arguments> =
            Stream.of(
                arguments(
                    arrayOf(
                        intArrayOf(0,0,1,0,0,0,0,1,0,0,0,0,0), intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0),
                        intArrayOf(0,1,1,0,1,0,0,0,0,0,0,0,0), intArrayOf(0,1,0,0,1,1,0,0,1,0,1,0,0),
                        intArrayOf(0,1,0,0,1,1,0,0,1,1,1,0,0), intArrayOf(0,0,0,0,0,0,0,0,0,0,1,0,0),
                        intArrayOf(0,0,0,0,0,0,0,1,1,1,0,0,0), intArrayOf(0,0,0,0,0,0,0,1,1,0,0,0,0)
                    ),
                    6
                ),
                arguments(
                    arrayOf(intArrayOf(0,0,0,0,0,0,0,0)),
                    0
                ),
                arguments(
                    arrayOf(
                        intArrayOf(1,1,0,0,0), intArrayOf(1,1,0,0,0),
                        intArrayOf(0,0,0,1,1), intArrayOf(0,0,0,1,1)
                    ),
                    4
                )
            )

        @JvmStatic
        fun lastStoneWeightProvider(): Stream<Arguments> =
            Stream.of(
                arguments(intArrayOf(2,7,4,1,8,1), 1),
                arguments(intArrayOf(1), 1)
            )

        @JvmStatic
        fun mergeProvider(): Stream<Arguments> =
            Stream.of(
                arguments(
                    arrayOf(intArrayOf(1,3), intArrayOf(2,6), intArrayOf(8,10), intArrayOf(15,18)),
                    arrayOf(intArrayOf(1,6), intArrayOf(8,10), intArrayOf(15,18))
                ),
                arguments(
                    arrayOf(intArrayOf(1,4), intArrayOf(4,5)),
                    arrayOf(intArrayOf(1,5)),
                ),
                arguments(
                    arrayOf(intArrayOf(1,4), intArrayOf(0,2), intArrayOf(3,5)),
                    arrayOf(intArrayOf(0,5)),
                )
            )
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}