package com.sushistack.hacker.rank

class StackQueue_7 {

    /**
     * 문제 이해:
     * 괄호 문자열이 주어졌을 때, 균형이 맞는지 확인해야 합니다.
     * 균형의 조건:
     * 모든 괄호가 짝을 이루어야 함.
     * 괄호 쌍 안에 포함된 괄호들도 균형을 이루어야 함.
     * 예:
     * "([])" → YES
     * "{[(])}" → NO
     * "{{[[(())]]}}" → YES
     * 솔루션 접근법:
     * 스택을 사용하여 괄호의 짝을 맞춥니다.
     * 여는 괄호는 스택에 넣고, 닫는 괄호는 스택의 마지막 요소와 비교합니다.
     * 모든 괄호를 처리한 후 스택이 비어있으면 균형이 맞는 것입니다.
     * 코드 분석:
     * isBalanced(s: String):
     * stack: ArrayDeque를 사용하여 괄호를 추적.
     * 각 문자에 대해:
     * 여는 괄호((, {, [) → 스택에 추가.
     * 닫는 괄호(), }, ]) → 스택이 비어있거나 마지막 괄호가 짝이 맞지 않으면 "NO" 반환.
     * 문자열 끝에서 스택이 비어있으면 "YES", 아니면 "NO".
     * main(): 여러 테스트 케이스를 처리하기 위한 입력 처리 로직.
     */
    fun isBalanced(s: String): String {
        val stack = ArrayDeque<Char>()
        for (char in s) {
            when (char) {
                '(', '{', '[' -> stack.addLast(char)
                ')' -> if (stack.isEmpty() || stack.removeLast() != '(') return "NO"
                '}' -> if (stack.isEmpty() || stack.removeLast() != '{') return "NO"
                ']' -> if (stack.isEmpty() || stack.removeLast() != '[') return "NO"
            }
        }
        return if (stack.isEmpty()) "YES" else "NO"
    }

    /**
     * 문제 이해:
     * 큐(Queue)는 FIFO(First-In-First-Out) 자료구조입니다.
     * 두 개의 스택을 사용하여 큐를 구현해야 합니다.
     * 지원해야 할 연산:
     * put: 요소를 큐 끝에 추가 (Enqueue)
     * pop: 큐 앞에서 요소 제거 및 반환 (Dequeue)
     * peek: 큐 앞의 요소 확인
     * 솔루션 접근법:
     * 두 스택을 사용:
     * inputStack: 새 요소를 추가하는 스택
     * outputStack: 요소를 제거하고 확인하는 스택
     * 입력 순서를 유지하면서 FIFO를 구현하기 위해:
     * Enqueue 시 inputStack에 추가
     * Dequeue/Peek 시 outputStack에서 처리
     * outputStack이 비어있으면 inputStack의 요소를 뒤집어서 outputStack으로 이동
     * 코드 분석:
     * put(value: Int):
     * 단순히 inputStack에 요소를 추가
     * pop(): Int:
     * outputStack이 비어있으면 moveInputToOutputIfNeeded() 호출
     * outputStack에서 마지막 요소 제거 및 반환
     * peek(): Int:
     * outputStack이 비어있으면 moveInputToOutputIfNeeded() 호출
     * outputStack의 마지막 요소 반환 (제거하지 않음)
     * moveInputToOutputIfNeeded():
     * inputStack의 요소를 모두 꺼내어 outputStack에 추가
     * 스택의 LIFO 특성상 순서가 뒤집혀 FIFO가 됨
     */
    class QueueWithStacks {
        // 두 개의 스택 사용: 입력 스택과 출력 스택
        private val inputStack = ArrayDeque<Int>()
        private val outputStack = ArrayDeque<Int>()

        // 요소를 큐에 추가 (Enqueue)
        fun put(value: Int) {
            inputStack.addLast(value)
        }

        // 큐에서 요소 제거 및 반환 (Dequeue)
        fun pop(): Int {
            // outputStack이 비어있으면 inputStack에서 옮김
            moveInputToOutputIfNeeded()
            return outputStack.removeLast()
        }

        // 큐의 맨 앞 요소 확인 (Peek)
        fun peek(): Int {
            // outputStack이 비어있으면 inputStack에서 옮김
            moveInputToOutputIfNeeded()
            return outputStack.last()
        }

        // inputStack의 요소를 outputStack으로 이동
        private fun moveInputToOutputIfNeeded() {
            if (outputStack.isEmpty()) {
                while (inputStack.isNotEmpty()) {
                    outputStack.addLast(inputStack.removeLast())
                }
            }
        }
    }

