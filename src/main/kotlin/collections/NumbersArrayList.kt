package collections

class NumbersArrayList(initialCapacity: Int = INITIAL_CAPACITY) : NumbersMutableList {

    private var numbers = arrayOfNulls<Int>(initialCapacity)
    override var size: Int = 0
        private set

    override fun add(number: Int) {
        growIfNeeded()
        numbers[size] = number
        size++
    }

    override fun plus(number: Int) {
        add(number)
    }

    override fun add(index: Int, number: Int) {
        checkIndexToAdd(index)
        growIfNeeded()
        System.arraycopy(numbers, index, numbers, index + 1, size - index)
        numbers[index] = number
        size++
    }

    override fun set(index: Int, number: Int) {
        add(index, number)
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return numbers[index]!!
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        System.arraycopy(numbers, index + 1, numbers, index, size - index - 1)
        size--
        numbers[size] = null
    }

    override fun remove(number: Int) {
        for (i in 0 until size) {
            if (numbers[i] == number) {
                removeAt(i)
                return
            }
        }
    }

    override fun minus(number: Int) {
        remove(number)
    }

    override fun clear() {
        numbers = arrayOfNulls<Int>(INITIAL_CAPACITY)
        size = 0
    }

    override fun contains(number: Int): Boolean {
        for (i in numbers.indices) {
            if (numbers[i] == number) {
                return true
            }
        }
        return false
    }

    private fun checkIndex(index: Int) {
        if (index !in 0 until size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
    }

    private fun checkIndexToAdd(index: Int) {
        if (index !in 0.. size) {
            throw IndexOutOfBoundsException("Index: $index, Size: $size")
        }
    }

    private fun growIfNeeded() {
        if (numbers.size == size) {
            val newNumbers = arrayOfNulls<Int>(size * 2)
            System.arraycopy(numbers, 0, newNumbers, 0, size)
            numbers = newNumbers
        }
    }

    companion object {
        private const val INITIAL_CAPACITY = 10
    }
}
