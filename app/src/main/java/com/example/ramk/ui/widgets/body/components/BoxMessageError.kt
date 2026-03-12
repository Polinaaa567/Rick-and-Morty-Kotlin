package com.example.ramk.ui.widgets.body.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

import com.example.ramk.ui.widgets.body.BoxQuote


@Composable
fun BoxMessageError(
    title: String,
    titleColor: Color,
    titleShadowColor: Color,
    fontSizeTitle: TextUnit,
    fontWeightTitle: FontWeight?,
    modifierTitle: Modifier?,

    message: String,
    backgroundMessageBoxColor: Color,
    borderMessageBoxColor: Color,
    fontWeightMessage: FontWeight?,
    dropText: String?,

    borderColor: Color,
    backgroundColor: Color,
    backgroundColorShadow: Color,
    shadowColor: Color,


    widgetContent: @Composable () -> Unit?,
    animate: @Composable () -> Unit,
) {
    val shape = RoundedCornerShape(size = 30.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .dropShadow(
                shape = shape,
                shadow = Shadow(
                    spread = 6.dp,
                    radius = 10.dp,
                    color = shadowColor,
                )
            )
            .background(
                color = backgroundColorShadow,
                shape = shape
            )
            .padding(bottom = 8.dp)
            .background(color = backgroundColor, shape = shape)
            .drawBehind {
                val strokeWidth = 4.dp.toPx()
                val dashOn = 5.dp.toPx()
                val dashOff = 2.dp.toPx()

                drawRoundRect(
                    cornerRadius = CornerRadius(
                        x = 30.dp.toPx(),
                        y = 30.dp.toPx()
                    ),
                    color = borderColor,
                    style = Stroke(
                        width = strokeWidth,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(dashOn, dashOff),
                            phase = 0.0f
                        )
                    ),
                    size = size
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            animate()

            ShadowText(
                text = title,
                fontSize = fontSizeTitle,
                fontWeight = fontWeightTitle,
                colorText = titleColor,
                colorShadow = titleShadowColor,
                modifier = modifierTitle,
            )

            BoxQuote(
                text = message,
                backgroundColor = backgroundMessageBoxColor,
                fontWeight = fontWeightMessage,
                dropText = dropText,
                borderColor = borderMessageBoxColor
            )

            widgetContent()
        }
    }
}


