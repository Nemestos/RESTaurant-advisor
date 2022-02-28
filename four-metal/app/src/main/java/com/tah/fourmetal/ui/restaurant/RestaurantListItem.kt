package com.tah.fourmetal.ui.restaurant

import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
        Box(
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column() {

                }
                Card(
                    modifier = Modifier.fillMaxHeight(),
                    shape = RoundedCornerShape(2.dp),
                    elevation = 2.dp
                ) {
                    Image(
                        painter = painterResource(id = com.tah.fourmetal.R.mipmap.ic_burger_foreground),
                        contentDescription = "burger fond"
                    )
                }
            }
        }
    }
}