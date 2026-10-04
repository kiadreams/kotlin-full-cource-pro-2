package collections

class NumbersLinkedList : NumbersMutableList {

    private var first: Node? = null
    private var last: Node? = null
    override var size: Int = 0
        private set

    override fun add(number: Int) {
        if (size == 0) {
            val node = Node(number)
            first = node
            last = node
            size++
            return
        }
        val newNode = Node(number)
        last?.next = newNode
        last = newNode
        size++
    }

    override fun add(index: Int, number: Int) {
        checkIndexToAdd(index)
        if (index == size) {
            add(number)
            return
        }
        if (index == 0) {
            val node = Node(number, first)
            first = node
            size++
            return
        }
        val before = getNode(index - 1)
        val after = before.next
        val newNode = Node(number, after)
        before.next = newNode
        size++
    }

    override fun plus(number: Int) {
        add(number)
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return getNode(index).item
    }

    override fun set(index: Int, number: Int) {
        add(index, number)
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        if (index == 0 && size == 1) {
            clear()
            return
        }
        if (index == 0) {
            first = first?.next
            size--
            return
        }
        val before = getNode(index - 1)
        val after = before.next?.next
        before.next = after
        if (after == null) {
            last = before
        }
        size--
    }

    override fun remove(number: Int) {
        if (size == 1 && first?.item == number) {
            removeAt(0)
            return
        }
        var before = first
        while (before != last) {
            val node = before?.next
            if (node?.item == number) {
                val after = node.next
                before.next = after
                if (after == null) {
                    last = before
                }
                size--
                return
            }
            before = node
        }
    }

    override fun minus(number: Int) {
        remove(number)
    }

    override fun clear() {
        first = null
        last = null
        size = 0
    }

    override fun contains(number: Int): Boolean {
        var node = first
        while (node != last) {
            if (node?.item == number) {
                return true
            }
            node = node?.next
        }
        return false
    }

    private fun getNode(index: Int): Node {
        if (index == 0) return first!!
        if (index == size - 1) return last!!

        var node = first
        repeat(index) {
            node = node?.next
        }
        return node!!
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

    class Node(
        var item: Int,
        var next: Node? = null
    )
}