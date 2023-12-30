package com.sushistack.hacker.rank

import kotlin.math.abs

class Greedy_8 {
    /**
     * 주어진 정수 배열 arr에서 임의의 두 원소 간의 최소 절대 차이를 구하는 문제입니다.
     *
     * 해결 방법:
     *
     * 배열 정렬: 배열 arr을 오름차순으로 정렬합니다. 정렬된 배열에서 인접한 원소 간의 차이가 최소 절대 차이가 될 가능성이 높습니다.
     * 최소 절대 차이 계산: 정렬된 배열을 순회하면서 인접한 두 원소 간의 절대 차이를 계산하고, 최소값을 갱신합니다.
     * 결과 반환: 최소 절대 차이를 반환합니다.
     */
    fun minimumAbsoluteDifference(arr: Array<Int>): Int {
        arr.sort()
        var minDiff = Int.MAX_VALUE

        for (i in 0 until arr.size - 1) {
            val diff = abs(arr[i] - arr[i + 1])
            if (diff < minDiff) {
                minDiff = diff
            }
        }

        return minDiff
    }

    /**
     * 레나는 중요한 코딩 대회를 앞두고 예선 대회를 치릅니다. 각 대회는 행운 점수 L과 중요도 T로 표현됩니다. 레나는 중요한 대회(T = 1)를 최대 k번 패배할 수 있으며, 이 경우 행운 점수가 L만큼 증가합니다. 중요하지 않은 대회(T = 0)는 항상 패배하며, 행운 점수가 L만큼 증가합니다. 레나가 얻을 수 있는 최대 행운 점수를 구하는 문제입니다.
     *
     * 해결 방법:
     *
     * 중요한 대회와 중요하지 않은 대회 분리: 입력받은 contests 배열을 순회하며 중요한 대회와 중요하지 않은 대회를 분리합니다.
     * 중요한 대회 정렬: 중요한 대회를 행운 점수 L을 기준으로 내림차순 정렬합니다.
     * 최대 행운 점수 계산:
     * 중요하지 않은 대회는 모두 패배하여 행운 점수를 증가시킵니다.
     * 중요한 대회를 순회하면서, k번까지는 패배하여 행운 점수를 증가시키고, 그 이후에는 승리하여 행운 점수를 감소시킵니다.
     * 결과 반환: 최대 행운 점수를 반환합니다.
     *
     * 핵심 아이디어:
     *
     * 중요한 대회를 행운 점수 기준으로 내림차순 정렬하여, 행운 점수가 높은 대회부터 패배하도록 합니다.
     * k번까지는 패배하고, 그 이후에는 승리하는 것이 최대 행운 점수를 얻는 방법입니다.
     * 중요하지 않은 대회는 항상 패배하여 행운 점수를 증가시킵니다.
     */
    fun luckBalance(k: Int, contests: Array<Array<Int>>): Int {
        var luck = 0
        val importantContests = mutableListOf<Int>()

        for (contest in contests) {
            val l = contest[0]
            val t = contest[1]

            if (t == 0) {
                luck += l
            } else {
                importantContests.add(l)
            }
        }

        importantContests.sortDescending()

        for (i in importantContests.indices) {
            if (i < k) {
                luck += importantContests[i]
            } else {
                luck -= importantContests[i]
            }
        }

        return luck
    }

    /**
     * 주어진 꽃 가격 배열 c와 친구 수 k에 대해, 모든 꽃을 구매하는 최소 비용을 계산하는 문제입니다. 각 친구는 이전에 구매한 꽃의 수에 1을 더한 값을 꽃 가격에 곱하여 지불합니다. 즉, 첫 번째 꽃은 원래 가격으로, 두 번째 꽃은 원래 가격의 2배로, 세 번째 꽃은 원래 가격의 3배로 구매합니다.
     *
     * 해결 방법:
     *
     * 꽃 가격 내림차순 정렬: 꽃 가격 배열 c를 내림차순으로 정렬합니다. 비싼 꽃부터 구매해야 최소 비용을 얻을 수 있습니다.
     * 친구별 구매 횟수 관리: 각 친구가 구매한 꽃의 수를 저장하는 배열 purchases를 초기화합니다.
     * 꽃 구매 및 비용 계산: 정렬된 꽃 가격 배열을 순회하면서 각 꽃을 친구들에게 분배하고 비용을 계산합니다.
     * 현재 꽃 가격에 purchases[친구 인덱스] + 1을 곱하여 구매 비용을 계산합니다.
     * purchases[친구 인덱스]를 1 증가시킵니다.
     * 친구 인덱스를 (친구 인덱스 + 1) % k로 갱신하여 다음 친구에게 꽃을 분배합니다.
     * 총 비용 반환: 계산된 총 비용을 반환합니다.
     */
    fun getMinimumCost(c: Array<Int>, k: Int): Int {
        c.sortDescending() // 꽃 가격 내림차순 정렬
        val purchases = IntArray(k) { 0 } // 친구별 구매 횟수 초기화
        var totalCost = 0
        var friendIndex = 0

        for (price in c) {
            totalCost += price * (purchases[friendIndex] + 1)
            purchases[friendIndex]++
            friendIndex = (friendIndex + 1) % k // 다음 친구 선택
        }

        return totalCost
    }

    /**
     * 문제 설명 및 해결 방법
     * 주어진 정수 배열 arr에서 k개의 원소를 선택하여 부분 배열을 만들 때, 부분 배열의 최대값과 최소값의 차이(unfairness)를 최소화하는 문제입니다.
     *
     * 해결 방법:
     *
     * 배열 정렬: 배열 arr을 오름차순으로 정렬합니다.
     * 부분 배열 순회: 정렬된 배열에서 k개의 원소를 선택할 수 있는 모든 부분 배열을 순회합니다.
     * unfairness 계산: 각 부분 배열의 최대값과 최소값의 차이(unfairness)를 계산합니다.
     * 최소 unfairness 갱신: 계산된 unfairness 중 최소값을 갱신합니다.
     * 결과 반환: 최소 unfairness를 반환합니다.
     */
    fun maxMin(k: Int, arr: Array<Int>): Int {
        arr.sort() // 배열 정렬
        var minUnfairness = Int.MAX_VALUE

        for (i in 0..arr.size - k) {
            val subArray = arr.sliceArray(i until i + k)
            val unfairness = subArray.last() - subArray.first()
            if (unfairness < minUnfairness) {
                minUnfairness = unfairness
            }
        }

        return minUnfairness
    }

}