package com.example.ramk.ui.widgets.body

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.ramk.domain.model.ResultDomain
import com.example.ramk.presentation.LocalCharacterViewModel
import com.example.ramk.ui.theme.extendedColors
import com.example.ramk.ui.widgets.body.components.ImageCharacterWIthFrame
import com.example.ramk.ui.widgets.body.components.InformationAboutCharacter
import com.example.ramk.ui.widgets.body.components.ShadowText
import com.example.ramk.ui.widgets.body.components.animation.colorInfinity


@Composable
fun CharacterCard(character: ResultDomain) {
    val viewModel = LocalCharacterViewModel.current

    val extendedParams = MaterialTheme.extendedColors

    val shape = RoundedCornerShape(
        topStart = 30.dp,
        topEnd = 10.dp,
        bottomStart = 10.dp,
        bottomEnd = 30.dp
    )

    val status = character.status

    Box(
        modifier = Modifier
            .padding(
                bottom = 20.dp,
                start = 10.dp,
                end = 10.dp
            )
            .dropShadow(
                shape = shape,
                shadow = Shadow(
                    spread = 6.dp,
                    radius = 10.dp,
                    color = extendedParams.cardShadow2,
                )
            )
            .background(
                color = extendedParams.cardShadow,
                shape = shape,
            )
    ) {
        Card(
            shape = shape,
            colors = CardColors(
                containerColor = extendedParams.cardBackground,
                contentColor = extendedParams.cardBackground,
                disabledContainerColor = extendedParams.cardShadow,
                disabledContentColor = extendedParams.cardShadow
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
                .border(
                    width = 2.dp,
                    color = colorInfinity(
                        initialValue = Color.Yellow,
                        targetValue = extendedParams.imageFrameColor
                    ),
                    shape = shape,
                )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ImageCharacterWIthFrame(
                    image = character.image,
                    status = status
                )

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(all = 10.dp),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        ShadowText(
                            text = "${character.name}",
                            fontSize = 25.sp,
                            colorText = extendedParams.nameTextColor,
                            colorShadow = extendedParams.nameTextShadow,
                            modifier = Modifier.weight(weight = 1f),
                            fontWeight = FontWeight.Bold
                        )

                        Icon(
                            modifier = Modifier
                                .size(size = 25.dp)
                                .pointerInput(key1 = Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            viewModel.addFavourite(character)
                                            println(character.gender)
                                        }
                                    )
                                },
                            imageVector = if (character.isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Добавить в фавориты",
                            tint = Color.Red,
                        )

                    }

                    Spacer(Modifier.height(height = 10.dp))

                    character.species?.length?.let {
                        if (it <= 7) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                InformationAboutCharacter(
                                    icon = Icons.Filled.Biotech,
                                    iconColor = null,
                                    info = character.species,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(width = 10.dp))
                                InformationAboutCharacter(
                                    icon = if (character.gender.equals(other = "Male")) {
                                        Icons.Filled.Male
                                    } else if (character.gender.equals(other = "Female")) {
                                        Icons.Filled.Female
                                    } else {
                                        Icons.Filled.Transgender
                                    },
                                    iconColor = null,
                                    info = "${character.gender}",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            InformationAboutCharacter(
                                icon = Icons.Filled.Biotech,
                                iconColor = null,
                                info = character.species,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(height = 10.dp))

                            InformationAboutCharacter(
                                icon = if (character.gender.equals(other = "Male")) Icons.Filled.Male else if (character.gender.equals(
                                        other = "Female"
                                    )
                                ) Icons.Filled.Female else Icons.Filled.Transgender,
                                iconColor = null,
                                info = "${character.gender}",
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(Modifier.height(height = 10.dp))
                    }
                    InformationAboutCharacter(
                        icon = Icons.Filled.LocationOn,
                        iconColor = Color.Yellow,
                        info = "${character.location?.name}",
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(Modifier.height(height = 10.dp))
                    Text(
                        maxLines = 1,
                        text = "${character.origin?.name}",
                        color = extendedParams.textInfoColor,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}


