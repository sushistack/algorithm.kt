package com.sushistack.hacker.rank

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.hypot
import kotlin.math.truncate

class Skills {
    internal class Array {
        fun case1() {
            // 크기 5, 모든 요소를 0으로 초기화
            val arr = Array(5) { 0 }
            println(arr.joinToString()) // 출력: 0, 0, 0, 0, 0

            // 인덱스를 활용한 초기화
            val indexed = Array(5) { i -> i + 1 }
            println(indexed.joinToString()) // 출력: 1, 2, 3, 4, 5
        }

        fun case2() {
            val arr = arrayOf(1, 2, 3, 4, 5)
            println(arr.joinToString()) // 출력: 1, 2, 3, 4, 5
        }

        fun case3() {
            val intArr = IntArray(5) { it * 2 } // 0, 2, 4, 6, 8
            val longArr = LongArray(3) { 1L }    // 1, 1, 1
            println(intArr.joinToString())       // 출력: 0, 2, 4, 6, 8
            println(longArr.joinToString())      // 출력: 1, 1, 1
        }
        fun case4() {
            val arr = arrayOf(1, 2, 3)
            println(arr.contains(2)) // 출력: true
            println(2 in arr)        // 동일, 출력: true
        }
        fun case5() {
            val arr = arrayOf(1, 2, 3, 4, 5)
            val fullCopy = arr.copyOf()          // 전체 복사
            val partialCopy = arr.copyOfRange(1, 4) // 인덱스 1부터 3까지
            println(fullCopy.joinToString())     // 출력: 1, 2, 3, 4, 5
            println(partialCopy.joinToString())  // 출력: 2, 3, 4
        }
        fun case6() {
            val arr = arrayOf(3, 1, 4, 1, 5)
            arr.sort() // 원본 오름차순 정렬
            println(arr.joinToString()) // 출력: 1, 1, 3, 4, 5

            val desc = arr.sortedArrayDescending() // 내림차순 새 배열
            println(desc.joinToString()) // 출력: 5, 4, 3, 1, 1
        }
        fun case7() {
            val arr = arrayOf(1, 2, 3, 4)
            val sum = arr.fold(0) { acc, x -> acc + x } // 초기값 0부터 누적 합
            println(sum) // 출력: 10

            val product = arr.reduce { acc, x -> acc * x } // 초기값 없이 곱셈
            println(product) // 출력: 24
        }
        fun case8() {
            val arr = arrayOf(3, 1, 4, 1, 5)
            println(arr.maxOrNull()) // 출력: 5
            println(arr.minOrNull()) // 출력: 1
        }
        fun case9() {
            val arr = arrayOf(2, 4, 6)
            println(arr.all { it % 2 == 0 }) // 모두 짝수? 출력: true
            println(arr.any { it > 5 })      // 5보다 큰 값 있음? 출력: true
        }
        fun case10() {
            val arr = arrayOf(1, 2, 3, 4, 5)
            val sliced = arr.sliceArray(1..3)
            println(sliced.joinToString()) // 출력: 2, 3, 4
        }
        fun case11() {
            val arr = arrayOf(1, 2, 3, 4)
            println(arr.sum())     // 출력: 10
            println(arr.average()) // 출력: 2.5
        }
        fun case12() {
            val arr = arrayOf(1, 2, 3, 4)
            println(arr.sum())     // 출력: 10
            println(arr.average()) // 출력: 2.5
        }
        fun case13() {
            val arr = arrayOf(1, 2, 3, 2, 1)
            val twos = arr.count { it == 2 }
            println(twos) // 출력: 2
        }
        fun case14() {
            val arr = arrayOf(1, 2, 2, 3, 3)
            val unique = arr.distinct()
            println(unique.joinToString()) // 출력: 1, 2, 3
        }
        fun case15() {
            val arr = arrayOf(1, 2, 2, 3, 3)
            val unique = arr.distinct()
            println(unique.joinToString()) // 출력: 1, 2, 3
        }
        fun case16() {
            val matrix = Array(3) { IntArray(3) { 0 } }
            matrix[1][1] = 1
            println(matrix.map { it.joinToString() }.joinToString("\n"))
        }
    }
    class List {
        fun case1() {
            val list = listOf(1, 2, 3, 4, 5)
            val sub = list.subList(1, 4) // 인덱스 1부터 3까지
            val sliced = list.slice(1..3)
            println(sub)    // 출력: [2, 3, 4]
            println(sliced) // 출력: [2, 3, 4]
        }
        fun case2() {
            val list = listOf(1, 2, 3)
            val reversed = list.reversed()
            println(reversed) // 출력: [3, 2, 1]
        }
        fun case3() {
            val list = listOf(1, 2, 3, 4)
            val windows = list.windowed(2, 1) { it.sum() }
            println(windows) // 출력: [3, 5, 7] (1+2, 2+3, 3+4)
        }
        fun case4() {
            val matrix = List(3) { MutableList(3) { 0 } }
            matrix[1][1] = 1
            println(matrix.map { it.joinToString() }.joinToString("\n"))
        }
        fun case5() {
            val list = listOf(1, 2, 3, 4)
            val (evens, odds) = list.partition { it % 2 == 0 }
            println(evens) // 출력: [2, 4]
            println(odds)  // 출력: [1, 3]
        }
        fun case6() {
            val list1 = listOf(1, 2, 3)
            val list2 = listOf("A", "B", "C")
            val pairs = list1.zip(list2)
            println(pairs) // 출력: [(1, A), (2, B), (3, C)]
        }
        fun case7() {
            val nested = listOf(listOf(1, 2), listOf(3, 4))
            val flat = nested.flatten()
            println(flat) // 출력: [1, 2, 3, 4]

            val list = listOf(1, 2, 3)
            val result = list.flatMap { listOf(it, it * 2) }
            println(result) // 출력: [1, 2, 2, 4, 3, 6]
        }
        fun case8() {
            val mutableSet = mutableSetOf(1, 2, 3, 4)
            mutableSet.retainAll(setOf(2, 3))
            println(mutableSet) // 출력: [2, 3]
        }
    }
    class Map {
        fun case1() {
            val mutableMap = mutableMapOf(1 to "One")
            mutableMap.putAll(mapOf(2 to "Two", 3 to "Three"))
            println(mutableMap) // 출력: {1=One, 2=Two, 3=Three}

            mutableMap.putIfAbsent(2, "New") // 이미 있으면 무시
            println(mutableMap) // 출력: {1=One, 2=Two, 3=Three}
        }
        fun case2() {
            val map = mapOf(1 to "One", 2 to "Two", 3 to "Three")
            val filtered = map.filter { it.key % 2 == 0 }
            println(filtered) // 출력: {2=Two}

            val keyFiltered = map.filterKeys { it > 1 }
            println(keyFiltered) // 출력: {2=Two, 3=Three}

            val valueFiltered = map.filterValues { it.length > 3 }
            println(valueFiltered) // 출력: {3=Three}
        }
        fun case3() {
            val mutableMap = mutableMapOf<Int, String>()
            val value = mutableMap.getOrPut(1) { "One" } // 없으면 삽입
            println(value)      // 출력: One
            println(mutableMap) // 출력: {1=One}
        }
        fun case4() {
            val mutableMap = mutableMapOf(1 to "One")
            mutableMap.replace(1, "First")
            println(mutableMap) // 출력: {1=First}
        }
        fun case5() {
            val arr = arrayOf(1, 2, 3)
            val mapFromArr = arr.mapIndexed { index, value -> index to value }.toMap()
            println(mapFromArr) // 출력: {0=1, 1=2, 2=3}

            val list = listOf("A", "B", "C")
            val mapFromList = list.mapIndexed { index, value -> index to value }.toMap()
            println(mapFromList) // 출력: {0=A, 1=B, 2=C}
        }
        fun case6() {
            val map = mapOf(1 to "One", 2 to "Two")
            val keyArray = map.keys.toTypedArray()
            val valueList = map.values.toList()
            println(keyArray.joinToString()) // 출력: 1, 2
            println(valueList)              // 출력: [One, Two]
        }
        fun case7() {
            val list = listOf(1, 2, 2, 3, 1)
            val freq = list.groupingBy { it }.eachCount()
            println(freq) // 출력: {1=2, 2=2, 3=1}
        }
    }
    class Loop {
        /**
         * 반복 종류	주요 사용법
         * 기본 for	in, downTo, step, withIndex
         * while	while, do-while
         * 제어	break, continue, 레이블 (@label)
         * 확장 함수	forEach, forEachIndexed, repeat
         * 범위/인덱스	until, indices, generateSequence
         */
        fun case1() {
            for (i in 1..5) { // 1부터 5까지 (포함)
                print("$i ") // 출력: 1 2 3 4 5
            }

            for (i in 1..10 step 2) { // 1부터 10까지 2씩 증가
                print("$i ") // 출력: 1 3 5 7 9
            }

            val list = listOf(1, 2, 3)
            for (item in list) {
                print("$item ") // 출력: 1 2 3
            }

            val array = arrayOf("A", "B", "C")
            for ((index, value) in array.withIndex()) {
                println("[$index] = $value")
            }

            val map = mapOf(1 to "One", 2 to "Two")
            for ((key, value) in map) {
                println("$key: $value")
            }
        }
        fun case2() {
            var i = 0
            while (i < 5) {
                print("$i ") // 출력: 0 1 2 3 4
                i++
            }

            i = 0
            do {
                print("$i ") // 출력: 0 1 2 3 4
                i++
            } while (i < 5)
        }
        fun case3() {
            for (i in 1..10) {
                if (i == 5) break
                print("$i ") // 출력: 1 2 3 4
            }

            for (i in 1..5) {
                if (i % 2 == 0) continue
                print("$i ") // 출력: 1 3 5
            }

            outer@ for (i in 1..3) {
                for (j in 1..3) {
                    if (j == 2) break@outer // 바깥 반복문 종료
                    print("($i, $j) ") // 출력: (1, 1)
                }
            }
        }
        fun case4() {
            repeat(3) {
                print("Hello ") // 출력: Hello Hello Hello
            }
        }
        fun case5() {
            val seq = generateSequence(1) { it + 1 }.take(5) // 1부터 5개
            seq.forEach { print("$it ") } // 출력: 1 2 3 4 5
        }
        fun case6() {
            for (i in 0 until 5) { // 0부터 4까지
                print("$i ") // 출력: 0 1 2 3 4
            }

            val list = listOf("A", "B", "C")
            for (i in list.indices) {
                print("${list[i]} ") // 출력: A B C
            }

            val matrix = Array(2) { IntArray(2) { it + 1 } }
            for (i in matrix.indices) {
                for (j in matrix[i].indices) {
                    print("${matrix[i][j]} ") // 출력: 1 2 1 2
                }
                println()
            }
        }
    }
    class Math {
        fun case0() {
            println(truncate(3.7))  // 출력: 3.0 (소수점 버림)
            println(truncate(-3.7)) // 출력: -3.0
        }
        fun case1() {
            println(hypot(3.0, 4.0)) // √(3² + 4²), 출력: 5.0
        }
    }
    class DataStucture {
        /**
         * 자료구조	Kotlin 구현	주요 메서드	활용 예시
         * Stack	ArrayDeque	addLast, removeLast	DFS, 괄호 문제
         * Queue	ArrayDeque	addLast, removeFirst	BFS, 작업 처리
         * Priority Queue	PriorityQueue	add, poll, peek	다익스트라, 그리디
         * Tree	사용자 정의	-	트리 순회, BST
         * Deque	ArrayDeque	addFirst, removeLast	슬라이딩 윈도우
         * Set	HashSet, TreeSet	add, intersect, union	중복 제거, 집합 연산
         */

