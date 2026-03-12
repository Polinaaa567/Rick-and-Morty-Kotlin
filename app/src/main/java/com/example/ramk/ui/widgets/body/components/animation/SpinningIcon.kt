package com.example.ramk.ui.widgets.body.components.animation

import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

import com.example.ramk.ui.theme.extendedColors


@Composable
fun SpinningIcon(
    iconColor: Color,
    iconDescription: String,
    icon: ImageVector
) {
    val extendedParams = MaterialTheme.extendedColors

    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 360.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
        )
    )

    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .rotate(degrees = rotation)
            .background(
                color = extendedParams.nameTextColor,
                shape = RoundedCornerShape(percent = 100)
            )
            .padding(all = 3.dp)
            .background(
                color = Color(color = 0xFFB4B4B4),
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
