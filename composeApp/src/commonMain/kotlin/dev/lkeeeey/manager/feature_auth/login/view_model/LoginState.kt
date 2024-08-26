package presentation.ui.splash.view_model

import dev.lkeeeey.manager.business.core.NetworkState
import dev.lkeeeey.manager.business.core.ProgressBarState
import dev.lkeeeey.manager.business.core.Queue
import dev.lkeeeey.manager.business.core.UIComponent


data class LoginState(
    val nameRegister: String = "",
    val usernameLogin: String = "",
    val passwordLogin: String = "",

    val isTokenValid: Boolean = false,
    val navigateToMain: Boolean = false,

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
