package chapter11.dogs

import java.awt.Dimension
import java.awt.Font
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

class DisplayDog {

    fun show() {
        val textArea = JTextArea().apply {
            isEditable = false
            text = "Hello World!"
            font = Font(Font.SANS_SERIF, Font.PLAIN, 16)
            margin = Insets(32, 32, 32, 32)
        }
        val scrollPane = JScrollPane(textArea)
        val frame = JFrame().apply {
            isVisible = true
            size = Dimension(1600, 600)
            isResizable = true
            add(scrollPane)
        }
        DogRepository.getInstance("dog-shelter")
            .dogs
            .joinToString("\n")
            .let { textArea.text = it }
    }
}