package com.example.ramk.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

import com.example.ramk.presentation.LocalCharacterViewModel
import com.example.ramk.ui.theme.extendedColors
import com.example.ramk.ui.widgets.body.BoxQuote
import com.example.ramk.ui.widgets.body.CharacterCard
import com.example.ramk.ui.widgets.body.components.BoxMessageError
import com.example.ramk.ui.widgets.body.components.animation.PulsingIcon
import com.example.ramk.ui.widgets.header.Header


@Composable
fun AllCharacterRickAndMortyScreen() {
    val params = MaterialTheme.colorScheme
    val extendedParams = MaterialTheme.extendedColors

    val viewModel = LocalCharacterViewModel.current
    val uiState by viewModel.uiState.collectAsState()

    val listState = rememberLazyListState()

    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }.map { visibleItems ->
            val lastVisibleItem = visibleItems.lastOrNull()
            val totalItems = listState.layoutInfo.totalItemsCount

            lastVisibleItem != null && lastVisibleItem.index >= totalItems - 2
        }.distinctUntilChanged().collect { shouldLoadMore ->
            if (shouldLoadMore && uiState.isLoadingMore) {
                viewModel.loadNextPage()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = extendedParams.frameColor)
            .padding(top = 3.dp, start = 3.dp, end = 3.dp)
            .background(color = extendedParams.frame2Color)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = params.background)
        ) {
            stickyHeader {
                Header()
            }

            if (uiState.result != null) {
                item {
                    BoxQuote(
                        text = "Wubba lubba dub dub!",
                        backgroundColor = extendedParams.infoBackground,
                        fontWeight = FontWeight.Bold,
                        dropText = null,
                        borderColor = extendedParams.headerStripeColor
                    )
                }
            } else if (!uiState.isLoading) {
                item {
                    Box(modifier = Modifier.padding(top = 20.dp)) {
                        BoxMessageError(
                            title = "ПОРТАЛ ЗАБЛОКИРОВАН!",
                            titleColor = Color(color = 0xffdb4437),
                            titleShadowColor = Color(color = 0xff320000),
                            fontSizeTitle = 25.sp,
                            fontWeightTitle = FontWeight.Bold,
                            modifierTitle = Modifier.padding(
                                start = 10.dp, end = 10.dp, top = 20.dp
                            ),
                            message = "Ну и бардак! Данные не грузятся. Рик, наверное, перепил швепса и сломал реальность. Попробуйте позже.",
                            backgroundMessageBoxColor = extendedParams.infoBackground,
                            borderMessageBoxColor = Color(color = 0xffdb4437),
                            fontWeightMessage = null,
                            dropText = "- Жить - значит рисковать всем.",
                            borderColor = Color(color = 0xffdb4437),
                            backgroundColor = extendedParams.cardBackground,
                            backgroundColorShadow = extendedParams.cardShadow,
                            shadowColor = Color(color = 0xffdb4437).copy(alpha = 0.7f),
                            widgetContent = {
                                Button(
                                    modifier = Modifier.padding(
                                        bottom = 20.dp
                                    ),
                                    onClick = { viewModel.refresh() },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = extendedParams.headerStripeColor,
                                        contentColor = extendedParams.cardBackground
                                    )
                                ) {
                                    Row {
                                        Icon(
                                            imageVector = Icons.Filled.Refresh,
                                            contentDescription = "Починить",
                                            modifier = Modifier
                                                .size(size = 30.dp)
                                                .padding(end = 5.dp)
                                        )

                                        Text(
                                            text = "Починить портал", style = TextStyle(
                                                fontSize = 25.sp, fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                }
                            },
                            animate = {
                                PulsingIcon(
                                    iconColor = Color(color = 0xff000000),
                                    iconDescription = "Основная информация заблокирована",
                                    icon = Icons.Filled.Warning,
                                    backgroundBoxColor = Color(color = 0xFFFBFFE6),
                                    frameColor = Color(color = 0xffdb4437)
                                )
                            }
                        )
                    }
                }
            }

            val characters = uiState.result?.results ?: emptyList()
            items(items = characters) { character ->
                CharacterCard(character = character)
            }

            if (uiState.isLoading && !characters.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = 16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(
                            color = extendedParams.imageFrameColor,
                            trackColor = extendedParams.iconModeColor,
                            strokeWidth = 5.dp
                        )
                    }
                }
            }

            if (uiState.isLoading && characters.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = 16.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(
                            color = extendedParams.imageFrameColor,
                            trackColor = extendedParams.iconModeColor,
                            strokeWidth = 5.dp
                        )
                    }
                }
            }
        }
    }
}
