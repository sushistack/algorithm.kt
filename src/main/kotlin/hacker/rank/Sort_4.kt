package com.sushistack.hacker.rank

class Sort_4 {

    /**
     * 버블 정렬 문제 설명 및 해결 방법
     * 주어진 정수 배열을 오름차순으로 정렬하는 버블 정렬 알고리즘을 구현하고, 정렬 과정에서 발생한 스왑 횟수, 정렬된 배열의 첫 번째 원소, 마지막 원소를 출력하는 문제입니다.
     *
     * 버블 정렬 알고리즘:
     *
     * 배열의 첫 번째 원소부터 마지막 원소까지 순회합니다.
     * 현재 원소와 다음 원소를 비교합니다.
     * 현재 원소가 다음 원소보다 크면 두 원소를 교환(스왑)합니다.
     * 배열의 마지막 원소까지 위 과정을 반복합니다.
     * 배열의 마지막 원소는 정렬된 상태가 되므로, 다음 순회에서는 마지막 원소를 제외하고 반복합니다.
     * 더 이상 스왑이 발생하지 않을 때까지 위 과정을 반복합니다.
     * 해결 방법:
     *
     * 스왑 횟수를 저장할 변수 numSwaps를 초기화합니다.
     * 배열을 순회하면서 버블 정렬 알고리즘을 구현합니다.
     * 원소를 교환할 때마다 numSwaps를 1 증가시킵니다.
     * 정렬이 완료되면 numSwaps, 정렬된 배열의 첫 번째 원소, 마지막 원소를 출력합니다.
     */
    fun countSwaps(a: Array<Int>) {
        var numSwaps = 0
        val n = a.size

        for (i in 0 until n) {
            for (j in 0 until n - 1) {
                if (a[j] > a[j + 1]) {
                    val temp = a[j]
                    a[j] = a[j + 1]
                    a[j + 1] = temp
                    numSwaps++
                }
            }
        }

        println("Array is sorted in $numSwaps swaps.")
        println("First Element: ${a[0]}")
        println("Last Element: ${a[n - 1]}")
    }



    /**
     * 문제 설명 및 해결 방법
     * 마크는 정해진 예산 k 내에서 최대한 많은 장난감을 구매하려고 합니다. 장난감들의 가격이 배열 prices로 주어졌을 때, 마크가 구매할 수 있는 최대 장난감 수를 구하는 문제입니다. 각 장난감은 한 번만 구매할 수 있습니다.
     *
     * 해결 방법:
     *
     * 정렬: 장난감 가격 배열 prices를 오름차순으로 정렬합니다. 이렇게 하면 가장 저렴한 장난감부터 구매할 수 있습니다.
     * 순회 및 구매: 정렬된 배열을 순회하면서, 현재 장난감 가격을 예산 k에서 빼고, 구매한 장난감 수를 증가시킵니다.
     * 예산 초과 확인: 예산 k가 0보다 작아지면 순회를 종료합니다.
     * 결과 반환: 구매한 장난감 수를 반환합니다.
     */
    fun maximumToys(prices: Array<Int>, k: Int): Int {
        val sortedPrices = prices.sortedArray() // 가격을 오름차순으로 정렬
        var budget = k
        var toyCount = 0

        for (price in sortedPrices) {
            if (budget >= price) {
                budget -= price
                toyCount++
            } else {
                break // 예산 초과 시 종료
            }
        }

        return toyCount
    }

    /**
     * 주어진 일별 지출액 배열 expenditure와 추적 기간 d에 대해, 각 날짜의 지출액이 이전 d일 동안의 중간값의 두 배 이상일 경우 알림을 보내는 횟수를 계산하는 문제입니다.
     *
     * 해결 방법:
     *
     * 중간값 계산 함수: 이전 d일 동안의 지출액 배열에서 중간값을 계산하는 함수를 구현합니다.
     * 배열을 정렬합니다.
     * d가 홀수이면 중간 인덱스의 값을 반환합니다.
     * d가 짝수이면 중간 두 값의 평균을 반환합니다.
     * 알림 횟수 계산:
     * 알림 횟수를 저장할 변수 notifications를 초기화합니다.
     * d일부터 배열의 마지막 날짜까지 순회합니다.
     * 이전 d일 동안의 지출액 배열을 생성하고 중간값을 계산합니다.
     * 현재 날짜의 지출액이 중간값의 두 배 이상이면 notifications를 1 증가시킵니다.
     * 결과 반환: notifications를 반환합니다.
     * 효율적인 방법
     *
     * 매번 중간 값을 계산하기 위해 배열을 정렬하는 것은 비효율적입니다. 계수 정렬(Counting Sort)을 사용하여 중간값을 빠르게 계산하는 것이 좋습니다.
     *
     * 계수 정렬 배열: 지출액의 최대값을 기준으로 계수 정렬 배열을 생성합니다.
     * 이전 d일 데이터 저장: 이전 d일 동안의 지출액 데이터를 계수 정렬 배열에 저장합니다.
     * 중간값 계산 (계수 정렬 활용):
     * 계수 정렬 배열을 순회하면서 중간값을 계산합니다.
     * d가 홀수이면 중간 인덱스의 값을 찾습니다.
     * d가 짝수이면 중간 두 값의 평균을 찾습니다.
     * 알림 횟수 계산:
     * d일부터 배열의 마지막 날짜까지 순회하면서, 현재 날짜의 지출액이 중간값의 두 배 이상이면 알림 횟수를 증가시킵니다.
     * 다음 날짜 계산을 위해 이전 d일 데이터에서 첫 번째 날짜의 지출액을 제거하고, 현재 날짜의 지출액을 추가합니다.
     */
    fun activityNotifications(expenditure: Array<Int>, d: Int): Int {
        var notifications = 0
        val count = IntArray(201) // 지출액 최대값은 200

        for (i in 0 until d) {
            count[expenditure[i]]++
        }

        for (i in d until expenditure.size) {
            val median = getMedian(count, d)
            if (expenditure[i] >= 2 * median) {
                notifications++
            }
            count[expenditure[i - d]]--
            count[expenditure[i]]++
        }

        return notifications
    }

    fun getMedian(count: IntArray, d: Int): Double {
        var median = 0.0
        var sum = 0
        val mid = d / 2

        if (d % 2 == 1) {
            for (i in count.indices) {
                sum += count[i]
                if (sum > mid) {
                    median = i.toDouble()
                    break
                }
            }
        } else {
            var first = -1
            var second = -1
            for (i in count.indices) {
                sum += count[i]
                if (first == -1 && sum >= mid) {
                    first = i
                }
                if (sum >= mid + 1) {
                    second = i
                    break
                }
            }
            median = (first + second) / 2.0
        }

        return median
    }
}