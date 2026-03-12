package com.example.ramk.ui.widgets.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

import com.example.ramk.presentation.LocalCharacterViewModel
import com.example.ramk.ui.theme.extendedColors
import com.example.ramk.ui.widgets.navigation.components.ShadowIcon
import com.example.ramk.ui.widgets.navigation.components.ShadowText


@Composable
fun BottomNavigationBar(
    navController: NavController,
) {
    val viewModel = LocalCharacterViewModel.current
    val uiState by viewModel.uiState.collectAsState()

    val items = listOf(BottomNavItem.Home, BottomNavItem.Favourite)

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val extendedParams = MaterialTheme.extendedColors

    Box(
        modifier = Modifier
            .background(color = extendedParams.frameColor)
            .padding(bottom = 3.dp, start = 3.dp, end = 3.dp)
            .background(color = extendedParams.frame2Color)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 5.dp)
                .background(color = extendedParams.headerStripeColor)
                .dropShadow(
                    shape = RoundedCornerShape(size = 100.dp),
                    shadow = Shadow(
                        radius = 10.dp,
                        spread = 6.dp,
                        color = extendedParams.headerStripeColor.copy(alpha = 0.5f),
                        offset = DpOffset(x = 0.dp, y = (-4).dp)
                    )
                )
        )
        NavigationBar(
            containerColor = extendedParams.menuBackgroundColor,
            modifier = Modifier
                .padding(top = 5.dp)
                .background(color = extendedParams.menuBackgroundColor)
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = extendedParams.nameTextColor,
                        selectedTextColor = Color.White,
                        unselectedTextColor = extendedParams.nameTextColor,
                        indicatorColor = Color.Transparent
                    ),
                    icon = {
                        if (currentRoute == item.route::class.qualifiedName) {
                            Box() {
                                ShadowIcon(icon = item.icon, offsetX = -1, offsetY = -1)
                                ShadowIcon(icon = item.icon, offsetX = 1, offsetY = -1)
                                ShadowIcon(icon = item.icon, offsetX = -1, offsetY = 1)
                                ShadowIcon(icon = item.icon, offsetX = 1, offsetY = 1)
                                ShadowIcon(icon = item.icon, offsetX = 0, offsetY = 1)
                                ShadowIcon(icon = item.icon, offsetX = 1, offsetY = 0)
                                ShadowIcon(icon = item.icon, offsetX = -1, offsetY = 0)
                                ShadowIcon(icon = item.icon, offsetX = 0, offsetY = -1)

                                Icon(
                                    modifier = Modifier
                                        .size(size = 30.dp)
                                        .dropShadow(
                                            shape = CircleShape,
                                            shadow = Shadow(
                                                radius = 10.dp,
                                                spread = 6.dp,
                                                color = extendedParams.headerStripeColor.copy(
                                                    alpha = 0.5f
                                                ),
                                            )
                                        ),
                                    imageVector = item.icon,
                                    contentDescription = item.title,
                                )
                            }
                        } else {
                            Icon(
                                modifier = Modifier.size(size = 30.dp),
                                imageVector = item.icon,
                                contentDescription = item.title,
                            )
                        }
                    },
                    label = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (currentRoute == item.route::class.qualifiedName) {
                                Box {
                                    ShadowText(text = item.title, offsetX = -1, offsetY = -1)
                                    ShadowText(text = item.title, offsetX = 1, offsetY = -1)
                                    ShadowText(text = item.title, offsetX = -1, offsetY = 1)
                                    ShadowText(text = item.title, offsetX = 1, offsetY = 1)
                                    Text(
                                        text = item.title,
                                        style = TextStyle(
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                }
                            } else {
                                Text(
                                    text = item.title,
                                    style = TextStyle(
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                )
                            }

                            if (item.title == "Фавориты")
                                Box(
                                    modifier = Modifier
                                        .padding(start = 5.dp)
                                        .background(
                                            color = Color(color = 0xffdb4437),
                                            shape = RoundedCornerShape(percent = 100)
                                        )
                                ) {

                                    Text(
                                        modifier = Modifier.padding(
                                            vertical = 5.dp,
                                            horizontal = 10.dp
                                        ),
                                        text = "${uiState.favouriteRickAndMortyList?.size}",
                                        color = Color.White,
                                        style = TextStyle(
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                        }
                    },
                    selected = currentRoute == item.route::class.qualifiedName,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}
