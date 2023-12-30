package com.sushistack.hacker.rank

class StringManufact_6 {
    /**
     * 문제 설명 및 해결 방법
     * 두 문자열 a와 b가 주어졌을 때, 두 문자열을 애너그램으로 만들기 위해 삭제해야 하는 최소 문자 수를 구하는 문제입니다. 애너그램은 두 문자열이 동일한 문자를 동일한 빈도로 포함하는 경우를 의미합니다.
     *
     * 해결 방법:
     *
     * 문자 빈도수 계산: 각 문자열의 문자 빈도수를 계산합니다. 해시맵 또는 배열을 사용하여 각 문자의 등장 횟수를 저장합니다.
     * 빈도수 비교 및 삭제 문자 수 계산: 두 문자열의 문자 빈도수를 비교하여 삭제해야 하는 문자 수를 계산합니다.
     * 두 문자열에 공통으로 존재하는 문자의 경우, 빈도수의 차이만큼 삭제해야 합니다.
     * 한 문자열에만 존재하는 문자의 경우, 해당 문자의 빈도수만큼 삭제해야 합니다.
     * 삭제 문자 수 반환: 계산된 삭제 문자 수를 반환합니다.
     */
    fun makeAnagram(a: String, b: String): Int {
        val aFreq = mutableMapOf<Char, Int>()
        val bFreq = mutableMapOf<Char, Int>()

        // 문자열 a의 문자 빈도수 계산
        for (char in a) {
            aFreq[char] = aFreq.getOrDefault(char, 0) + 1
        }

        // 문자열 b의 문자 빈도수 계산
        for (char in b) {
            bFreq[char] = bFreq.getOrDefault(char, 0) + 1
        }

        var deletions = 0

        // 두 문자열의 문자 빈도수 비교 및 삭제 문자 수 계산
        for (char in aFreq.keys + bFreq.keys) {
            val aCount = aFreq.getOrDefault(char, 0)
            val bCount = bFreq.getOrDefault(char, 0)
            deletions += Math.abs(aCount - bCount)
        }

        return deletions
    }

    /**
     * 주어진 문자열 s에서 인접한 문자가 동일한 경우, 문자를 삭제하여 인접한 문자가 서로 다른 문자열로 만드는 문제입니다. 최소 삭제 횟수를 구해야 합니다.
     *
     * 해결 방법:
     *
     * 문자열 순회: 문자열 s를 순회합니다.
     * 인접 문자 비교: 현재 문자와 다음 문자를 비교합니다.
     * 삭제 횟수 계산: 현재 문자와 다음 문자가 동일하면 삭제 횟수를 1 증가시킵니다.
     * 결과 반환: 문자열 순회가 완료되면 삭제 횟수를 반환합니다.
     */
    fun alternatingCharacters(s: String): Int {
        var deletions = 0
        for (i in 0 until s.length - 1) {
            if (s[i] == s[i + 1]) {
                deletions++
            }
        }
        return deletions
    }


    /**
     * 주어진 문자열 s가 유효한 문자열인지 판단하는 문제입니다. 유효한 문자열은 다음과 같은 두 가지 조건을 만족합니다.
     *
     * 모든 문자의 등장 횟수가 동일한 경우: 문자열 내 모든 문자의 등장 횟수가 같은 경우 유효합니다.
     * 하나의 문자를 제거하여 모든 문자의 등장 횟수가 동일해지는 경우: 문자열 내 하나의 문자를 제거했을 때, 나머지 모든 문자의 등장 횟수가 같아지는 경우 유효합니다.
     * 해결 방법:
     *
     * 문자 빈도수 계산: 문자열 s의 문자 빈도수를 계산합니다. 해시맵을 사용하여 각 문자의 등장 횟수를 저장합니다.
     * 빈도수 분석: 문자 빈도수를 분석하여 유효성을 판단합니다.
     * 모든 빈도수가 동일한 경우: "YES"를 반환합니다.
     * 빈도수가 2 종류인 경우:
     * 빈도수가 작은 쪽이 1이고, 빈도수가 큰 쪽의 개수가 1개인 경우: "YES"를 반환합니다.
     * 빈도수가 큰 쪽이 빈도수가 작은 쪽보다 1 크고, 빈도수가 큰 쪽의 개수가 1개인 경우: "YES"를 반환합니다.
     * 그 외의 경우: "NO"를 반환합니다.
     *
     * 핵심 아이디어:
     *
     * 문자 빈도수를 효율적으로 저장하기 위해 해시맵을 사용합니다.
     * 빈도수의 등장 횟수를 계산하여 유효성을 판단합니다.
     * 빈도수의 종류가 1개 또는 2개인 경우에 따라 유효성을 판단하는 조건을 다르게 처리합니다.
     * 빈도수의 종류가 2개인 경우, 빈도수를 정렬하여 비교합니다.
     */
    fun isValid(s: String): String {
        val freqMap = mutableMapOf<Char, Int>()
        for (char in s) {
            freqMap[char] = freqMap.getOrDefault(char, 0) + 1
        }

        val freqCountMap = mutableMapOf<Int, Int>()
        for (freq in freqMap.values) {
            freqCountMap[freq] = freqCountMap.getOrDefault(freq, 0) + 1
        }

        if (freqCountMap.size == 1) {
            return "YES"
        }

        if (freqCountMap.size == 2) {
            val freqs = freqCountMap.keys.sorted()
            val count1 = freqCountMap[freqs[0]]!!
            val count2 = freqCountMap[freqs[1]]!!

            if (freqs[0] == 1 && count1 == 1) {
                return "YES"
            }

            if (freqs[1] - freqs[0] == 1 && count2 == 1) {
                return "YES"
            }
        }

        return "NO"
    }