        fun case1() {
            val stack = ArrayDeque<Int>()

            // Push (요소 추가)
            stack.addLast(1) // 또는 stack.push(1)
            stack.addLast(2)
            stack.addLast(3)
            println(stack) // 출력: [1, 2, 3]

            // Pop (최상단 요소 제거 및 반환)
            println(stack.removeLast()) // 출력: 3
            println(stack) // 출력: [1, 2]

            // Peek (최상단 요소 확인)
            println(stack.last()) // 출력: 2

            // 비어있는지 확인
            println(stack.isEmpty()) // 출력: false
        }
        fun case2() {
            val queue = ArrayDeque<Int>()

            // Enqueue (요소 추가)
            queue.addLast(1)
            queue.addLast(2)
            queue.addLast(3)
            println(queue) // 출력: [1, 2, 3]

            // Dequeue (첫 요소 제거 및 반환)
            println(queue.removeFirst()) // 출력: 1
            println(queue) // 출력: [2, 3]

            // Peek (첫 요소 확인)
            println(queue.first()) // 출력: 2

            // 비어있는지 확인
            println(queue.isEmpty()) // 출력: false
        }
        fun case3() {
            // 최소 힙 (기본)
            val minHeap = PriorityQueue<Int>()
            minHeap.add(3)
            minHeap.add(1)
            minHeap.add(4)
            println(minHeap) // 출력: [1, 3, 4]
            println(minHeap.poll()) // 출력: 1 (최소값 제거)

            // 최대 힙 (Comparator 사용)
            val maxHeap = PriorityQueue<Int>(compareByDescending { it })
            maxHeap.addAll(listOf(3, 1, 4))
            println(maxHeap.poll()) // 출력: 4 (최대값 제거)

            // 커스텀 객체 정렬
            data class Item(val id: Int, val priority: Int)
            val pq = PriorityQueue<Item>(compareBy { it.priority })
            pq.add(Item(1, 5))
            pq.add(Item(2, 2))
            println(pq.poll()) // 출력: Item(id=2, priority=2)
        }

