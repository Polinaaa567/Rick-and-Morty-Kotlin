package com.example.ramk.ui.widgets.body.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.ramk.ui.theme.extendedColors


@Composable
fun InformationAboutCharacter(
    icon: ImageVector,
    iconColor: Color?,
    info: String,
    fontWeight: FontWeight
) {
    val extendedParams = MaterialTheme.extendedColors
    val shape = RoundedCornerShape(size = 30.dp)

    Box(
        modifier = Modifier
            .border(
                color = extendedParams.headerStripeColor,
                width = 1.dp,
                shape = shape
            )
            .background(
                color = extendedParams.infoBackground,
                shape = shape
            )
            .shadow(
                elevation = 8.dp,
                shape = shape,
                clip = false,
                ambientColor = extendedParams.cardShadow2,
                spotColor = extendedParams.cardShadow2
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 3.dp, horizontal = 10.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Информация о персонаже",
                tint = iconColor ?: extendedParams.textInfoColor,
                modifier = Modifier
                    .size(size = 25.dp)
                    .padding(end = 5.dp)
            )
            Text(
                text = info,
                fontSize = 15.sp,
                style = TextStyle(
                    fontWeight = fontWeight,
                    color = extendedParams.textInfoColor,
                ),
                textAlign = TextAlign.Center,
            )
        }
    }
}