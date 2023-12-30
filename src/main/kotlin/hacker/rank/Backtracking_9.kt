package com.sushistack.hacker.rank

class Backtracking_9 {
    /**
     * 주어진 정수 n에 대해, 피보나치 수열의 n번째 항을 반환하는 문제입니다. 피보나치 수열은 다음과 같이 정의됩니다.
     *
     * F(0) = 0
     * F(1) = 1
     * F(n) = F(n-1) + F(n-2) (n > 1)
     * 해결 방법:
     *
     * 재귀 함수 구현: 피보나치 수열의 정의를 그대로 구현한 재귀 함수를 작성합니다.
     * 기저 사례 처리: n이 0 또는 1인 경우를 기저 사례로 처리합니다.
     * 재귀 호출: n이 2 이상인 경우, F(n-1)과 F(n-2)를 재귀적으로 호출하여 더한 값을 반환합니다.
     */
    fun fibonacci(n: Int): Int {
        if (n <= 1) {
            return n
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2)
        }
    }

    /**
     * 주어진 계단의 높이 n에 대해, 1, 2, 또는 3 걸음으로 계단을 오르는 방법의 수를 구하는 문제입니다. 결과는 1000000007로 나눈 나머지를 반환해야 합니다.
     *
     * 해결 방법:
     *
     * 재귀 함수 구현: 계단을 오르는 방법의 수를 계산하는 재귀 함수를 작성합니다.
     * 기저 사례 처리:
     * n이 0인 경우, 1가지 방법이 있습니다 (아무것도 안 하는 경우).
     * n이 음수인 경우, 0가지 방법이 있습니다.
     * 재귀 호출: n이 양수인 경우, n-1, n-2, n-3 계단을 오르는 방법의 수를 재귀적으로 호출하여 더한 값을 반환합니다.
     * 모듈러 연산: 결과를 1000000007로 나눈 나머지를 반환합니다.
     * 메모이제이션 (선택적): 재귀 호출 시 중복 계산을 줄이기 위해 메모이제이션을 사용합니다.
     */
    fun stepPerms(n: Int): Long {
        val MOD = 10000000007L // 문제에서 요구하는 모듈러 상수
        // 메모이제이션을 위한 가변 맵
        val dp = mutableMapOf<Int, Long>()

        // 내부 재귀 함수
        fun climb(stairs: Int): Long {
            // 기본 케이스
            if (stairs == 0) return 1L // 계단을 다 올라간 경우 (유효한 방법)
            if (stairs < 0) return 0L  // 음수 계단은 불가능

            // 이미 계산된 값이 있으면 반환
            dp[stairs]?.let { return it }

            // 1, 2, 3 단계를 사용한 방법 계산
            val ways = (climb(stairs - 1) +  // 1단계
                    climb(stairs - 2) +   // 2단계
                    climb(stairs - 3)) % MOD // 3단계

            // 결과를 캐시에 저장
            dp[stairs] = ways
            return ways
        }

        return climb(n)
    }

    /**
     * 문제 이해:
     * 10x10 크로스워드 그리드가 주어집니다.
     * '+'는 채우지 않는 칸, '-'는 단어를 채워야 하는 칸입니다.
     * 세미콜론으로 구분된 단어 리스트를 그리드에 적절히 배치해야 합니다.
     * 솔루션 접근법:
     * 백트래킹 알고리즘을 사용합니다.
     * 각 단어를 그리드에 가로 또는 세로로 배치해보고, 가능한 경우 다음 단어로 진행합니다.
     * 배치가 불가능하면 이전 상태로 돌아가 다른 위치를 시도합니다.
     * 주요 함수 설명:
     * crosswordPuzzle: 메인 함수로, 그리드와 단어 리스트를 받아 해결된 그리드를 반환합니다.
     * solveCrossword: 재귀적으로 단어를 배치하며 모든 단어를 성공적으로 배치하면 true를 반환합니다.
     * canPlaceWordHorizontally/Vertically: 단어를 가로/세로로 배치 가능한지 확인합니다.
     * 그리드 범위를 벗어나지 않는지
     * 기존 문자와 충돌하지 않는지('-'이거나 같은 문자인지)
     * placeWordHorizontally/Vertically: 단어를 실제로 배치합니다.
     * removeWordHorizontally/Vertically: 배치한 단어를 제거하여 원상태로 복구합니다.
     * 동작 방식:
     * 단어를 하나씩 선택하여 모든 가능한 위치(가로/세로)에 배치 시도
     * 성공하면 다음 단어로 진행
     * 실패하면 이전 단어 배치를 취소하고 다른 위치 시도
     * 모든 단어가 배치되면 결과 반환
     *
     */
    fun crosswordPuzzle(crossword: Array<String>, words: String): Array<String> {
        // 단어들을 세미콜론으로 분리하여 리스트로 변환
        val wordList = words.split(";")
        // 입력된 크로스워드 그리드를 수정 가능한 2D 문자 배열로 변환
        val grid = crossword.map { it.toCharArray() }.toTypedArray()

        // 크로스워드 해결 시도
        if (solveCrossword(grid, wordList, 0)) {
            // 해결 성공 시 결과 문자열 배열로 변환하여 반환
            return grid.map { String(it) }.toTypedArray()
        }
        // 해결 불가능한 경우 (문제 조건상 발생하지 않음)
        return crossword
    }

    fun solveCrossword(grid: Array<CharArray>, words: List<String>, wordIndex: Int): Boolean {
        // 모든 단어를 배치했으면 true 반환
        if (wordIndex == words.size) return true

        val word = words[wordIndex]

        // 그리드의 모든 위치를 탐색
        for (i in 0 until 10) {
            for (j in 0 until 10) {
                // 가로로 배치 가능한지 확인하고 배치
                if (canPlaceWordHorizontally(grid, word, i, j)) {
                    placeWordHorizontally(grid, word, i, j)
                    if (solveCrossword(grid, words, wordIndex + 1)) return true
                    removeWordHorizontally(grid, word, i, j)
                }
                // 세로로 배치 가능한지 확인하고 배치
                if (canPlaceWordVertically(grid, word, i, j)) {
                    placeWordVertically(grid, word, i, j)
                    if (solveCrossword(grid, words, wordIndex + 1)) return true
                    removeWordVertically(grid, word, i, j)
                }
            }
        }
        return false
    }

    // 가로로 단어를 배치할 수 있는지 확인
    fun canPlaceWordHorizontally(grid: Array<CharArray>, word: String, row: Int, col: Int): Boolean {
        if (col + word.length > 10) return false
        for (k in word.indices) {
            val cell = grid[row][col + k]
            if (cell != '-' && cell != word[k]) return false
        }
        return true
    }

    // 세로로 단어를 배치할 수 있는지 확인
    fun canPlaceWordVertically(grid: Array<CharArray>, word: String, row: Int, col: Int): Boolean {
        if (row + word.length > 10) return false
        for (k in word.indices) {
            val cell = grid[row + k][col]
            if (cell != '-' && cell != word[k]) return false
        }
        return true
    }

    // 가로로 단어 배치
    fun placeWordHorizontally(grid: Array<CharArray>, word: String, row: Int, col: Int) {
        for (k in word.indices) {
            grid[row][col + k] = word[k]
        }
    }

    // 세로로 단어 배치
    fun placeWordVertically(grid: Array<CharArray>, word: String, row: Int, col: Int) {
        for (k in word.indices) {
            grid[row + k][col] = word[k]
        }
    }

    // 가로로 배치한 단어 제거
    fun removeWordHorizontally(grid: Array<CharArray>, word: String, row: Int, col: Int) {
        for (k in word.indices) {
            grid[row][col + k] = '-'
        }
    }

    // 세로로 배치한 단어 제거
    fun removeWordVertically(grid: Array<CharArray>, word: String, row: Int, col: Int) {
        for (k in word.indices) {
            grid[row + k][col] = '-'
        }
    }

    /**
     * 문제 이해:
     * Super Digit은 다음과 같이 정의됩니다:
     * 한 자리 숫자면 그 숫자가 Super Digit입니다.
     * 여러 자리면 각 자리 숫자의 합을 구하고, 그 합에 대해 다시 Super Digit을 계산합니다.
     * 입력은 문자열 n과 반복 횟수 k로, n을 k번 연결한 숫자의 Super Digit을 구해야 합니다.
     * 예: n = "9875", k = 4면 "9875987598759875"의 Super Digit을 계산.
     * 솔루션 접근법:
     * 직접 n을 k번 연결하면 숫자가 너무 커질 수 있으므로, 효율적인 방법을 사용합니다.
     * n의 각 자리 합을 먼저 구하고, 이를 k번 반복한 효과를 반영합니다.
     * 이후 재귀적으로 Super Digit을 계산합니다.
     * 코드 분석:
     * superDigit(n: String, k: Int):
     * n의 각 자리 숫자를 합산 (initialSum).
     * k번 반복한 효과는 initialSum * k로 계산 가능 (totalSum).
     * 이 합을 문자열로 변환하여 calculateSuperDigit에 전달.
     * calculateSuperDigit(num: String):
     * 한 자리면 숫자로 변환해 반환.
     * 여러 자리면 각 자리 합을 구하고 재귀 호출.
     * Long 타입을 사용한 이유: 중간 계산에서 숫자가 Int 범위를 초과할 수 있음.
     * 최적화 포인트:
     * n을 실제로 k번 연결하지 않고, 초기 합에 k를 곱하는 방식으로 메모리와 시간 효율성을 높였습니다.
     * 예: "9875"의 합이 29라면, 이를 4번 반복한 합은 29 * 4 = 116으로 계산.
     */
    fun superDigit(n: String, k: Int): Int {
        // 초기 숫자의 각 자리 합을 계산
        val initialSum = n.fold(0L) { sum, digit -> sum + (digit - '0') }
        // k번 반복한 결과의 합은 초기 합에 k를 곱한 것과 같음
        val totalSum = initialSum * k
        // 최종적으로 super digit 계산
        return calculateSuperDigit(totalSum.toString())
    }

    // 재귀적으로 super digit을 계산하는 함수
    fun calculateSuperDigit(num: String): Int {
        // 한 자리 숫자면 그 숫자가 super digit
        if (num.length == 1) return num.toInt()

        // 각 자리 숫자의 합 계산
        val sum = num.fold(0L) { acc, digit -> acc + (digit - '0') }
        // 합에 대해 다시 super digit 계산
        return calculateSuperDigit(sum.toString())
    }
}