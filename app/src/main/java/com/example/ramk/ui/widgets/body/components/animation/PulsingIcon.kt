package com.example.ramk.ui.widgets.body.components.animation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun PulsingIcon(
    iconColor: Color,
    iconDescription: String,
    icon: ImageVector,
    backgroundBoxColor: Color,
    frameColor: Color,
) {

    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000),
            repeatMode = RepeatMode.Reverse,
        )
    )

    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .scale(scale)
            .background(
                color = frameColor,
                shape = RoundedCornerShape(percent = 100)
            )
            .padding(all = 3.dp)
            .background(
                color = backgroundBoxColor,
                shape = RoundedCornerShape(percent = 100)
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            tint = iconColor,
            modifier = Modifier
                .size(size = 75.dp)
                .padding(all = 10.dp)
        )
    }
}