    /**
     * 주어진 문자열 s에서 특별한 부분 문자열(special substring)의 개수를 찾는 문제입니다. 특별한 부분 문자열은 다음과 같은 두 가지 조건을 만족합니다.
     *
     * 모든 문자가 동일한 경우: "aaa", "bb", "c" 등
     * 중간 문자를 제외한 모든 문자가 동일한 경우: "aadaa", "xyx" 등
     * 해결 방법:
     *
     * 동일 문자 연속 구간 계산: 문자열 s를 순회하면서 동일한 문자가 연속되는 구간의 길이와 문자를 저장합니다.
     * 특별한 부분 문자열 개수 계산:
     * 동일 문자 연속 구간의 길이 len에 대해 len * (len + 1) / 2 개의 특별한 부분 문자열이 존재합니다.
     * 중간 문자를 제외한 모든 문자가 동일한 경우를 찾습니다.
     * 현재 구간의 문자와 이전/다음 구간의 문자가 동일하고, 이전/다음 구간의 길이가 1 이상이면 특별한 부분 문자열이 존재합니다.
     * 특별한 부분 문자열의 개수는 이전/다음 구간의 길이 중 작은 값입니다.
     * 결과 반환: 계산된 특별한 부분 문자열의 개수를 반환합니다.
     */
    fun substrCount(n: Int, s: String): Long {
        var count = 0L
        val sameChars = mutableListOf<Pair<Char, Int>>()
        var i = 0

        while (i < n) {
            var j = i
            while (j < n && s[i] == s[j]) {
                j++
            }
            sameChars.add(Pair(s[i], j - i))
            i = j
        }

        for (pair in sameChars) {
            count += pair.second.toLong() * (pair.second + 1) / 2
        }

        for (j in 1 until sameChars.size - 1) {
            if (sameChars[j - 1].first == sameChars[j + 1].first && sameChars[j].second == 1) {
                count += Math.min(sameChars[j - 1].second, sameChars[j + 1].second)
            }
        }

        return count
    }

    /**
     * 주어진 두 문자열 s1과 s2의 공통 부분 수열(common subsequence) 중 가장 긴 부분 수열의 길이를 구하는 문제입니다. 부분 수열은 원본 문자열에서 0개 이상의 문자를 삭제하여 만들 수 있으며, 문자의 순서는 변경할 수 없습니다.
     *
     * 해결 방법:
     *
     * 동적 프로그래밍 (Dynamic Programming): 2차원 배열 dp를 사용하여 부분 문제를 해결하고 결과를 저장합니다.
     * dp[i][j]는 s1의 처음 i개 문자와 s2의 처음 j개 문자의 공통 부분 수열의 최대 길이를 나타냅니다.
     * 배열 초기화: dp 배열의 첫 번째 행과 첫 번째 열을 0으로 초기화합니다.
     * 배열 채우기: s1과 s2의 각 문자를 비교하면서 dp 배열을 채웁니다.
     * s1[i - 1]과 s2[j - 1]이 같으면 dp[i][j] = dp[i - 1][j - 1] + 1입니다.
     * s1[i - 1]과 s2[j - 1]이 다르면 dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])입니다.
     * 결과 반환: dp[s1.length][s2.length]를 반환합니다.
     *
     */
    fun commonChild(s1: String, s2: String): Int {
        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }

        for (i in 1..s1.length) {
            for (j in 1..s2.length) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }

        return dp[s1.length][s2.length]
    }
}