package com.sushistack.hacker.rank

class Hashmap_3 {
    /**
     * 해롤드는 magazine에 있는 단어들을 오려서 ransom note를 만들고 싶다.
     * 각 단어는 대소문자를 구별하며, 부분 문자열을 사용할 수 없고 전체 단어 단위로만 사용 가능하다.
     * 즉, magazine의 단어들이 ransom note의 단어들을 포함하는지 검사하는 문제이다.
     *
     *  해결 방법 (해시맵 활용)
     * 	1.	magazine의 단어 등장 횟수를 해시맵(Map) 에 저장.
     * 	2.	ransom note의 단어를 하나씩 확인하며, magazine에서 사용할 수 있는지 검사.
     * 	3.	magazine에서 필요한 단어의 개수가 부족하면 “No” 출력.
     * 	4.	모든 단어를 만들 수 있으면 “Yes” 출력.
     */
    fun checkMagazine(magazine: Array<String>, note: Array<String>) {
        val wordCount = mutableMapOf<String, Int>()

        // 1. magazine 단어 개수를 맵에 저장
        for (word in magazine) {
            wordCount[word] = wordCount.getOrDefault(word, 0) + 1
        }

        // 2. note 단어가 magazine에서 충분히 있는지 확인
        for (word in note) {
            if (wordCount.getOrDefault(word, 0) == 0) {
                println("No")
                return
            }
            wordCount[word] = wordCount[word]!! - 1
        }

        println("Yes")
    }

    /**
     * 주어진 두 문자열 s1과 s2가 하나 이상의 공통 부분 문자열을 가지고 있는지 확인하는 문제이다.
     * 공통 부분 문자열은 한 글자만 일치해도 가능하다.
     *
     * 해결 방법 (해시셋 활용, O(n + m) 해결)
     * 	1.	s1의 모든 문자를 Set에 저장.
     * 	2.	s2의 문자를 하나씩 확인하며, s1의 문자 집합에 포함되어 있는지 검사.
     * 	3.	공통 문자가 있으면 “YES”, 없으면 “NO” 출력.
     *
     * 이 방법은 O(n + m) 시간 복잡도로 효율적으로 해결할 수 있다.
     *
     */
    fun twoStrings(s1: String, s2: String): String {
        val s1Chars = s1.toSet()

        for (char in s2) {
            if (char in s1Chars) {
                return "YES"
            }
        }

        return "NO"
    }


    /**
     * 주어진 문자열 s에서 서로 아나그램이 되는 부분 문자열 쌍의 개수를 찾는 문제다.
     *
     * 아나그램이란?
     * 	•	두 문자열이 같은 문자의 빈도를 가질 경우 아나그램이다.
     * 	•	예: "abc"와 "bca"는 아나그램 ({'a':1, 'b':1, 'c':1} 동일)
     * 	•	예: "abba"의 아나그램 쌍 → ("ab", "ba"), ("abb", "bba"), ("a", "a")
     *
     * 	해결 방법 (해시맵 활용, O(n²) 최적화)
     * 	1.	모든 부분 문자열의 정렬된 형태를 해시맵에 저장
     * 	•	부분 문자열의 문자 빈도 정보가 동일하면 같은 키로 취급
     * 	2.	각 키에 대해 조합 개수 계산
     * 	•	같은 키를 가진 부분 문자열이 count개 있으면, (count * (count - 1)) / 2 쌍이 가능
     */
    fun sherlockAndAnagrams(s: String): Int {
        val freqMap = mutableMapOf<String, Int>()

        // 모든 부분 문자열을 정렬된 문자 형태로 저장
        for (i in s.indices) {
            for (j in i until s.length) {
                val substring = s.substring(i, j + 1).toCharArray().sorted().joinToString("")
                freqMap[substring] = freqMap.getOrDefault(substring, 0) + 1
            }
        }

        // 아나그램 쌍의 개수 계산
        var count = 0
        for (value in freqMap.values) {
            if (value > 1) {
                count += (value * (value - 1)) / 2  // 조합 계산
            }
        }

        return count
    }

