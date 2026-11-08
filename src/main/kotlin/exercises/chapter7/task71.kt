package exercises.chapter7

fun main() {
    val enginePow = readln().trim().toInt()

    val tax = if (enginePow <= 100) 10
    else if (enginePow <= 150) 34
    else if (enginePow <= 200) 49
    else if (enginePow <= 250) 75
    else 150

    println(
        """
        Вид ТС: легковой автомобиль
        Мощность двигателя: $enginePow л.с.
        Налоговая ставка: $tax руб./л.с.
        Сумма налога: ${tax * enginePow} руб.
    """.trimIndent()
    )
}