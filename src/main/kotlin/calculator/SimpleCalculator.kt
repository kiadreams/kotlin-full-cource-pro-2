package calculator

class SimpleCalculator : Calculator {
    override fun sum(a: Int, b: Int) = a + b

    override fun multiplication(a: Int, b: Int) = a * b

    override fun division(a: Int, b: Int) = a.toDouble() / b

    override fun subtraction(a: Int, b: Int) = a - b
}