package collections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MyMutableListTest {

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 1 element then size is 1`(list: MyMutableList<Int>) {
        list.add(1)
        assertEquals(1, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 10 element used operator plus`(list: MyMutableList<Int>) {
        repeat(10) { list + it }
        assertEquals(10, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 10 elements then size is 10`(list: MyMutableList<Int>) {
        repeat(10) { list.add(it + 1) }
        assertEquals(10, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get 5th element used operator get`(list: MyMutableList<Int>) {
        repeat(10) { list.add(it) }
        assertEquals(5, list[5])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get 50th element then result is correct`(list: MyMutableList<Int>) {
        repeat(100) { list.add(it) }
        assertEquals(50, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element removed then size decreased`(list: MyMutableList<Int>) {
        repeat(100) { list.add(it) }
        list.removeAt(50)
        assertEquals(99, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed 50th element next value at this position`(list: MyMutableList<Int>) {
        repeat(100) { list.add(it) }
        list.removeAt(50)
        assertEquals(51, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed last element it will be null`(list: MyMutableList<Int>) {
        repeat(10) { list.add(it) }
        list.removeAt(9)
        assertEquals(8, list[8])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed value 50 next value at this position`(list: MyMutableList<Int>) {
        repeat(100) { list.add(it) }
        list.remove(50)
        assertEquals(51, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removed value 50 used operator minus`(list: MyMutableList<Int>) {
        repeat(100) { list.add(it) }
        list - 50
        assertEquals(51, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element add to first position this element must be first`(list: MyMutableList<Int>) {
        repeat(100) { list.add(it) }
        list.add(5, 1000)
        assertEquals(1000, list[5])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element add to last position this element must be last`(list: MyMutableList<Int>) {
        repeat(10) { list.add(it) }
        list.add(9, 1000)
        assertEquals(1000, list[9])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When collection is cleaning then it's must be empty`(list: MyMutableList<Int>) {
        repeat(10) { list.add(it) }
        list.clear()
        assertEquals(0, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When collection contains element then method return true`(list: MyMutableList<Int>) {
        repeat(100) { list.add(it) }
        assertTrue(list.contains(90))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When collection doesn't contain element then method return false`(list: MyMutableList<Int>) {
        repeat(100) { list.add(it) }
        assertFalse(list.contains(110))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When set values used set operator`(list: MyMutableList<Int>) {
        repeat(100) { list[it] = it }
        assertEquals(55, list[55])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method get invoked with wrong index then exception is thrown`(list: MyMutableList<Int>) {
        repeat(10) { list[it] = it }
        assertThrows<IndexOutOfBoundsException> { list[55] }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method add by index invoked with wrong index then exception is thrown`(list: MyMutableList<Int>) {
        repeat(10) { list[it] = it }
        assertThrows<IndexOutOfBoundsException> { list[11] = 5 }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method removeAt index invoked with wrong index then exception is thrown`(list: MyMutableList<Int>) {
        repeat(10) { list[it] = it }
        assertThrows<IndexOutOfBoundsException> { list.removeAt(50) }
    }

    companion object {

        @JvmStatic
        fun mutableListSource() = listOf(MyArrayList(), MyLinkedList<Int>())
    }
}