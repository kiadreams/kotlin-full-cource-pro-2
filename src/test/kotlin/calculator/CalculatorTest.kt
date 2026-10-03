package calculator

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class CalculatorTest {

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `when 5 add to 10 then result 15`(calculator: Calculator) {
        val result = calculator.sum(10, 5)
        val expected = 15
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `when 100 add to 50 then result 150`(calculator: Calculator) {
        val result = calculator.sum(100, 50)
        val expected = 150
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `when 100 multip to 50 then result 5000`(calculator: Calculator) {
        val result = calculator.multiplication(100, 50)
        val expected = 5000
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `when 2 multip to 3 then result 4`(calculator: Calculator) {
        val result = calculator.multiplication(2, 3)
        val expected = 6
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `when 10 division to 2 then result 5`(calculator: Calculator) {
        val result = calculator.division(10, 2)
        val expected = 5.0
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `when 100 division to 10 then result 10`(calculator: Calculator) {
        val result = calculator.division(100, 10)
        val expected = 10.0
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `when 10 subtraction to 2 then result 8`(calculator: Calculator) {
        val result = calculator.subtraction(10, 2)
        val expected = 8
        assertEquals(expected, result)
    }


    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun `when 100 subtraction to 11 then result 89`(calculator: Calculator) {
        val result = calculator.subtraction(100, 11)
        val expected = 89
        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        fun calculatorsSource() = listOf<Calculator>(SimpleCalculator(), LoggingCalculator())
    }

}