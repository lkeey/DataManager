package dev.lkeeeey.manager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.lkeeeey.manager.presentation.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    AppTheme {
        val navigator = rememberNavController()
        val viewModel: SharedViewModel = koinInject()

        LaunchedEffect(key1 = viewModel.tokenManager.state.value.isTokenAvailable) {
            if (!viewModel.tokenManager.state.value.isTokenAvailable) {
                navigator.popBackStack()
                navigator.navigate(AppNavigation.Splash.route)
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navigator,
                startDestination = AppNavigation.Splash.route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = AppNavigation.Splash.route) {
                    SplashNav(navigateToMain = {
                        navigator.popBackStack()
                        navigator.navigate(AppNavigation.Main.route)
                    })
                }
                composable(route = AppNavigation.Main.route) {
                    MainNav {
                        navigator.popBackStack()
                        navigator.navigate(AppNavigation.Splash.route)
                    }
                }
            }
        }

    }

//    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
//    }
}