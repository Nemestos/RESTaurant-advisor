package com.tah.fourmetal.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tah.fourmetal.R

val Righteous = FontFamily(
    Font(R.font.righteous)
)
val Reenie = FontFamily(
    Font(R.font.reenie)
)
val Roboto = FontFamily(
    Font(R.font.roboto_regular, weight = FontWeight.Normal),
    Font(R.font.roboto_bold, weight = FontWeight.Bold),
    Font(R.font.roboto_light, weight = FontWeight.Light),
    Font(R.font.roboto_thin, weight = FontWeight.Thin),
)

val RobotoTypo = Typography(
    body1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    body2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    ),
    h2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Thin,
        fontSize = 64.sp
    ),
    h3 = TextStyle(
        fontFamily = Roboto,
        fontStyle = FontStyle.Italic,
        fontSize = 64.sp
    )
)