    /**
     * 문제 설명 및 해결 방법
     * 주어진 배열 arr에서 공비 r을 갖는 등비수열을 이루는 세 개의 인덱스 (i, j, k) 쌍의 개수를 찾는 문제입니다. 즉, arr[i] * r = arr[j]이고 arr[j] * r = arr[k]를 만족하는 i < j < k인 인덱스 쌍의 개수를 찾아야 합니다.
     *
     * 효율적인 해결을 위해 해시맵을 사용합니다.
     *
     * 오른쪽 해시맵 (right): 각 숫자의 등장 횟수를 저장합니다. 배열을 순회하면서 각 숫자의 등장 횟수를 기록합니다.
     * 왼쪽 해시맵 (left): 현재 인덱스 이전의 숫자들의 등장 횟수를 저장합니다.
     * 배열 순회: 배열의 각 원소 arr[j]를 순회하면서,
     * arr[j] / r이 left에 존재하는지 확인하고, 존재하면 left[arr[j] / r]을 가져옵니다.
     * arr[j] * r이 right에 존재하는지 확인하고, 존재하면 right[arr[j] * r]을 가져옵니다.
     * left[arr[j] / r]과 right[arr[j] * r]을 곱하여 현재 arr[j]를 중간 값으로 하는 등비수열의 개수를 계산합니다.
     * right에서 arr[j]의 등장 횟수를 1 감소시키고, left에 arr[j]의 등장 횟수를 1 증가시킵니다.
     * 결과 반환: 모든 원소에 대해 계산된 등비수열의 개수를 합하여 반환합니다.
     */
    fun countTriplets(arr: Array<Long>, r: Long): Long {
        val left = mutableMapOf<Long, Long>().withDefault { 0 }
        val right = mutableMapOf<Long, Long>().withDefault { 0 }
        for (num in arr) {
            right[num] = right.getValue(num) + 1
        }

        var count = 0L
        for (num in arr) {
            right[num] = right.getValue(num) - 1

            val leftCount = left.getValue(num / r)
            val rightCount = right.getValue(num * r)

            if (num % r == 0L) {
                count += leftCount * rightCount
            }

            left[num] = left.getValue(num) + 1
        }

        return count
    }


    /**
     * freqQuery(queries: Array<Array<Int>>): List<Int> 함수:
     *
     * frequencyMap: 각 값의 빈도수를 저장하는 맵입니다.
     * frequencyCountMap: 각 빈도수의 등장 횟수를 저장하는 맵입니다. 즉, 어떤 빈도수가 몇 번 등장했는지 저장합니다.
     * result: 3번 쿼리의 결과를 저장하는 리스트입니다.
     * queries 배열을 순회하며 각 쿼리를 처리합니다.
     * 쿼리 처리:
     *
     * 1번 쿼리 (삽입):
     * frequencyMap에서 value의 현재 빈도수를 가져와 1 증가시킵니다.
     * frequencyCountMap에서 이전 빈도수의 등장 횟수를 1 감소시키고, 새로운 빈도수의 등장 횟수를 1 증가시킵니다.
     * 2번 쿼리 (삭제):
     * frequencyMap에서 value의 현재 빈도수가 0보다 크면 1 감소시킵니다.
     * frequencyCountMap에서 이전 빈도수의 등장 횟수를 1 감소시키고, 새로운 빈도수의 등장 횟수를 1 증가시킵니다.
     * 3번 쿼리 (빈도수 확인):
     * frequencyCountMap에서 value 빈도수의 등장 횟수가 0보다 크면 result에 1을 추가하고, 그렇지 않으면 0을 추가합니다.
     * 결과 반환:
     *
     * result 리스트를 반환합니다.
     * 핵심 아이디어:
     *
     * frequencyMap을 사용하여 각 값의 빈도수를 추적합니다.
     * frequencyCountMap을 사용하여 각 빈도수의 등장 횟수를 추적합니다. 이를 통해 3번 쿼리를 효율적으로 처리할 수 있습니다.
     * 각 쿼리 처리 후 frequencyCountMap을 적절히 업데이트하여 빈도수 정보를 최신 상태로 유지합니다.
     */
    fun freqQuery(queries: Array<Array<Int>>): List<Int> {
        val frequencyMap = mutableMapOf<Int, Int>().withDefault { 0 }
        val frequencyCountMap = mutableMapOf<Int, Int>().withDefault { 0 }
        val result = mutableListOf<Int>()

        for (query in queries) {
            val operation = query[0]
            val value = query[1]

            when (operation) {
                1 -> {
                    val oldFrequency = frequencyMap.getValue(value)
                    frequencyMap[value] = oldFrequency + 1
                    val newFrequency = frequencyMap.getValue(value)

                    if (oldFrequency > 0) {
                        frequencyCountMap[oldFrequency] = frequencyCountMap.getValue(oldFrequency) - 1
                    }
                    frequencyCountMap[newFrequency] = frequencyCountMap.getValue(newFrequency) + 1
                }
                2 -> {
                    val oldFrequency = frequencyMap.getValue(value)
                    if (oldFrequency > 0) {
                        frequencyMap[value] = oldFrequency - 1
                        val newFrequency = frequencyMap.getValue(value)

                        frequencyCountMap[oldFrequency] = frequencyCountMap.getValue(oldFrequency) - 1
                        frequencyCountMap[newFrequency] = frequencyCountMap.getValue(newFrequency) + 1
                    }
                }
                3 -> {
                    if (frequencyCountMap.getValue(value) > 0) {
                        result.add(1)
                    } else {
                        result.add(0)
                    }
                }
            }
        }

        return result
    }
}