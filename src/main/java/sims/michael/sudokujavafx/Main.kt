package sims.michael.sudokujavafx

import javafx.application.Application
import javafx.css.PseudoClass
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.scene.control.TextFormatter
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import java.net.URL


class Main : Application() {
    override fun start(primaryStage: Stage) {
        val board = GridPane()

        val right = PseudoClass.getPseudoClass("right")
        val bottom = PseudoClass.getPseudoClass("bottom")

        for (col in 0 until 9) {
            for (row in 0 until 9) {
                val cell = StackPane()
                cell.styleClass.add("cell")
                cell.pseudoClassStateChanged(right, col == 2 || col == 5)
                cell.pseudoClassStateChanged(bottom, row == 2 || row == 5)
                cell.children.add(createTextField())
                board.add(cell, col, row)
            }
        }

        val scene = Scene(board)
        scene.stylesheets.add("/sims/michael/sudokufx/style.css")


        val resource: URL = javaClass.getResource("sample.fxml")
        val root = FXMLLoader.load<Parent>(resource)
        primaryStage.title = "Hello World"
        //primaryStage.isResizable = false
        //primaryStage.scene = scene
        primaryStage.scene = Scene(root, 300.0, 275.0)
        primaryStage.show()
    }

    private fun createTextField(): TextField {
        return TextField().apply {
            textFormatter = TextFormatter<Int> { c -> c.takeIf { it.controlNewText.matches("\\d?".toRegex()) } }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = launch(Main::class.java, *args)
    }
}

class Controller {}