package presentation.ui.splash.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import business.constants.CUSTOM_TAG
import dev.lkeeeey.manager.business.core.DataState
import dev.lkeeeey.manager.business.core.NetworkState
import dev.lkeeeey.manager.business.core.Queue
import dev.lkeeeey.manager.business.core.UIComponent
import dev.lkeeeey.manager.business.interactors.CheckTokenInteractor
import dev.lkeeeey.manager.business.interactors.LoginInteractor
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginViewModel(
    private val loginInteractor: LoginInteractor,
//    private val registerInteractor: RegisterInteractor,
    private val checkTokenInteractor: CheckTokenInteractor,
) : ViewModel() {


    val state: MutableState<LoginState> = mutableStateOf(LoginState())

    fun onTriggerEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.Login -> {
                login()
            }

            is LoginEvent.Register -> {
                register()
            }

            is LoginEvent.OnUpdateNameRegister -> {
                onUpdateNameRegister(event.value)
            }

            is LoginEvent.OnUpdatePasswordLogin -> {
                onUpdatePasswordLogin(event.value)
            }

            is LoginEvent.OnUpdateUsernameLogin -> {
                onUpdateUsernameLogin(event.value)
            }

            is LoginEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }

            is LoginEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }

            is LoginEvent.OnRetryNetwork -> {
                onRetryNetwork()
            }

            is LoginEvent.OnUpdateNetworkState -> {
                onUpdateNetworkState(event.networkState)
            }
        }
    }

    init {
        checkToken()
    }

    private fun checkToken() {
        checkTokenInteractor.execute().onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    onTriggerEvent(LoginEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    state.value = state.value.copy(isTokenValid = dataState.data ?: false)
                    state.value = state.value.copy(navigateToMain = dataState.data ?: false)
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun login() {
        loginInteractor.execute(
            email = state.value.usernameLogin,
            password = state.value.passwordLogin,
        ).onEach { dataState ->
            when (dataState) {
                is DataState.NetworkStatus -> {}
                is DataState.Response -> {
                    onTriggerEvent(LoginEvent.Error(dataState.uiComponent))
                }

                is DataState.Data -> {
                    state.value =
                        state.value.copy(navigateToMain = !dataState.data.isNullOrEmpty())
                }

                is DataState.Loading -> {
                    state.value =
                        state.value.copy(progressBarState = dataState.progressBarState)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun register() {
//        registerInteractor.execute(
//            email = state.value.usernameLogin,
//            password = state.value.passwordLogin,
//            name = state.value.nameRegister
//        ).onEach { dataState ->
//            when (dataState) {
//                is DataState.NetworkStatus -> {}
//                is DataState.Response -> {
//                    onTriggerEvent(LoginEvent.Error(dataState.uiComponent))
//                }
//
//                is DataState.Data -> {
//                    state.value =
//                        state.value.copy(navigateToMain = !dataState.data.isNullOrEmpty())
//                }
//
//                is DataState.Loading -> {
//                    state.value =
//                        state.value.copy(progressBarState = dataState.progressBarState)
//                }
//            }
//        }.launchIn(viewModelScope)
    }


    private fun onUpdateNameRegister(value: String) {
        state.value = state.value.copy(nameRegister = value)
    }

    private fun onUpdatePasswordLogin(value: String) {
        state.value = state.value.copy(passwordLogin = value)
    }


    private fun onUpdateUsernameLogin(value: String) {
        state.value = state.value.copy(usernameLogin = value)
    }


    private fun appendToMessageQueue(uiComponent: UIComponent) {
        if (uiComponent is UIComponent.None) {
            println("${CUSTOM_TAG}: onTriggerEvent:  ${uiComponent.message}")
            return
        }

        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            println("${CUSTOM_TAG}: removeHeadMessage: Nothing to remove from DialogQueue")
        }
    }


    private fun onRetryNetwork() {

    }


    private fun onUpdateNetworkState(networkState: NetworkState) {
        state.value = state.value.copy(networkState = networkState)
    }


}