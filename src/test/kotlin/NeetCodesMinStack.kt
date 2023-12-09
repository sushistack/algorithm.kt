import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag

@Tag("Medium")
@Tag("Stack")
@DisplayName("""
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    
    Implement the MinStack class:
    
    MinStack() initializes the stack object.
    void push(int val) pushes the element val onto the stack.
    void pop() removes the element on the top of the stack.
    int top() gets the top element of the stack.
    int getMin() retrieves the minimum element in the stack.
    You must implement a solution with O(1) time complexity for each function.
""")
class NeetCodesMinStack {
    private val d = mutableListOf<Int>()
    private val min = mutableListOf<Int>()

    fun push(`val`: Int) {
        d.add(`val`)
        min.add(minOf(`val`, if(min.isEmpty()) `val` else min.last() ))
    }

    fun pop() {
        d.removeLast()
        min.removeLast()
    }

    fun top(): Int {
        return d.last()
    }

    fun getMin(): Int {
        return min.last()
    }

}