package collections

class MyLinkedList<T> : MyMutableList<T> {

    private var modCount: Int = 0
    private var first: Node<T>? = null
    private var last: Node<T>? = null
    override var size: Int = 0
        private set

    override fun add(element: T): Boolean {
        modCount++
        val prevLast = last
        last = Node(prevLast, element, null)
        if (prevLast == null) {
            first = last
        } else {
            prevLast.next = last
        }
        size++
        return true
    }

    override fun add(index: Int, element: T) {
        modCount++
        checkIndexToAdd(index)
        if (index == size) {
            add(element)
            return
        }
        if (index == 0) {
            val node = Node(null, element, first)
            first?.prev = node
            first = node
            size++
            return
        }
        val before = getNode(index - 1)
        val after = before.next
        val newNode = Node(before, element, after)
        before.next = newNode
        after?.prev = newNode
        size++
    }

    override fun plus(element: T) {
        add(element)
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return getNode(index).item
    }

    override fun set(index: Int, element: T) {
        add(index, element)
    }

    override fun removeAt(index: Int) {
        modCount++
        checkIndex(index)
        val node = getNode(index)
        unlink(node)
    }

    override fun remove(element: T) {
        modCount++
        var node = first
        repeat(size) {
            if (node?.item == element) {
                unlink(node)
                return
            }
            node = node?.next
        }
    }

    override fun minus(element: T) {
        remove(element)
    }

    override fun clear() {
        modCount++
        first = null
        last = null
        size = 0
    }

    override fun contains(element: T): Boolean {
        var node = first
        repeat(size) {
            if (node?.item == element) {
                return true
            }
            node = node?.next
        }
        return false
    }

    override fun iterator(): MutableIterator<T> {
        return object : MutableIterator<T> {
            private var nextNode: Node<T>? = first
            private val currentModCount = modCount

            override fun hasNext(): Boolean {
                return nextNode != null
            }

            @Suppress("UNCHECKED_CAST")
            override fun next(): T {
                if (currentModCount != modCount) throw ConcurrentModificationException()
                return (nextNode?.item as T)
                    .also { nextNode = nextNode?.next }
            }

            override fun remove() {
                TODO("Not yet implemented")
            }
        }
    }

    private fun getNode(index: Int): Node<T> {
        if (index == 0) return first!!
        if (index == size - 1) return last!!

        if (index < size / 2) {
            var node = first
            repeat(index) {
                node = node?.next
            }
            return node!!
        } else {
            var node = last
            repeat(size - index - 1) {
                node = node?.prev
            }
            return node!!
        }
    }

    private fun checkIndex(index: Int) {
        if (index !in 0 until size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
    }

    private fun checkIndexToAdd(index: Int) {
        if (index !in 0..size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
    }

    private fun unlink(node: Node<T>) {
        val before = node.prev
        val after = node.next
        before?.next = after
        after?.prev = before
        if (after == null) {
            last = before
        }
        if (before == null) {
            first = after
        }
        size--
    }

    class Node<T>(
        var prev: Node<T>? = null,
        var item: T,
        var next: Node<T>? = null
    )
}