package com.example.sdcpredict.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Red400 = Color(0xFFCF6679)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val NavyBlue = Color(0xFF090E1D)
val RoyalBlue = Color(0xFF0F67FE)
val DarkBlue = Color(0xFF141B31)
val OffWhite = Color(0xFFF2F5F9)
val Grey = Color(0xFF9EA7B8)

internal val wearColorPalette: Colors = Colors(
    primary = White,
    primaryVariant = Purple700,
    secondary = RoyalBlue,
    background = NavyBlue,
    onBackground = DarkBlue,
    secondaryVariant = Grey,

    error = Red400,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onError = Color.Black
)