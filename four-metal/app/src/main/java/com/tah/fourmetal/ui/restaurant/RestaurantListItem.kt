package com.tah.fourmetal.ui.restaurant

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tah.fourmetal.data.models.Restaurant

@Composable
fun RestaurantListItem(rest: Restaurant) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box() {
            Text(
                text = rest.name,
                color = Color.Blue,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}