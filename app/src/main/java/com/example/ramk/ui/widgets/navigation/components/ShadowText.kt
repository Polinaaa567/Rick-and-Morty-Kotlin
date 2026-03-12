package com.example.ramk.ui.widgets.navigation.components

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.ramk.ui.theme.extendedColors


@Composable
fun ShadowText(
    text: String,
    offsetX: Int = 0,
    offsetY: Int = 0,
) {
    val extendedParams = MaterialTheme.extendedColors

    Text(
        text = text,
        style = TextStyle(
            color = extendedParams.headerStripeColor,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.offset(x = offsetX.dp, y = offsetY.dp)
    )
}