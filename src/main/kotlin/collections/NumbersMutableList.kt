package collections

interface NumbersMutableList {

    val size: Int

    fun add(number: Int)

    operator fun plus(number: Int)

    fun add(index: Int, number: Int)

    operator fun get(index: Int): Int

    operator fun set(index: Int, number: Int)

    fun removeAt(index: Int)

    fun remove(number: Int)

    operator fun minus(number: Int)

    fun clear()

    fun contains(number: Int): Boolean
}