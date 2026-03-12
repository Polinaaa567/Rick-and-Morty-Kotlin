package com.example.ramk.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color


data class ExtendedColors(
    val cardBackground: Color,
    val nameTextColor: Color,
    val nameTextShadow: Color,
    val cardShadow: Color,
    val cardGlare: Color,
    val iconModeColor: Color,
    val infoBackground: Color,
    val frameColor: Color,
    val headerBackground: Color,
    val headerStripeColor: Color,
    val frame2Color: Color,
    val textInfoColor: Color,
    val cardShadow2: Color,
    val imageFrameColor: Color,
    val imageFrameColor2: Color,
    val statusFrameColor: Color,
    val menuBackgroundColor: Color,
)

val LocalExtendedColors = compositionLocalOf {
    ExtendedColors(
        cardBackground = Color.Unspecified,
        nameTextColor = Color.Unspecified,
        nameTextShadow = Color.Unspecified,
        cardShadow = Color.Unspecified,
        cardGlare = Color.Unspecified,
        iconModeColor = Color.Unspecified,
        infoBackground = Color.Unspecified,
        frameColor = Color.Unspecified,
        frame2Color = Color.Unspecified,
        headerBackground = Color.Unspecified,
        headerStripeColor = Color.Unspecified,
        textInfoColor = Color.Unspecified,
        cardShadow2 = Color.Unspecified,
        imageFrameColor = Color.Unspecified,
        imageFrameColor2 = Color.Unspecified,
        statusFrameColor = Color.Unspecified,
        menuBackgroundColor = Color.Unspecified
    )
}

fun lightExtendedColors() = ExtendedColors(
    cardBackground = Vanilla,
    nameTextColor = ForestGreen,
    nameTextShadow = LindenBlossom,
    cardShadow = SageGreen,
    cardGlare = CreamyGreen,
    iconModeColor = GrassGreen,
    infoBackground = SoftGreen,
    frameColor = PearGreen,
    frame2Color = VividGreen,
    headerBackground = PaleGreen,
    headerStripeColor = GrassGreen,
    textInfoColor = Coniferous,
    cardShadow2 = HarvestGold,
    imageFrameColor = Pink,
    imageFrameColor2 = Indigo,
    statusFrameColor = White,
    menuBackgroundColor = LightGreen
)

fun darkExtendedColors() = ExtendedColors(
    cardBackground = AnthraciteBlue,
    cardShadow = DeepIndigo,
    nameTextColor = LimeGreen,
    nameTextShadow = Petrol,
    cardGlare = BrightCyan,
    iconModeColor = NeonTurquoise,
    infoBackground = DarkCyan,
    frameColor = BrightCyan,
    frame2Color = ElectricTeal,
    headerBackground = DeepTeal,
    headerStripeColor = NeonTurquoise,
    textInfoColor = SkyBlue,
    cardShadow2 = PurpleDark,
    imageFrameColor = Pink,
    imageFrameColor2 = Indigo,
    statusFrameColor = Black,
    menuBackgroundColor = DarkBlue
)

private val DarkColorScheme = darkColorScheme(
    background = BlackWithBlueUndertone
)

private val LightColorScheme = lightColorScheme(
    background = CreamyLime
)

val MaterialTheme.extendedColors: ExtendedColors
    @Composable get() = LocalExtendedColors.current

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = when {

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val extendedColors = if (darkTheme) darkExtendedColors() else lightExtendedColors()

    CompositionLocalProvider(value = LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme, typography = Typography, content = content
        )
    }
}