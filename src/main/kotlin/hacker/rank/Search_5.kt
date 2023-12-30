package com.sushistack.hacker.rank

class Search_5 {
    /**
     * 해커랭크의 "Ice Cream Parlor" 문제는 다음과 같습니다.
     *
     * 문제 설명:
     *
     * 한 아이스크림 가게에 여러 종류의 아이스크림이 있고, 각 아이스크림은 가격을 가지고 있습니다. 두 명의 아이가 가게에 와서 두 개의 아이스크림을 사려고 합니다.
     * 이 두 아이스크림의 가격의 합은 아이들이 가진 돈과 같아야 합니다.
     * 아이스크림 가격의 배열과 아이들이 가진 돈이 주어질 때, 두 아이스크림의 인덱스를 찾아 출력해야 합니다.
     */
    fun whatFlavors(cost: Array<Int>, money: Int) {
        val map = mutableMapOf<Int, Int>()
        for (i in cost.indices) {
            val complement = money - cost[i]
            if (map.containsKey(complement)) {
                val index1 = map[complement]!! + 1
                val index2 = i + 1
                if (index1 < index2) {
                    println("$index1 $index2")
                } else {
                    println("$index2 $index1")
                }
                return
            }
            map[cost[i]] = i
        }
    }

    /**
     * 주어진 이진 트리에 대해, 특정 깊이의 노드들의 자식 노드를 교환하는 작업을 수행하고, 교환 후의 트리를 중위 순회(in-order traversal)한 결과를 출력하는 문제입니다.
     *
     * 문제 이해:
     *
     * 이진 트리: 노드와 자식 노드 정보로 주어집니다.
     * 교환 연산: 특정 깊이 k의 배수(k, 2k, 3k, ...)에 해당하는 노드들의 자식 노드를 교환합니다.
     * 중위 순회: 교환 연산 후, 트리를 중위 순회하여 노드 인덱스를 출력합니다.
     * 해결 방법:
     *
     * 트리 생성: 입력받은 노드 정보를 사용하여 이진 트리를 생성합니다.
     * 교환 연산 함수: 주어진 깊이 k에 대해 트리의 노드들을 순회하며, k의 배수에 해당하는 깊이의 노드들의 자식 노드를 교환합니다.
     * 중위 순회 함수: 트리를 중위 순회하여 노드 인덱스를 리스트에 저장합니다.
     * 쿼리 처리: 주어진 쿼리 queries를 순회하면서, 각 쿼리에 대해 교환 연산을 수행하고, 중위 순회 결과를 저장합니다.
     * 결과 반환: 중위 순회 결과를 2차원 배열 형태로 반환합니다.
     */
    class Node(val index: Int, var left: Node?, var right: Node?)

    fun swapNodes(indexes: Array<Array<Int>>, queries: Array<Int>): Array<Array<Int>> {
        val root = Node(1, null, null)
        val nodes = mutableMapOf(1 to root)

        for (i in indexes.indices) {
            val node = nodes[i + 1]!!
            val leftIndex = indexes[i][0]
            val rightIndex = indexes[i][1]

            if (leftIndex != -1) {
                node.left = Node(leftIndex, null, null)
                nodes[leftIndex] = node.left!!
            }
            if (rightIndex != -1) {
                node.right = Node(rightIndex, null, null)
                nodes[rightIndex] = node.right!!
            }
        }

        val result = mutableListOf<Array<Int>>()

        for (k in queries) {
            swap(root, k, 1)
            val inOrderResult = inOrderTraversal(root)
            result.add(inOrderResult.toTypedArray())
        }

        return result.toTypedArray()
    }

    fun swap(node: Node?, k: Int, depth: Int) {
        if (node == null) return

        if (depth % k == 0) {
            val temp = node.left
            node.left = node.right
            node.right = temp
        }

        swap(node.left, k, depth + 1)
        swap(node.right, k, depth + 1)
    }

    fun inOrderTraversal(node: Node?): List<Int> {
        val result = mutableListOf<Int>()
        inOrder(node, result)
        return result
    }

    fun inOrder(node: Node?, result: MutableList<Int>) {
        if (node == null) return

        inOrder(node.left, result)
        result.add(node.index)
        inOrder(node.right, result)
    }

