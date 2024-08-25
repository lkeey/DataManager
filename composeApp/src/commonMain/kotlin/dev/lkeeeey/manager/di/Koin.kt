package dev.lkeeeey.manager.di

import dev.lkeeeey.manager.business.interactors.CheckTokenInteractor
import dev.lkeeeey.manager.business.interactors.LogoutInteractor
import dev.lkeeeey.manager.common.Context
import dev.lkeeeey.manager.presentation.SharedViewModel
import org.koin.dsl.module
import presentation.token_manager.TokenManager

fun appModule(context: Context) = module {
    //  Interactors
    single { CheckTokenInteractor(get()) }
    single { LogoutInteractor(get()) }

    //    Managers
    single { TokenManager(get(), get()) }

    //    View Models
    factory { SharedViewModel(get()) }

}
