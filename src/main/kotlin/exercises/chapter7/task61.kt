package exercises.chapter7


fun main() {
    val sum = readln().toInt()

    println("""
        Вам доступно:
        100$ - ${sum / 100}шт
        50$ - ${sum % 100 / 50}шт
        20$ - ${sum % 100 % 50 / 20}шт
        10$ - ${sum % 100 % 50 % 20 / 10}шт
        5$ - ${sum % 100 % 50 % 20 % 10 / 5}шт
        2$ - ${sum % 100 % 50 % 20 % 10 % 5 / 2}шт
        1$ - ${sum % 100 % 50 % 20 % 10 % 5 % 2}шт
    """.trimIndent())
}
