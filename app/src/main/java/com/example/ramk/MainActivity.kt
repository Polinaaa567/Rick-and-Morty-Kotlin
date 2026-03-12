package com.example.ramk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.ramk.presentation.CharacterViewModel
import com.example.ramk.presentation.LocalCharacterViewModel
import com.example.ramk.ui.screen.AllCharacterRickAndMortyScreen
import com.example.ramk.ui.screen.MainScreen
import com.example.ramk.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: CharacterViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsState()

            CompositionLocalProvider(value = LocalCharacterViewModel provides viewModel) {
                RickAndMortyTheme(darkTheme = uiState.isNightMode) {
                    println("MainActivityViewModel = ${viewModel.hashCode()}")
                    MainScreen()
                }
            }

        }
    }
}
