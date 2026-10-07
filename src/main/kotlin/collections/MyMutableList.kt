package collections

interface MyMutableList<T> : MyMutableCollection<T> {

    override val size: Int

    override fun add(element: T): Boolean

    operator fun plus(element: T)

    fun add(index: Int, element: T)

    operator fun get(index: Int): T

    operator fun set(index: Int, element: T)

    fun removeAt(index: Int)

    override fun remove(element: T)

    operator fun minus(element: T)

    override fun clear()

    override fun contains(element: T): Boolean
}