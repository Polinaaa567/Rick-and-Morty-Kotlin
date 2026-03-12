package com.example.ramk.presentation

import androidx.compose.runtime.compositionLocalOf


val LocalCharacterViewModel = compositionLocalOf<CharacterViewModel> {
    error("No ViewModel provider")
}