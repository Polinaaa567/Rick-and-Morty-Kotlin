package com.example.ramk.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ramk.domain.model.ResultDomain

import com.example.ramk.presentation.LocalCharacterViewModel
import com.example.ramk.ui.theme.extendedColors
import com.example.ramk.ui.widgets.body.CharacterCard
import com.example.ramk.ui.widgets.body.components.BoxMessageError
import com.example.ramk.ui.widgets.body.components.animation.SpinningIcon
import com.example.ramk.ui.widgets.header.Header


@Composable
fun FavouriteListScreen() {
    val params = MaterialTheme.colorScheme
    val extendedParams = MaterialTheme.extendedColors

    val viewModel = LocalCharacterViewModel.current
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = extendedParams.frameColor)
            .padding(top = 3.dp, start = 3.dp, end = 3.dp)
            .background(color = extendedParams.frame2Color)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = params.background)
        ) {
            stickyHeader {
                Header()
            }
            item {
                Spacer(modifier = Modifier.height(height = 20.dp))
            }

            val favouriteList = uiState.favouriteRickAndMortyList ?: emptyList()
            if (favouriteList.isEmpty()) {
                item {
                    BoxMessageError(
                        title = "ПУСТОЕ ИЗМЕРЕНИЕ",
                        titleColor = extendedParams.nameTextColor,
                        titleShadowColor = extendedParams.nameTextShadow,
                        fontSizeTitle = 25.sp,
                        fontWeightTitle = FontWeight.Bold,
                        modifierTitle = Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp),
                        message = "Здесь пока никого нет. Нажми на сердечко у персонажа, чтобы добавить его в любимчики.",
                        backgroundMessageBoxColor = extendedParams.infoBackground,
                        borderMessageBoxColor = extendedParams.headerStripeColor,
                        fontWeightMessage = null,
                        dropText = "- Я Мистер Мисикс, посмотрите на меня!",
                        borderColor = extendedParams.frameColor,
                        backgroundColorShadow = extendedParams.cardShadow,
                        shadowColor = extendedParams.cardShadow2,
                        backgroundColor = extendedParams.cardBackground,
                        widgetContent = { null },
                        animate = {
                            SpinningIcon(
                                iconColor = extendedParams.nameTextColor,
                                iconDescription = "ПУСТОЕ ИЗМЕРЕНИЕ ФАВОРИТОВ",
                                icon = Icons.Filled.HeartBroken,
                            )
                        }
                    )
                }
            } else {
                items(
                    items = favouriteList,
                    key = { favourite: ResultDomain -> favourite.id!! }
                ) { favourite ->
                    CharacterCard(character = favourite)
                }
            }
        }
    }
}
