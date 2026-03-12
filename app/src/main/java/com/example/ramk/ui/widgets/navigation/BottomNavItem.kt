package com.example.ramk.ui.widgets.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable


sealed class BottomNavItem(
    val route: Any,
    val title: String,
    val icon: ImageVector,
) {
    data object Home : BottomNavItem(
        route = HomeRoute,
        title = "Персонажи",
        icon = Icons.Filled.Home,
    )

    data object Favourite : BottomNavItem(
        route = FavouriteRoute,
        title = "Фавориты",
        icon = Icons.Default.Favorite
    )
}

@Serializable object HomeRoute
@Serializable object FavouriteRoute