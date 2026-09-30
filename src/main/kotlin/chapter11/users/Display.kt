package chapter11.users

import chapter11.observer.Observer
import java.awt.Dimension
import java.awt.Font
import java.awt.Insets
import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextArea

class Display {

    fun show() {
        val textArea = JTextArea().apply {
            isEditable = false
            text = "Hello App!"
            font = Font(Font.SANS_SERIF, Font.PLAIN, 24)
            margin = Insets(50, 100, 50, 100)
        }
        val scrollPane = JScrollPane(textArea)
        JFrame().apply {
            isVisible = true
            size = Dimension(1024, 600)
            isResizable = true
            add(scrollPane)
            defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        }

        UsersRepository.getInstance("qwerty").addOnUsersChangeListener { users ->
            users.joinToString("\n").let { textArea.text = it }
        }
    }
}