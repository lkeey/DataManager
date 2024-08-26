package dev.lkeeeey.manager.feature_auth.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.lkeeeey.manager.feature_auth.login.presetation.LoginScreen
import dev.lkeeeey.manager.presentation.navigation.SplashNavigation
import org.koin.compose.koinInject
import presentation.ui.splash.view_model.LoginViewModel

@Composable
internal fun SplashNav(
    viewModel: LoginViewModel = koinInject(),
    navigateToMain: () -> Unit
) {

    val navigator = rememberNavController()

//    ChangeStatusBarColors(Color.Black)
    NavHost(
        startDestination = SplashNavigation.Splash.route,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = SplashNavigation.Splash.route) {
            SplashScreen(
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent,
                navigateToMain = navigateToMain,
                navigateToLogin = {
                    navigator.popBackStack()
                    navigator.navigate(SplashNavigation.Login.route)
                })
        }
        composable(route = SplashNavigation.Login.route) {
            LoginScreen(
                navigateToMain = navigateToMain,
                navigateToRegister = {
                    navigator.navigate(SplashNavigation.Register.route)
                },
                state = viewModel.state.value,
                events = viewModel::onTriggerEvent
            )
        }
        composable(route = SplashNavigation.Register.route) {
//            RegisterScreen(
//                navigateToMain = navigateToMain, popUp = {
//                    navigator.popBackStack()
//                }, state = viewModel.state.value,
//                events = viewModel::onTriggerEvent
//            )
        }
    }

}