package com.example.ramk.ui.widgets.body.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp


@Composable
fun ShadowText(
    text: String,
    fontSize: TextUnit,
    colorText: Color,
    colorShadow: Color,
    modifier: Modifier?,
    fontWeight: FontWeight?,
) {
    Box(
        modifier = modifier ?: Modifier,
    ) {
        Text(
            text = text,
            modifier = Modifier.offset(x = 2.dp, y = 2.dp),
            style = TextStyle(
                color = colorShadow,
                fontSize = fontSize,
                fontWeight = fontWeight ?: FontWeight.Normal
            ),
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = text,
            style = TextStyle(
                color = colorText,
                fontSize = fontSize,
                fontWeight = fontWeight ?: FontWeight.Normal
            ), textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}