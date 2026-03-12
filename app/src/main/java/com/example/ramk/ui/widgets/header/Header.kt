package com.example.ramk.ui.widgets.header

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.ramk.R
import com.example.ramk.presentation.LocalCharacterViewModel
import com.example.ramk.ui.theme.extendedColors


@Composable
fun Header() {
    val extendedParams = MaterialTheme.extendedColors
    val viewModel = LocalCharacterViewModel.current

    val uiState by viewModel.uiState.collectAsState()
    val isNightMode = uiState.isNightMode

    val gradientColors = listOf(
        Color(color = 0xffc3ff68),
        Color(color = 0xff6effe8),
        Color(color = 0xfffa96ff),
    )

    val jorick = FontFamily(Font(resId = R.font.jorick_regular, weight = FontWeight.Normal))

    Column(
        modifier = Modifier.background(
            color = extendedParams.headerBackground,
        )
    ) {
        Spacer(modifier = Modifier.height(height = 20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxSize(),
        ) {
            Box {
                Text(
                    text = "R&M",
                    fontFamily = jorick,
                    style = TextStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors
                        ),
                        fontSize = 50.sp,
                        fontStyle = FontStyle.Italic
                    ),
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier.offset(x = 3.dp, y = 3.dp),
                    text = "R&M",
                    fontFamily = jorick,
                    style = TextStyle(
                        color = Color(color = 0xff1F3A4B),
                        fontSize = 50.sp,
                        fontStyle = FontStyle.Italic
                    ),
                    textAlign = TextAlign.Center,
                )
            }

            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .dropShadow(
                        shape = RoundedCornerShape(size = 20.dp),
                        shadow = Shadow(
                            radius = 10.dp,
                            spread = 4.dp,
                            color = extendedParams.frame2Color.copy(alpha = 0.5f),
                        )
                    )
                    .background(
                        color = extendedParams.headerBackground,
                        shape = RoundedCornerShape(size = 20.dp),
                    )
                    .size(size = 40.dp)
                    .border(
                        width = 2.dp,
                        color = extendedParams.iconModeColor,
                        shape = RoundedCornerShape(size = 20.dp)
                    )

            ) {
                IconButton(
                    onClick = { viewModel.changeMode() },
                    shape = IconButtonDefaults.filledShape,
                ) {
                    Icon(
                        imageVector = if (isNightMode) Icons.Filled.ModeNight else Icons.Filled.WbSunny,
                        contentDescription = "Смена режима",
                        tint = extendedParams.iconModeColor,
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .height(height = 5.dp)
                .fillMaxWidth()
                .background(color = extendedParams.headerStripeColor)
                .dropShadow(
                    shape = RoundedCornerShape(size = 100.dp),
                    shadow = Shadow(
                        radius = 10.dp,
                        spread = 6.dp,
                        color = extendedParams.headerStripeColor.copy(alpha = 0.5f),
                        offset = DpOffset(x = 0.dp, y = 4.dp)
                    )
                )
        )
    }
}
