package com.sushistack.hacker.rank

class Arrays_2 {
    /**
     * 주어진 **2D 배열(6x6)**에서 hourglass(모래시계) 형태를 찾아 합계를 계산하는 문제.
     * 모래시계의 형태는 다음과 같음:
     *
     *  해결 방법
     * 	1.	2D 배열을 순회하면서 모래시계 형태를 찾음
     * 	•	arr[i][j]을 중심으로 (위, 가운데, 아래) 값 합산
     * 	2.	각 모래시계 합 중에서 최댓값 찾기
     * 	3.	6x6 배열에서 모래시계는 (4x4 = 16개)만 존재
     * 	•	즉, i ∈ [0,3] & j ∈ [0,3] 인덱스에서 탐색 가능
     */
    fun hourglassSum(arr: Array<Array<Int>>): Int {
        var maxSum = Int.MIN_VALUE

        for (i in 0..3) {
            for (j in 0..3) {
                val sum = arr[i][j] + arr[i][j+1] + arr[i][j+2] +  // 위
                        arr[i+1][j+1] +                         // 중간
                        arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2] // 아래

                maxSum = maxOf(maxSum, sum) // 최댓값 갱신
            }
        }

        return maxSum
    }

    /**
     * 배열을 왼쪽으로 d번 회전시키는 문제.
     * 	•	배열의 가장 왼쪽 요소는 사라지고, 맨 오른쪽 끝으로 이동함.
     * 	해결 방법
     * 	1.	배열을 d번 직접 회전하면 비효율적 → 슬라이싱 이용
     * 	2.	배열을 d만큼 회전하면 앞부분을 잘라서 뒤로 붙이면 됨
     * 	•	newArray = a[d:] + a[:d]
     * 	•	즉, a를 d 인덱스 기준으로 나누고 순서를 바꾸는 것
     */
    fun rotLeft(a: Array<Int>, d: Int): Array<Int> {
        val n = a.size
        val rotatedArray = a.copyOfRange(d, n) + a.copyOfRange(0, d)
        return rotatedArray
    }

    /**
     * 롤러코스터 줄에서 사람들은 자신의 원래 위치를 나타내는 스티커를 붙이고 있음.
     * 각 사람은 앞에 있는 사람을 뇌물로 매수하여 위치를 바꿀 수 있음.
     * 단, 한 사람이 최대 2명까지만 매수 가능!
     *
     * 📌 목표:
     * 	•	최소한의 뇌물로 현재 주어진 줄의 상태를 만들 수 있는 뇌물 횟수 출력
     * 	•	만약 한 사람이 3번 이상 뇌물을 줬다면 "Too chaotic" 출력
     * 	뇌물 횟수 세는 방법
     * 	1.	각 사람 q[i]의 원래 위치는 q[i] - 1
     * 	•	만약 **현재 위치 i**보다 원래 위치가 2 이상 앞이라면 “Too chaotic” 출력
     * 	2.	자기 앞의 max(0, q[i] - 2)부터 i-1까지 검사
     * 	•	자기 앞에 자신보다 큰 숫자가 몇 개 있는지 세기
     * 	•	큰 숫자 개수 = 자신을 밀어낸 사람들의 수(뇌물 횟수)
     */
    fun minimumBribes(q: Array<Int>) {
        var bribes = 0

        for (i in q.indices) {
            if (q[i] - (i + 1) > 2) {
                println("Too chaotic")
                return
            }

            // 자신이 갈 수 있는 최소 인덱스부터 현재 인덱스까지 체크
            for (j in maxOf(0, q[i] - 2) until i) {
                if (q[j] > q[i]) {
                    bribes++
                }
            }
        }

        println(bribes)
    }

    /**
     * 정렬되지 않은 배열 arr이 주어지고, 배열에는 1부터 n까지의 연속된 숫자가 무작위로 섞여 있음.
     * 주어진 배열을 오름차순으로 정렬하는 데 필요한 최소한의 swap 횟수를 구하는 문제.
     *
     * 해결 방법 (싸이클 탐색 활용)
     *
     * 배열이 [1, 2, 3, ..., n]의 연속된 정수로 이루어져 있기 때문에 싸이클 정렬 (Cycle Sort) 방식을 사용하면 효율적으로 해결할 수 있음.
     *
     * 🚀 핵심 아이디어
     * 	1.	올바른 위치에 있지 않은 요소를 찾는다.
     * 	2.	현재 위치의 요소를 원래 있어야 하는 위치로 swap.
     * 	3.	이미 방문한 노드는 다시 방문하지 않도록 체크.
     * 	4.	싸이클을 찾고 swap을 카운트.
     * 	•	하나의 싸이클에서 필요한 swap 수는 (싸이클 길이 - 1)
     */
    fun minimumSwaps(arr: Array<Int>): Int {
        var swaps = 0
        val visited = BooleanArray(arr.size) // 방문 여부 체크

        for (i in arr.indices) {
            // 이미 방문했거나, 현재 위치가 올바른 경우 스킵
            if (visited[i] || arr[i] == i + 1) continue

            var cycleSize = 0
            var j = i

            // 싸이클을 찾으며 swap 카운트
            while (!visited[j]) {
                visited[j] = true
                j = arr[j] - 1  // 값이 원래 있어야 할 인덱스로 이동
                cycleSize++
            }

            // 싸이클 크기가 2 이상이면 (cycleSize - 1)만큼 swap 필요
            if (cycleSize > 1) swaps += (cycleSize - 1)
        }

        return swaps
    }
}