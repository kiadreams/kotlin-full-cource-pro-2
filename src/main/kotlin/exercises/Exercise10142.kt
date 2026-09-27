package exercises

import javax.swing.JLabel
import java.awt.Color

fun main() {
    // Пример 1: Настройка объекта с помощью прямых вызовов
    val labelDirect = JLabel()
    labelDirect.text = "Hello, World!"
    labelDirect.font = labelDirect.font.deriveFont(16f)
    labelDirect.foreground = Color.BLUE

    // Пример 2: Настройка объекта с помощью функции `with`
    val labelWith = JLabel()
    with(labelWith) {
        text = "Hello, World!"
        font = font.deriveFont(16f)
        foreground = Color.BLUE
    }

    // Пример 3: Настройка объекта с помощью функции `also`
    val labelAlso = JLabel().also { label ->
        label.text = "Hello, World!"
        label.font = label.font.deriveFont(16f)
        label.foreground = Color.BLUE
    }

    // Пример 4: Настройка объекта с помощью функции `let`
    val labelLet = JLabel().let { label ->
        label.text = "Hello, World!"
        label.font = label.font.deriveFont(16f)
        label.foreground = Color.BLUE
        label // Возвращаем объект
    }

    val labelApply = JLabel().apply {
        text = "Hello, World!"
        font = font.deriveFont(16f)
        foreground = Color.BLUE
    }

    println(labelDirect)
    println(labelWith)
    println(labelAlso)
    println(labelLet)
    println(labelApply)
}