package collections

import kotlin.time.measureTime

fun main() {
    val numbers1 = NumbersArrayList()
    val numbers2 = arrayListOf<Int>()
    val numbers3 = NumbersLinkedList()
    val time = measureTime {
        repeat(1_000_000) { numbers3.add(0, it) }
    }
    println(time)
}
