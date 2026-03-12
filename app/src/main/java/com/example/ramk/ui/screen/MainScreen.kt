package com.example.ramk.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.ramk.ui.widgets.navigation.BottomNavigationBar
import com.example.ramk.ui.widgets.navigation.FavouriteRoute
import com.example.ramk.ui.widgets.navigation.HomeRoute

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier.padding(paddingValues = innerPadding)
        ) {
            composable<HomeRoute> {
                AllCharacterRickAndMortyScreen()
            }
            composable<FavouriteRoute> {
                FavouriteListScreen()
            }
        }
    }
}