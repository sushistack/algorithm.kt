package com.sushistack.hacker.rank

class DynamicProgramming_10 {
    /**
     * 주어진 정수 배열 arr에서 인접하지 않은 원소들의 부분집합 중 최대 합을 구하는 문제입니다. 모든 원소가 음수일 경우 최대 합은 0이 될 수 있습니다.
     *
     * 해결 방법:
     *
     * 동적 프로그래밍 (Dynamic Programming): dp 배열을 사용하여 부분 문제를 해결하고 결과를 저장합니다.
     * dp[i]는 arr 배열의 처음 i개 원소에 대한 최대 부분집합 합을 나타냅니다.
     * 배열 초기화:
     * dp[0]은 arr[0]과 0 중 큰 값으로 초기화합니다.
     * dp[1]은 arr[0], arr[1] 중 큰 값으로 초기화합니다.
     * 배열 채우기: arr 배열의 각 원소를 순회하면서 dp 배열을 채웁니다.
     * dp[i]는 dp[i - 1] (현재 원소를 포함하지 않는 경우)과 dp[i - 2] + arr[i] (현재 원소를 포함하는 경우) 중 큰 값으로 갱신합니다.
     * 음수 원소를 처리하기 위해 dp[i]가 음수가 되지 않도록 주의합니다.
     * 결과 반환: dp[arr.size - 1]을 반환합니다.
     */
    fun maxSubsetSum(arr: Array<Int>): Int {
        if (arr.isEmpty()) return 0
        if (arr.size == 1) return maxOf(0, arr[0])

        val dp = IntArray(arr.size)
        dp[0] = maxOf(0, arr[0])
        dp[1] = maxOf(dp[0], arr[1])

        for (i in 2 until arr.size) {
            dp[i] = maxOf(dp[i - 1], dp[i - 2] + arr[i], arr[i])
        }

        return dp[arr.size - 1]
    }



