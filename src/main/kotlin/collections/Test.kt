package collections

import kotlin.random.Random
import kotlin.time.measureTime

fun main() {
    val numbers = NumbersHashSet()
    repeat(100) {
        numbers.add(it)
    }
    println(numbers.contains(56))
    numbers.remove(56)
    println(numbers.contains(56))
}
