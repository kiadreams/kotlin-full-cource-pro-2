package collections

import kotlin.math.abs

class NumbersHashSet : NumbersMutableSet {

    private var elements = arrayOfNulls<Node>(INITIAL_CAPACITY)
    override var size: Int = 0
        private set

    override fun add(number: Int): Boolean {
        if (size >= elements.size * LOAD_FACTOR) {
            increaseArray()
        }
        return add(number, elements).also { added -> if (added) size++ }
    }

    override fun remove(number: Int) {
        val elementPosition = getElementPosition(number, elements.size)
        var element: Node? = elements[elementPosition] ?: return
        if (element?.item == number) {
            elements[elementPosition] = element.next
            size--
            return
        }
        while (element != null) {
            val after = element.next
            if (after?.item == number) {
                element.next = after.next
                size--
                return
            }
            element = after?.next
        }
    }

    override fun clear() {
        elements = arrayOfNulls<Node>(INITIAL_CAPACITY)
        size = 0
    }

    override fun contains(number: Int): Boolean {
        val elementPosition = getElementPosition(number, elements.size)
        var element = elements[elementPosition]
        while (element != null) {
            if (element.item == number) {
                return true
            }
            element = element.next
        }
        return false
    }

    private fun increaseArray() {
        val newArray = arrayOfNulls<Node>(elements.size * 2)
        for (element in elements) {
            var currentElement = element
            while (currentElement != null) {
                add(currentElement.item, newArray)
                currentElement = currentElement.next
            }
        }
        elements = newArray
    }

    private fun add(number: Int, array: Array<Node?>): Boolean {
        val newElement = Node(number, null)
        val position = getElementPosition(number, array.size)
        if (array[position] == null) {
            array[position] = newElement
            return true
        }
        var nextElement = array[position]
        while (true) {
            if (nextElement?.item == number) {
                return false
            }
            if (nextElement?.next == null) {
                nextElement?.next = newElement
                return true
            }
            nextElement = nextElement.next
        }
    }

    private fun getElementPosition(number: Int, arraySize: Int): Int {
        return abs(number % arraySize)
    }

    class Node(
        var item: Int,
        var next: Node? = null
    )

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }
}