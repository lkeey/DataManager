package dev.lkeeeey.manager.di

import dev.lkeeeey.manager.business.core.AppDataStore
import dev.lkeeeey.manager.business.core.AppDataStoreManager
import dev.lkeeeey.manager.business.core.http.KtorHttpClient
import dev.lkeeeey.manager.business.interactors.CheckTokenInteractor
import dev.lkeeeey.manager.business.interactors.LoginInteractor
import dev.lkeeeey.manager.business.interactors.LogoutInteractor
import dev.lkeeeey.manager.business.services.SplashService
import dev.lkeeeey.manager.business.services.SplashServiceImpl
import dev.lkeeeey.manager.common.Context
import dev.lkeeeey.manager.presentation.SharedViewModel
import org.koin.dsl.module
import dev.lkeeeey.manager.presentation.token_manager.TokenManager
import kotlinx.serialization.json.Json
import presentation.ui.splash.view_model.LoginViewModel

fun appModule(context: Context) = module {
    //  HTTP
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single {
        KtorHttpClient.httpClient(get())
    }

    //  Services
    single<SplashService> { SplashServiceImpl(get()) }


    //  Interactors
    single { CheckTokenInteractor(get()) }
    single { LogoutInteractor(get()) }
    single { LoginInteractor(get(), get()) }


    //    Managers
    single<AppDataStore> { AppDataStoreManager(context) }
    single { TokenManager(get(), get()) }


    //    View Models
    factory { SharedViewModel(get()) }
    factory { LoginViewModel(get(), get()) }
}
