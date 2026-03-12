package com.example.ramk.ui.widgets.body

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.ramk.ui.theme.extendedColors

@Composable
fun BoxQuote(
    text: String,
    backgroundColor: Color,
    fontWeight: FontWeight?,
    dropText: String?,
    borderColor: Color
) {
    val extendedParams = MaterialTheme.extendedColors

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 20.dp,
                top = 20.dp
            )
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(size = 30.dp)
            )
            .padding(all = 15.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                style = TextStyle(
                    fontStyle = FontStyle.Italic,
                    fontWeight = fontWeight ?: FontWeight.Normal,
                    color = extendedParams.textInfoColor
                ),
                textAlign = TextAlign.Center,
            )
            if (dropText != null) {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = dropText,
                    fontSize = 20.sp,
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        fontWeight = fontWeight ?: FontWeight.Normal,
                        color = extendedParams.textInfoColor
                    ),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}