    fun runQueueWithStacks() {
        val queue = QueueWithStacks()
        val q = readLine()!!.toInt() // 쿼리 수 입력

        repeat(q) {
            val query = readLine()!!.split(" ").map { it.toInt() }
            when (query[0]) {
                1 -> queue.put(query[1])  // Enqueue
                2 -> queue.pop()          // Dequeue
                3 -> println(queue.peek()) // Peek
            }
        }
    }


    /**
     * 문제 이해:
     * 건물 높이 배열이 주어졌을 때, 연속된 건물들로 만들 수 있는 가장 큰 직사각형의 면적을 구해야 합니다.
     * 직사각형의 높이는 선택된 건물 중 최소 높이, 너비는 연속된 건물 수입니다.
     * 예: [1, 2, 3, 4, 5] → 최대 면적은 높이 5, 너비 1로 5.
     * 솔루션 접근법:
     * 스택을 사용하여 효율적으로 최대 면적을 계산합니다.
     * 히스토그램에서 최대 직사각형을 찾는 잘 알려진 알고리즘을 적용합니다.
     * 각 건물 높이를 처리하면서, 더 낮은 높이를 만나면 이전 건물들로 가능한 직사각형 면적을 계산합니다.
     * 코드 분석:
     * largestRectangle(h: Array<Int>): Long:
     * stack: 건물 인덱스를 저장하여 높이와 위치를 추적.
     * maxArea: 최대 면적을 저장.
     * 알고리즘:
     * 현재 높이가 스택의 맨 위 높이보다 작을 때마다:
     * 스택에서 높이를 꺼내고, 해당 높이로 만들 수 있는 직사각형 면적 계산.
     * 너비는 현재 위치와 스택의 이전 위치 간 거리.
     * 모든 건물 처리 후, 남은 스택 요소를 위해 가상의 높이 0을 추가하여 계산 완료.
     * 반환값은 Long 타입으로 큰 면적을 처리 가능.
     */
    fun largestRectangle(h: Array<Int>): Long {
        // 스택을 사용하여 높이와 위치 저장
        val stack = ArrayDeque<Int>()
        var maxArea = 0L
        var i = 0

        // 배열 끝까지 처리 + 마지막 계산을 위해 가상의 0 높이 추가
        while (i <= h.size) {
            val currHeight = if (i == h.size) 0 else h[i]

            // 현재 높이가 스택의 맨 위보다 작으면 영역 계산
            while (stack.isNotEmpty() && currHeight < h[stack.last()]) {
                val height = h[stack.removeLast()]
                // 너비 계산: 스택이 비어있으면 i까지, 아니면 현재 i와 이전 위치 차이
                val width = if (stack.isEmpty()) i else i - stack.last() - 1
                maxArea = maxOf(maxArea, height.toLong() * width)
            }

            // 현재 위치를 스택에 추가
            stack.addLast(i)
            i++
        }

        return maxArea
    }

    /**
     * 문제 이해:
     * 크기 n의 배열에서 윈도우 크기 1부터 n까지 각 윈도우의 최소값들 중 최대값을 구해야 합니다.
     * 예: [1, 2, 3, 5, 1, 2]
     * 윈도우 크기 1: 최대 최소값 = 5
     * 윈도우 크기 2: 최대 최소값 = 3
     * 윈도우 크기 3: 최대 최소값 = 2 등
     * 솔루션 접근법:
     * 각 숫자가 최소값으로 작용할 수 있는 최대 윈도우 크기를 계산합니다.
     * 스택을 사용해 각 숫자의 왼쪽과 오른쪽에서 가장 가까운 작은 값의 위치를 찾습니다.
     * 이를 통해 각 숫자가 커버할 수 있는 윈도우 크기를 구하고, 해당 크기의 최대 최소값을 갱신합니다.
     * 코드 분석:
     * left와 right 배열:
     * left[i]: 인덱스 i 왼쪽에서 arr[i]보다 작은 값의 가장 가까운 위치.
     * right[i]: 인덱스 i 오른쪽에서 arr[i]보다 작은 값의 가장 가까운 위치.
     * 스택 사용:
     * 단조 스택을 유지하며 각 방향에서 더 작은 값 탐색.
     * 윈도우 크기 계산:
     * right[i] - left[i] - 1: 숫자가 최소값으로 작용하는 최대 윈도우 크기.
     * 결과 처리:
     * maxMinForSize: 각 윈도우 크기별 최대 최소값 저장.
     * 큰 크기에서 작은 크기로 최대값 전파 (작은 윈도우는 큰 윈도우의 최소값을 넘을 수 없음).
     */
    fun riddle(arr: Array<Long>): Array<Long> {
        val n = arr.size
        val result = Array(n) { 0L }

        // 각 숫자의 왼쪽과 오른쪽에서 가장 가까운 작은 값의 위치를 찾기 위한 스택
        val stack = ArrayDeque<Int>()
        val left = IntArray(n) { -1 }  // 왼쪽 경계
        val right = IntArray(n) { n }  // 오른쪽 경계

        // 왼쪽에서 더 작은 값의 위치 계산
        for (i in 0 until n) {
            while (stack.isNotEmpty() && arr[stack.last()] >= arr[i]) {
                stack.removeLast()
            }
            if (stack.isNotEmpty()) left[i] = stack.last()
            stack.addLast(i)
        }

        stack.clear()

        // 오른쪽에서 더 작은 값의 위치 계산
        for (i in n - 1 downTo 0) {
            while (stack.isNotEmpty() && arr[stack.last()] >= arr[i]) {
                stack.removeLast()
            }
            if (stack.isNotEmpty()) right[i] = stack.last()
            stack.addLast(i)
        }

        // 각 숫자가 최소값으로 커버할 수 있는 최대 윈도우 크기 계산
        val maxMinForSize = LongArray(n + 1) { 0L }
        for (i in 0 until n) {
            val windowSize = right[i] - left[i] - 1
            maxMinForSize[windowSize] = maxOf(maxMinForSize[windowSize], arr[i])
        }

        // 큰 윈도우에서 작은 윈도우로 최대값 전파
        for (i in n - 1 downTo 1) {
            maxMinForSize[i] = maxOf(maxMinForSize[i], maxMinForSize[i + 1])
        }

        // 결과 배열 채우기 (1부터 n까지)
        for (i in 0 until n) {
            result[i] = maxMinForSize[i + 1]
        }

        return result
    }

