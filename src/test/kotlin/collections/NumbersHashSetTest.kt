package collections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NumbersHashSetTest {

    private val numbers = NumbersHashSet()

    @Test
    fun `When added one number then size must be 1`() {

        repeat(100) {
            numbers.add(it)
        }
        assertEquals(100, numbers.size)
    }

    @Test
    fun `When added number to set result must be true`() {
        assertTrue(numbers.add(56))
    }

    @Test
    fun `When added the same number result must be false`() {
        numbers.add(56)
        assertFalse(numbers.add(56))
    }


    @Test
    fun `When removed number then size must be decrease`() {
        repeat(100) {
            numbers.add(it)
        }
        val firstSize = numbers.size
        numbers.remove(56)
        assertEquals(1, firstSize - numbers.size)
    }

    @Test
    fun `When cleared set then size must be 0`() {
        repeat(100) {
            numbers.add(it)
        }
        numbers.clear()
        assertEquals(0, numbers.size)
    }

    @Test
    fun `When added the number check contains another number must be false`() {
        numbers.add(56)
        assertFalse(numbers.contains(1))
    }

    @Test
    fun `When added the number check contains the same number must be true`() {
        numbers.add(56)
        assertTrue(numbers.contains(56))
    }
}