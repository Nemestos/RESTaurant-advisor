package com.tah.fourmetal.ui.restaurant

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tah.fourmetal.R
import com.tah.fourmetal.ui.theme.Righteous
import okhttp3.internal.immutableListOf
import java.util.*


@Composable
fun RestaurantListTop(title: String) {
    val availablesLogos = immutableListOf(R.drawable.ic_cat, R.drawable.ic_fox)
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title.uppercase(Locale.getDefault()),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            fontFamily = Righteous,
            color = Color.White
        )
        Icon(
            painterResource(
                id = availablesLogos.random()
            ),
            "top logo",
            Modifier.size(64.dp)
        )

    }
}