    /**
     * 문제 이해:
     * n x n 격자에서 시작점 (startX, startY)에서 목표점 (goalX, goalY)까지 최소 이동 횟수를 구해야 합니다.
     * 이동 규칙: 한 행이나 열을 따라 장애물('X')이나 경계에 닿을 때까지 이동 가능.
     * '.'은 이동 가능, 'X'는 이동 불가.
     * 솔루션 접근법:
     * BFS(너비 우선 탐색)를 사용하여 최소 이동 횟수를 찾습니다.
     * 각 이동은 한 방향으로 끝까지 가는 것이므로, 가능한 모든 끝점까지 탐색 후 큐에 추가.
     * 방문 체크를 통해 중복 탐색 방지.
     * 코드 분석:
     * minimumMoves:
     * visited: 각 셀의 방문 여부 저장.
     * queue: BFS용 큐로 좌표와 이동 횟수 저장.
     * directions: 네 방향(오른쪽, 왼쪽, 아래, 위) 탐색.
     * BFS 과정:
     * 현재 위치에서 네 방향으로 이동 시도.
     * 각 방향에서 장애물이나 경계를 만날 때까지 이동.
     * 새로 방문한 위치를 큐에 추가하며 이동 횟수 증가.
     * 목표점에 도달하면 즉시 이동 횟수 반환.
     */
    fun minimumMoves(grid: Array<String>, startX: Int, startY: Int, goalX: Int, goalY: Int): Int {
        val n = grid.size
        // 방문 여부와 이동 횟수를 저장하는 배열
        val visited = Array(n) { BooleanArray(n) { false } }
        // BFS를 위한 큐: (x, y, moves)
        val queue = ArrayDeque<Triple<Int, Int, Int>>()

        // 시작 위치 추가
        queue.add(Triple(startX, startY, 0))
        visited[startX][startY] = true

        // 네 방향으로 이동 가능 (오른쪽, 왼쪽, 아래, 위)
        val directions = arrayOf(
            intArrayOf(0, 1),  // 오른쪽
            intArrayOf(0, -1), // 왼쪽
            intArrayOf(1, 0),  // 아래
            intArrayOf(-1, 0)  // 위
        )

        while (queue.isNotEmpty()) {
            val (x, y, moves) = queue.removeFirst()

            // 목표에 도달하면 이동 횟수 반환
            if (x == goalX && y == goalY) return moves

            // 각 방향으로 끝까지 이동 시도
            for (dir in directions) {
                var newX = x
                var newY = y

                // 막히거나 경계를 만날 때까지 이동
                while (true) {
                    val nextX = newX + dir[0]
                    val nextY = newY + dir[1]

                    // 경계 체크 또는 장애물 체크
                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || grid[nextX][nextY] == 'X') {
                        break
                    }

                    newX = nextX
                    newY = nextY

                    // 아직 방문하지 않은 경우 큐에 추가
                    if (!visited[newX][newY]) {
                        visited[newX][newY] = true
                        queue.add(Triple(newX, newY, moves + 1))
                    }
                }
            }
        }

        return -1 // 도달 불가능 (문제 조건상 발생하지 않음)
    }
}