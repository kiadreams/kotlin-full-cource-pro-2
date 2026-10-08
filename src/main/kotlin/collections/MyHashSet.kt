package collections

import kotlin.math.abs

class MyHashSet<T> : MyMutableSet<T> {

    private var modCount: Int = 0
    private var elements = arrayOfNulls<Node<T>>(INITIAL_CAPACITY)
    override var size: Int = 0
        private set

    override fun add(element: T): Boolean {
        modCount++
        if (size >= elements.size * LOAD_FACTOR) {
            increaseArray()
        }
        return add(element, elements).also { added -> if (added) size++ }
    }

    override fun remove(element: T) {
        modCount++
        val elementPosition = getElementPosition(element, elements.size)
        var node: Node<T>? = elements[elementPosition] ?: return
        if (node?.item == element) {
            elements[elementPosition] = node.next
            size--
            return
        }
        while (node != null) {
            val afterNode = node.next
            if (afterNode?.item == element) {
                node.next = afterNode.next
                size--
                return
            }
            node = afterNode?.next
        }
    }

    override fun clear() {
        modCount++
        elements = arrayOfNulls<Node<T>>(INITIAL_CAPACITY)
        size = 0
    }

    override fun contains(element: T): Boolean {
        val elementPosition = getElementPosition(element, elements.size)
        var node = elements[elementPosition]
        while (node != null) {
            if (node.item == element) {
                return true
            }
            node = node.next
        }
        return false
    }

    override fun iterator(): MutableIterator<T> {
        return object : MutableIterator<T> {
            private val currentModCount = modCount
            private var currentPosition = 0
            private var nextNode: Node<T>? = elements[currentPosition]
            private var count = 0

            override fun hasNext(): Boolean {
                return count < size
            }

            @Suppress("UNCHECKED_CAST")
            override fun next(): T {
                if (currentModCount != modCount) throw ConcurrentModificationException()
                while (nextNode == null) {
                    nextNode = elements[++currentPosition]
                }
                return (nextNode?.item as T).also {
                    count++
                    nextNode = nextNode?.next
                }
            }

            override fun remove() {
                TODO("Not yet implemented")
            }
        }
    }

    private fun increaseArray() {
        val newArray = arrayOfNulls<Node<T>>(elements.size * 2)
        for (element in elements) {
            var currentElement = element
            while (currentElement != null) {
                add(currentElement.item, newArray)
                currentElement = currentElement.next
            }
        }
        elements = newArray
    }

    private fun add(element: T, array: Array<Node<T>?>): Boolean {
        val newNode = Node(element, null)
        val position = getElementPosition(element, array.size)
        if (array[position] == null) {
            array[position] = newNode
            return true
        }
        var nextNode = array[position]
        while (true) {
            if (nextNode?.item == element) {
                return false
            }
            if (nextNode?.next == null) {
                nextNode?.next = newNode
                return true
            }
            nextNode = nextNode.next
        }
    }

    private fun getElementPosition(element: T, arraySize: Int): Int {
        return abs(element.hashCode() % arraySize)
    }

    class Node<T>(
        var item: T, var next: Node<T>? = null
    )

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }
}