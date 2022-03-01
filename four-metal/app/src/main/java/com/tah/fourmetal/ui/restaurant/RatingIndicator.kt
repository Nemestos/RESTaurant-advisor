package com.tah.fourmetal.ui.restaurant


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.tah.fourmetal.R

@Composable
fun RatingIndicator(modifier: Modifier = Modifier, colorTint: Color, rating: String) {

    Icon(
        painter = painterResource(id = R.drawable.ic_star_rate),
        contentDescription = "star",
        tint = colorTint
    )
    Text(text = rating)


}