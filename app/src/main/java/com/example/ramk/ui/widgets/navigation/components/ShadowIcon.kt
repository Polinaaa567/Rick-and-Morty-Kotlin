package com.example.ramk.ui.widgets.navigation.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.ramk.ui.theme.extendedColors


@Composable
fun ShadowIcon(
    icon: ImageVector,
    offsetX: Int = 0,
    offsetY: Int = 0,
) {
    val extendedParams = MaterialTheme.extendedColors

    Icon(
        modifier = Modifier
            .size(size = 30.dp)
            .offset(x = offsetX.dp, y = offsetY.dp),
        tint = extendedParams.headerStripeColor,
        imageVector = icon,
        contentDescription = "Оконтовка Icon",
    )
}