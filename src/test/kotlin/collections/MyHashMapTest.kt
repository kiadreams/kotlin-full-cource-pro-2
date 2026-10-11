package collections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyHashMapTest {

    private val myHashMap = MyHashMap<Int, String>()

    @Test
    fun put() {
        myHashMap.put(1, "1")
        myHashMap.put(2, "2")
        assertEquals(2, myHashMap.size)
    }

    @Test
    fun remove() {
        myHashMap.put(2, "2")
        assertEquals("2", myHashMap.remove(2))
    }

    @Test
    fun clear() {
        myHashMap.put(2, "2")
        myHashMap.put(1, "2")
        myHashMap.clear()
        assertEquals(0, myHashMap.size)
    }

    @Test
    fun get() {
        myHashMap.put(2, "2")
        assertEquals("2", myHashMap[2])
    }

    @Test
    fun containsValue() {
        myHashMap.put(2, "2")
        assertTrue(myHashMap.containsValue("2"))
    }

    @Test
    fun getKeys() {
        myHashMap.put(2, "2")
        myHashMap.put(1, "1")
        assertEquals(2, myHashMap.keys.size)
    }

    @Test
    fun getValues() {
        myHashMap.put(2, "2")
        myHashMap.put(1, "1")
        assertEquals(2, myHashMap.values.size)
    }

    @Test
    fun containsKey() {
        myHashMap.put(1, "1")
        assertTrue(myHashMap.containsKey(1))
    }
}