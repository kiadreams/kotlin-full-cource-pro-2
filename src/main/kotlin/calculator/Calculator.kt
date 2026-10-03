package calculator

interface Calculator {
    fun sum(a: Int, b: Int): Int

    fun multiplication(a: Int, b: Int): Int

    fun division(a: Int, b: Int): Double

    fun subtraction(a: Int, b: Int): Int
}