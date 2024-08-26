package dev.lkeeeey.manager.business.interactors


import business.constants.AUTHORIZATION_BEARER_TOKEN
import business.constants.DataStoreKeys
import dev.lkeeeey.manager.business.core.AppDataStore
import dev.lkeeeey.manager.business.core.DataState
import dev.lkeeeey.manager.business.core.ProgressBarState
import dev.lkeeeey.manager.business.core.UIComponent
import dev.lkeeeey.manager.business.services.SplashService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginInteractor(
    private val service: SplashService,
    private val appDataStoreManager: AppDataStore,
) {


    fun execute(
        email: String,
        password: String,
    ): Flow<DataState<String>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.ButtonLoading))

            val apiResponse = service.login(email, password)

            apiResponse.alert?.let { alert ->
                emit(
                    DataState.Response(
                        uiComponent = UIComponent.Dialog(
                            alert = alert
                        )
                    )
                )
            }

            val result = apiResponse.result

            if (result != null) {
                appDataStoreManager.setValue(
                    DataStoreKeys.TOKEN,
                    AUTHORIZATION_BEARER_TOKEN + result
                )
                appDataStoreManager.setValue(
                    DataStoreKeys.EMAIL,
                    email
                )
            }

            emit(DataState.Data(result, apiResponse.status))

        } catch (e: Exception) {
            e.printStackTrace()
//            emit(handleUseCaseException(e))

        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}
