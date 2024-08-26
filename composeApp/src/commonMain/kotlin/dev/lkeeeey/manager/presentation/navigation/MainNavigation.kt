package dev.lkeeeey.manager.presentation.navigation

import datamanager.composeapp.generated.resources.Res
import datamanager.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.DrawableResource

sealed class MainNavigation (
    val route: String,
    val title: String,
    val selectedIcon: DrawableResource,
    val unSelectedIcon: DrawableResource,
) {

   data object Home : MainNavigation(
        route = "Home", title = "Home",
        selectedIcon = Res.drawable.compose_multiplatform,
        unSelectedIcon = Res.drawable.compose_multiplatform
    )

   data object Wishlist : MainNavigation(
        route = "Wishlist", title = "Wishlist",
        selectedIcon = Res.drawable.compose_multiplatform,
        unSelectedIcon = Res.drawable.compose_multiplatform
    )

   data object Cart : MainNavigation(
        route = "Cart", title = "Cart",
        selectedIcon = Res.drawable.compose_multiplatform,
        unSelectedIcon = Res.drawable.compose_multiplatform
    )

   data object Profile : MainNavigation(
        route = "Profile", title = "Profile",
        selectedIcon = Res.drawable.compose_multiplatform,
        unSelectedIcon = Res.drawable.compose_multiplatform
    )

}

