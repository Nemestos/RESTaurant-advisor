package com.tah.fourmetal.ui.restaurant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tah.fourmetal.data.models.Restaurant

@Composable
fun RestaurantList(restaurantList: SnapshotStateList<Restaurant>) {
    val length = restaurantList.size
    Column(modifier = Modifier.padding(10.dp)) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            itemsIndexed(restaurantList) { index, rest ->

                RestaurantListItem(rest)
                if (index < length - 2) {
                    Divider()

                }

            }
        }
    }

}