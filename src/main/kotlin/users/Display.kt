package users

import java.awt.Dimension
import javax.swing.JFrame

class Display {

    fun show() {
        val frame = JFrame().apply {
            isVisible = true
            size = Dimension(800, 600)
        }
    }
}