    /**
     * 문제 이해
     * 입력:
     * q = 1 (쿼리 수)
     * 첫 번째 쿼리: a = "daBcd", b = "ABC"
     * 목표: a를 조작해서 b와 동일하게 만들 수 있는지 확인.
     * 조작 규칙:
     * 소문자를 대문자로 변환 가능 (예: 'a' → 'A').
     * 소문자를 삭제 가능.
     * 대문자는 변경하거나 삭제 불가.
     * 단계별 풀이
     * 초기 상태:
     * a = "daBcd", b = "ABC"
     * a에는 소문자(d, a, c, d)와 대문자(B)가 포함됨.
     * b는 모두 대문자(A, B, C).
     * 조작 과정:
     * a를 b와 일치시키기 위해 다음과 같이 조작:
     * d (소문자): 삭제 → "aBcd"
     * a (소문자): A로 변환 → "ABcd"
     * B (대문자): 이미 B이므로 그대로 → "ABcd"
     * c (소문자): C로 변환 → "ABCd"
     * d (소문자): 삭제 → "ABC"
     * 결과: "ABC"가 되어 b = "ABC"와 일치.
     * 결론:
     * a를 조작해서 b와 동일하게 만들 수 있으므로 "YES" 출력.
     * 동적 프로그래밍 접근
     * 이 문제를 체계적으로 풀기 위해 DP를 사용합니다.
     * dp[i][j]: a의 처음 i개 문자로 b의 처음 j개 문자를 만들 수 있는지.
     * 점화식을 통해 각 단계에서 가능한 경우를 확인합니다.
     */
    fun abbreviation(a: String, b: String): String {
        val n = a.length
        val m = b.length

        // DP 테이블 초기화
        val dp = Array(n + 1) { BooleanArray(m + 1) }
        dp[0][0] = true // 빈 문자열 매칭 가능

        // a의 접두사가 모두 소문자인 경우 삭제 가능
        for (i in 1..n) {
            if (a[i - 1].isLowerCase()) {
                dp[i][0] = dp[i - 1][0]
            }
        }

        // DP 채우기
        for (i in 1..n) {
            for (j in 1..m) {
                val ca = a[i - 1] // a의 현재 문자
                val cb = b[j - 1] // b의 현재 문자

                if (ca.isUpperCase()) {
                    // a의 문자가 대문자면 b와 반드시 일치해야 함
                    if (ca == cb) {
                        dp[i][j] = dp[i - 1][j - 1]
                    }
                } else {
                    // a의 문자가 소문자인 경우
                    // 1. 삭제
                    dp[i][j] = dp[i - 1][j]
                    // 2. 대문자로 변환해서 매칭
                    if (ca.uppercaseChar() == cb) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j - 1]
                    }
                }
            }
        }

        return if (dp[n][m]) "YES" else "NO"
    }

    /**
     * 이 문제는 "Candies"라는 이름의 알고리즘 문제로, 동적 프로그래밍(DP)을 사용해 해결할 수 있습니다. 아래에서 한국어로 풀이를 설명하고, Kotlin 코드로 구현하겠습니다.
     *
     * 한국어 풀이 설명
     * 문제 이해
     * 상황: 유치원 교사 Alice는 학생들에게 사탕을 나눠주려 합니다. 학생들은 일렬로 앉아 있고, 각 학생은 성적에 따라 평점(rating)을 가지고 있습니다.
     * 규칙:
     * 모든 학생은 최소 1개의 사탕을 받아야 합니다.
     * 인접한 두 학생의 평점이 다를 경우, 평점이 더 높은 학생이 더 많은 사탕을 받아야 합니다.
     * 목표: Alice가 구매해야 하는 최소 사탕 개수를 구하는 것.
     * 예제
     * 입력: n = 3, arr = [1, 2, 2]
     * 가능한 사탕 분배:
     * 학생 1 (평점 1): 1개
     * 학생 2 (평점 2): 2개 (1보다 높으므로 더 많이)
     * 학생 3 (평점 2): 1개 (2와 같으므로 최소 1개면 충분)
     * 결과: 1 + 2 + 1 = 4 (최소 사탕 수)
     * 접근 방식
     * 아이디어:
     * 평점이 증가하거나 감소하는 방향에 따라 사탕 개수를 조정해야 합니다.
     * 왼쪽에서 오른쪽으로, 그리고 오른쪽에서 왼쪽으로 두 번 순회하며 각 학생이 받아야 할 최소 사탕 수를 계산합니다.
     * 동적 프로그래밍:
     * 각 학생의 사탕 수를 배열에 저장하고, 인접한 학생 간의 조건을 만족하도록 갱신합니다.
     * 풀이 과정
     * 초기화:
     * 모든 학생에게 최소 1개의 사탕을 할당.
     * 왼쪽 → 오른쪽 순회:
     * 현재 학생의 평점이 왼쪽 학생보다 높으면, 왼쪽 학생의 사탕 수 + 1을 할당.
     * 오른쪽 → 왼쪽 순회:
     * 현재 학생의 평점이 오른쪽 학생보다 높으면, 오른쪽 학생의 사탕 수 + 1과 현재 값을 비교해 더 큰 값을 선택.
     * 합산:
     * 모든 학생의 사탕 수를 합해 최소 총합을 구함.
     * 예제 풀이 (n = 3, arr = [1, 2, 2])
     * 초기: [1, 1, 1]
     * 왼쪽 → 오른쪽:
     * arr[0] = 1, arr[1] = 2: 1 < 2이므로 [1, 2, 1]
     * arr[1] = 2, arr[2] = 2: 같으므로 [1, 2, 1]
     * 오른쪽 → 왼쪽:
     * arr[2] = 2, arr[1] = 2: 같으므로 변화 없음 [1, 2, 1]
     * arr[1] = 2, arr[0] = 1: 2 > 1이지만 이미 조건 만족 [1, 2, 1]
     * 결과: 1 + 2 + 1 = 4
     */

    fun candies(n: Int, arr: Array<Int>): Long {
        val candies = LongArray(n) { 1L }

        for (i in 1 until n) {
            if (arr[i] > arr[i - 1]) {
                candies[i] = candies[i - 1] + 1
            }
        }

        for (i in n - 2 downTo 0) {
            if (arr[i] > arr[i + 1]) {
                candies[i] = maxOf(candies[i], candies[i + 1] + 1)
            }
        }

        return candies.sum()
    }
}