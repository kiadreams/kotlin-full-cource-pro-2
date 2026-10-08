package collections

fun main() {
    val mutNumbers = myListOf(1, 2, 3, 4, 5)
    (mutNumbers as MyMutableList<Int>).add(100)
    mutNumbers.forEach(::println)

}
