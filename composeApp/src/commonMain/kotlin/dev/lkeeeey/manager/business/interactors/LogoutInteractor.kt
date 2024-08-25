package dev.lkeeeey.manager.business.interactors


import business.constants.DataStoreKeys
import dev.lkeeeey.manager.business.core.AppDataStore
import dev.lkeeeey.manager.business.core.DataState
import dev.lkeeeey.manager.business.core.ProgressBarState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LogoutInteractor(
    private val appDataStoreManager: AppDataStore,
) {


    fun execute(): Flow<DataState<Boolean>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.ButtonLoading))

            appDataStoreManager.setValue(
                DataStoreKeys.TOKEN,
                ""
            )

            emit(DataState.Data(true))

        } catch (e: Exception) {
            e.printStackTrace()
//            emit(handleUseCaseException(e))

        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }


    }

}