        class TreeNode<T>(val value: T) {
            var left: TreeNode<T>? = null
            var right: TreeNode<T>? = null
        }

        fun case4() {
            // 트리 생성
            val root = TreeNode(1)
            root.left = TreeNode(2)
            root.right = TreeNode(3)
            root.left?.left = TreeNode(4)

            // 전위 순회 (Preorder)
            fun preorder(node: TreeNode<Int>?) {
                if (node == null) return
                print("${node.value} ")
                preorder(node.left)
                preorder(node.right)
            }

            preorder(root) // 출력: 1 2 4 3
        }
        /**
         * 기능 분류	주요 함수	적용 대상
         * 집합 연산	intersect, union, subtract, containsAll	Set, List
         * 요소 조작	plus, minus, addAll, removeAll, retainAll	모든 컬렉션
         * 변환/그룹	associate, groupBy, partition, zip	List, Set
         * 평탄화	flatten, flatMap	중첩 컬렉션
         */
        fun case5() {
            // HashSet (순서 보장 X)
            val set = mutableSetOf<Int>()
            set.add(1)
            set.add(2)
            set.add(1) // 중복 무시
            println(set) // 출력: [1, 2]

            // TreeSet (정렬 유지)
            val sortedSet = sortedSetOf(3, 1, 4, 1)
            println(sortedSet) // 출력: [1, 3, 4]

            // 집합 연산
            val set2 = setOf(2, 3)
            println(set.intersect(set2)) // 교집합, 출력: [2]
            println(set.union(set2))     // 합집합, 출력: [1, 2, 3]
            println(set.subtract(set2))  // 차집합
        }
    }
    class Additional {
        /**
         * 기능 분류	주요 함수	활용 예시
         * 요소 선택	take, drop, chunked	데이터 분할
         * 누적 연산	runningFold, runningReduce	DP, 누적 계산
         * 값 제한	coerceIn, coerceAtLeast	경계 처리
         * 스코프 함수	let, run, with, apply, also	객체 초기화, null 처리
         * Lazy 처리	sequence	대량 데이터 처리
         * 정렬	compareBy, sortedWith	커스텀 정렬
         */
        fun case1() {
            val list = listOf(1, 2, 3, 4, 5)
            println(list.take(3))      // 앞에서 3개, 출력: [1, 2, 3]
            println(list.takeLast(2))  // 뒤에서 2개, 출력: [4, 5]
        }
        fun case2() {
            val list = listOf(1, 2, 3, 4, 5)
            println(list.drop(2))      // 앞에서 2개 건너뛰기, 출력: [3, 4, 5]
            println(list.dropLast(1))  // 뒤에서 1개 건너뛰기, 출력: [1, 2, 3, 4]
        }
        fun case3() {
            val list = listOf(1, 2, 3, 0, 4, 5)
            println(list.takeWhile { it < 3 }) // 조건 만족까지, 출력: [1, 2]
            println(list.dropWhile { it < 3 }) // 조건 만족 건너뛰기, 출력: [3, 0, 4, 5]
        }
        fun case4() {
            val list = listOf(1, 2, 3, 4, 5, 6)
            val chunks = list.chunked(2) // 2개씩 묶기
            println(chunks) // 출력: [[1, 2], [3, 4], [5, 6]]

            // 각 묶음에 연산 적용
            val sums = list.chunked(2) { it.sum() }
            println(sums) // 출력: [3, 7, 11]
        }
        fun case5() {
            val list = listOf(1, 2, 3, 4)
            val sums = list.runningFold(0) { acc, x -> acc + x }
            println(sums) // 출력: [0, 1, 3, 6, 10] (초기값 포함)
        }
        fun case6() {
            val list = listOf(1, 2, 3, 4)
            val products = list.runningReduce { acc, x -> acc * x }
            println(products) // 출력: [1, 2, 6, 24] (초기값 없음)
        }
        fun case7() {
            val num = 15
            println(num.coerceIn(0..10)) // 0~10 사이로 제한, 출력: 10
        }
        fun case8() {
            val num = -5
            println(num.coerceAtLeast(0)) // 최소 0, 출력: 0
            println(num.coerceAtMost(3))  // 최대 3, 출력: -5
        }
        fun case9() {
            val seq = sequence {
                yieldAll(1..10)
            }.filter { it % 2 == 0 }.take(3)
            println(seq.toList()) // 출력: [2, 4, 6]
        }
        fun case10() {
            data class Person(val name: String, val age: Int)
            val people = listOf(Person("Alice", 25), Person("Bob", 20))

            val sorted = people.sortedWith(compareBy({ it.age }, { it.name }))
            println(sorted) // 출력: [Person(name=Bob, age=20), Person(name=Alice, age=25)]
        }
        fun case11() {
            val pair = 1 to "One"
            println(pair) // 출력: (1, One)

            val triple = Triple(1, "One", true)
            println(triple) // 출력: (1, One, true)
        }
    }
}