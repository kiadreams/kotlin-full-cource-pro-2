package collections

import kotlin.time.measureTime

fun main() {
    val numbers = NumbersArrayList()
//    val numbers = arrayListOf<Int>()
    val time = measureTime {
        repeat(1000_000) { numbers.add(0, it) }
    }
    println(time)
}
