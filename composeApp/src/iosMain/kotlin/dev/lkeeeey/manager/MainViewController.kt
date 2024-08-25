package dev.lkeeeey.manager

import androidx.compose.ui.window.ComposeUIViewController
import dev.lkeeeey.manager.common.Context

fun MainViewController() = ComposeUIViewController { App(Context()) }