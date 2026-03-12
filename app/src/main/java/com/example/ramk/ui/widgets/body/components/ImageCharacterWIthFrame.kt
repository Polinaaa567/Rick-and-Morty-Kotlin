package com.example.ramk.ui.widgets.body.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

import com.example.ramk.R
import com.example.ramk.ui.theme.extendedColors
import com.example.ramk.ui.widgets.body.components.animation.colorInfinity


@Composable
fun ImageCharacterWIthFrame(image: String?, status: String?) {
    val extendedParams = MaterialTheme.extendedColors

    val shapeImage = RoundedCornerShape(size = 30.dp)

    Box(
        modifier = Modifier
            .padding(start = 10.dp)
            .dropShadow(
                shape = shapeImage,
                shadow = Shadow(
                    radius = 10.dp,
                    color = colorInfinity(
                        initialValue = Color.Transparent,
                        targetValue = extendedParams.headerStripeColor
                    ),
                )
            )
            .padding(all = 3.dp)
            .background(
                color = colorInfinity(
                    initialValue = extendedParams.imageFrameColor,
                    targetValue = extendedParams.imageFrameColor2
                ), shape = shapeImage
            )
            .padding(all = 3.dp)
            .background(
                color = colorInfinity(
                    initialValue = extendedParams.frame2Color,
                    targetValue = extendedParams.nameTextColor
                ), shape = shapeImage
            )
    ) {

        AsyncImage(
            model = image,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            error = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Фото героя",
            modifier = Modifier
                .padding(all = 3.dp)
                .clip(shapeImage)
                .background(Color.Transparent),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(
                    color = extendedParams.statusFrameColor,
                    shape = CircleShape
                )
                .padding(all = 3.dp)
                .size(size = 20.dp)
                .background(
                    color = if (status.equals(other = "Alive")) Color(color = 0xFF4CAF50) else if (status.equals(
                            other = "Dead"
                        )
                    ) Color.Red else Color.Gray,
                    shape = CircleShape
                )
        )
    }
}
