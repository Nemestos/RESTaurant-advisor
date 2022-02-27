package com.tah.fourmetal.ui.restaurant

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tah.fourmetal.data.models.Restaurant

@Composable
fun RestaurantListItem(rest: Restaurant, onClick: (Restaurant) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(rest) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box() {
            rest.name?.let {
                Text(
                    text = it,
                    color = Color.Blue,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}