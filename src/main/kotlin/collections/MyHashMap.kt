package collections

import kotlin.math.abs

class MyHashMap<K, V> : MyMutableMap<K, V> {

    private var elements = arrayOfNulls<Node<K, V>>(INITIAL_CAPACITY)
    override var size: Int = 0
        private set

    override fun put(key: K, value: V): V? {
        if (size >= elements.size * LOAD_FACTOR) {
            increaseArray()
        }
        return put(key, value, elements).also { oldValue ->
            if (oldValue == null) size++
        }
    }

    private fun put(key: K, value: V, array: Array<Node<K, V>?>): V? {
        val newNode = Node(key, value)
        val position = getElementPosition(key, array.size)
        if (array[position] == null) {
            array[position] = newNode
            return null
        }
        var nextNode = array[position]
        while (true) {
            if (nextNode?.key == key) {
                return nextNode.value.also {
                    nextNode.value = value
                }
            }
            if (nextNode?.next == null) {
                nextNode?.next = newNode
                return null
            }
            nextNode = nextNode.next
        }
    }

    override fun remove(key: K): V? {
        val elementPosition = getElementPosition(key, elements.size)
        var node: Node<K, V>? = elements[elementPosition] ?: return null
        if (node?.key == key) {
            elements[elementPosition] = node.next
            size--
            return node.value
        }
        while (node != null) {
            val afterNode = node.next
            if (afterNode?.key == key) {
                node.next = afterNode.next
                size--
                return afterNode.value
            }
            node = afterNode?.next
        }
        return null
    }

    override fun clear() {
        elements = arrayOfNulls<Node<K, V>>(INITIAL_CAPACITY)
        size = 0
    }


    override fun get(key: K): V? {
        val elementPosition = getElementPosition(key, elements.size)
        var node = elements[elementPosition]
        while (node != null) {
            if (node.key == key) {
                return node.value
            }
            node = node.next
        }
        return null
    }

    override fun containsValue(value: V): Boolean {
        foreach {
            if (it.value == value) return true
        }
        return false
    }

    override val keys: MySet<K>
        get() = MyHashSet<K>().apply {
            foreach {
                add(it.key)
            }
        }
    override val values: MyCollection<V>
        get() = MyArrayList<V>().apply {
            foreach {
                add(it.value)
            }
        }


    private inline fun foreach(operation: (Node<K, V>) -> Unit) {
        for (node in elements) {
            var currentElement = node
            while (currentElement != null) {
                operation(currentElement)
                currentElement = currentElement.next
            }
        }

    }

    override fun containsKey(key: K): Boolean {
        val elementPosition = getElementPosition(key, elements.size)
        var node = elements[elementPosition]
        while (node != null) {
            if (node.key == key) {
                return true
            }
            node = node.next
        }
        return false
    }

    private fun increaseArray() {
        val newArray = arrayOfNulls<Node<K, V>>(elements.size * 2)
        for (element in elements) {
            var currentElement = element
            while (currentElement != null) {
                put(currentElement.key, currentElement.value, newArray)
                currentElement = currentElement.next
            }
        }
        elements = newArray
    }

    private fun getElementPosition(key: K, arraySize: Int): Int {
        return abs(key.hashCode() % arraySize)
    }

    class Node<K, V>(
        val key: K,
        var value: V,
        var next: Node<K, V>? = null
    )

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }
}