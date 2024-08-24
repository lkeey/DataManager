package dev.lkeeeey.manager

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DataManager",
    ) {
        App()
    }
}