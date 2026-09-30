package chapter11.dogs

import chapter11.observer.Observer
import java.awt.Dimension
import java.awt.Font
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.WindowConstants

class Display {

    fun show() {
        val textArea = JTextArea().apply {
            isEditable = false
            text = "Hello App!"
            font = Font(Font.SANS_SERIF, Font.PLAIN, 18)
            margin = Insets(50, 50, 50, 50)
        }
        val scrollPane = JScrollPane(textArea)
        JFrame().apply {
            isVisible = true
            size = Dimension(800, 600)
            isResizable = true
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            add(scrollPane)
        }

        DogRepository.getInstance("dog-shelter").addOnDogsChangeListeners { dogs ->
            dogs.joinToString("\n").let { textArea.text = it }
        }
    }
}