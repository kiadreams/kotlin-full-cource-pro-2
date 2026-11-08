package chapter13.collbacks

import chapter13.entities.Author
import chapter13.entities.Book
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea

object Display {

    private val infoAria = JTextArea().apply {
        isEditable = false
    }

    private val loadButton = JButton("Load Book").apply {
        addActionListener {
            isEnabled = false
            infoAria.text = "Loading book information...\n"
            val book = loadBook()
            infoAria.append("""
                Book: ${book.title}
                Year: ${book.year}
                Genre: ${book.genre}
            """.trimIndent())
            infoAria.append("\nLoading author information...\n")
            val author  = loadAuthors(book)
            infoAria.append("""
                Author: ${author.name}
                Biography: ${author.bio}
            """.trimIndent())
            isEnabled = true
        }
    }
    private val timerLabel = JLabel("Time: 00:00")
    private val topPanel = JPanel(BorderLayout()).apply {
        add(timerLabel, BorderLayout.WEST)
        add(loadButton, BorderLayout.EAST)
    }

    private val mainFrame = JFrame("Book and Author").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(infoAria), BorderLayout.CENTER)
        size = Dimension(800, 600)
    }

    fun show() {
        mainFrame.isVisible = true
        startTimer()
    }

    private fun loadBook(): Book {

        Thread.sleep(3000)
        return Book("1984", 1949, "Distopia")
    }

    private fun loadAuthors(book: Book): Author {
        Thread.sleep(3000)
        return Author("George Orwell", "British writer and journalist")
    }

    private fun startTimer() {
        var totalSeconds = 0
        while (true) {
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            timerLabel.text = "Time: %02d:%02d".format(minutes, seconds)
            Thread.sleep(1000)
            totalSeconds++
        }
    }
}