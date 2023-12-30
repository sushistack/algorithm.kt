package com.sushistack.hacker.rank

class Warmup_1 {
    /**
     * 하이커가 산책 기록을 남겼다. 산책은 U(Uphill, 오르막)와 D(Downhill, 내리막) 으로 구성되며, 항상 해수면(Sea Level)에서 시작하고 끝남.
     * 각 단계마다 해수면보다 위에 있으면 “산(mountain)”, 아래에 있으면 “계곡(valley)” 이 된다.
     *
     * 목표:
     * 주어진 steps와 path를 보고, 하이커가 지나간 계곡(valley)의 개수를 구하라.
     */
    fun countingValleys(steps: Int, path: String): Int {
        var level = 0 // 현재 높이 (Sea Level = 0)
        var valleyCount = 0 // 계곡 개수

        for (step in path) {
            if (step == 'U') {
                level += 1
            } else {
                level -= 1
            }

            // 계곡에서 해수면으로 올라올 때 계곡 1개 증가
            if (level == 0 && step == 'U') {
                valleyCount += 1
            }
        }

        return valleyCount
    }

    /**
     * 플레이어는 일렬로 배치된 구름(clouds) 위를 점프하며 끝까지 도달해야 함.
     * 	•	각 구름에는 0(안전) 또는 1(번개⚡, 위험) 이 표시됨.
     * 	•	이동 규칙
     * 	•	+1 또는 +2 만큼 이동 가능
     * 	•	“1”(번개⚡) 구름은 밟을 수 없음
     * 	•	최소 점프로 마지막 구름까지 도달하는 방법을 찾는 문제.
     */
    fun jumpingOnClouds(c: Array<Int>): Int {
        var jumps = 0
        var i = 0

        while (i < c.size - 1) {
            // 두 칸 점프가 가능하면 점프
            i += if (i + 2 < c.size && c[i + 2] == 0) {
                2
            } else {
                1
            }
            jumps++
        }

        return jumps
    }

    /**
     * 주어진 문자열 s를 무한히 반복해서 n개의 문자로 잘랐을 때, a의 개수를 구하는 문제.
     */
    fun repeatedString(s: String, n: Long): Long {
        val countInS = s.count { it == 'a' }.toLong() // 원래 문자열에서 'a' 개수
        val fullRepeats = n / s.length // 문자열이 몇 번 반복되는지
        val remainder = (n % s.length).toInt() // 남은 부분 길이

        val countInRemainder = s.take(remainder).count { it == 'a' }.toLong() // 나머지에서 'a' 개수

        return (countInS * fullRepeats) + countInRemainder
    }

}

