package dev.lkeeeey.manager.presentation

import androidx.lifecycle.ViewModel
import dev.lkeeeey.manager.presentation.token_manager.TokenManager


class SharedViewModel(
    val tokenManager: TokenManager,
) : ViewModel()