    /**
     * 주어진 정수 배열 arr과 목표 차이 값 k에 대해, 배열 내 두 원소의 차이가 k와 같은 쌍의 개수를 찾는 문제입니다.
     *
     * 해결 방법:
     *
     * 해시 집합(HashSet) 사용: 배열의 모든 원소를 해시 집합에 저장합니다.
     * 배열 순회 및 쌍 찾기: 배열을 순회하면서 각 원소 arr[i]에 대해 arr[i] + k와 arr[i] - k가 해시 집합에 존재하는지 확인합니다.
     * 쌍의 개수 계산: 해시 집합에 존재하는 경우, 쌍의 개수를 증가시킵니다.
     * 결과 반환: 계산된 쌍의 개수를 반환합니다.
     */
    fun pairs(k: Int, arr: Array<Int>): Int {
        val set = arr.toHashSet()
        var count = 0

        for (num in arr) {
            if (set.contains(num + k)) {
                count++
            }
        }

        return count
    }

    /**
     * 주어진 세 개의 정수 배열 a, b, c에서 다음 조건을 만족하는 서로 다른 세 개의 원소 (p, q, r)의 개수를 찾는 문제입니다.
     *
     * p는 배열 a의 원소입니다.
     * q는 배열 b의 원소입니다.
     * r는 배열 c의 원소입니다.
     * p <= q이고 r <= q입니다.
     * 해결 방법:
     *
     * 배열 중복 제거 및 정렬: 각 배열에서 중복을 제거하고 오름차순으로 정렬합니다. 중복 제거는 해시 집합(HashSet)을 사용하고, 정렬은 sortedArray()를 사용합니다.
     * 배열 b 순회: 배열 b의 각 원소 q에 대해 다음을 수행합니다.
     * 배열 a 및 c 순회: 배열 a와 c에서 q보다 작거나 같은 원소의 개수를 각각 계산합니다.
     * 이진 탐색(binarySearch())을 사용하여 효율적으로 개수를 계산합니다.
     * 삼중쌍 개수 계산: q에 대해 계산된 a와 c의 개수를 곱하여 삼중쌍 개수에 더합니다.
     * 결과 반환: 계산된 삼중쌍 개수를 반환합니다.
     */
    fun triplets(a: Array<Int>, b: Array<Int>, c: Array<Int>): Long {
        val aDistinct = a.toHashSet().toTypedArray().sortedArray()
        val bDistinct = b.toHashSet().toTypedArray().sortedArray()
        val cDistinct = c.toHashSet().toTypedArray().sortedArray()

        var count = 0L

        for (q in bDistinct) {
            val aCount = aDistinct.binarySearch(q).let { if (it < 0) -it - 1 else it + 1 }
            val cCount = cDistinct.binarySearch(q).let { if (it < 0) -it - 1 else it + 1 }

            count += aCount.toLong() * cCount.toLong()
        }

        return count
    }

    /**
     * 주어진 기계 배열 machines와 목표 생산량 goal에 대해, 모든 기계가 동시에 작동할 때 목표 생산량을 달성하는 데 필요한 최소 일수를 구하는 문제입니다. 각 기계는 고정된 일수 동안 하나의 아이템을 생산합니다.
     *
     * 해결 방법:
     *
     * 이분 탐색 (Binary Search): 생산에 필요한 최소 일수를 이분 탐색으로 찾습니다.
     * 탐색 범위: 최소 일수는 1일, 최대 일수는 machines 배열의 최대값 * goal입니다.
     * 중간값 mid를 계산하고, mid일 동안 생산 가능한 아이템 수를 계산합니다.
     * 생산 가능한 아이템 수가 goal보다 크거나 같으면, 최대 일수를 mid로 줄입니다.
     * 생산 가능한 아이템 수가 goal보다 작으면, 최소 일수를 mid + 1로 늘립니다.
     * 생산 가능한 아이템 수 계산 함수: 주어진 일수 days 동안 생산 가능한 아이템 수를 계산합니다.
     * machines 배열을 순회하면서 각 기계가 days 동안 생산 가능한 아이템 수를 계산하여 총 생산량에 더합니다.
     * 결과 반환: 이분 탐색으로 찾은 최소 일수를 반환합니다.
     */
    fun minTime(machines: Array<Long>, goal: Long): Long {
        var left = 1L
        var right = machines.maxOrNull()!!.toLong() * goal
        var result = right

        while (left <= right) {
            val mid = left + (right - left) / 2
            val itemsProduced = calculateItemsProduced(machines, mid)

            if (itemsProduced >= goal) {
                result = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return result
    }

    fun calculateItemsProduced(machines: Array<Long>, days: Long): Long {
        var itemsProduced = 0L
        for (machine in machines) {
            itemsProduced += days / machine
        }
        return itemsProduced
    }

}