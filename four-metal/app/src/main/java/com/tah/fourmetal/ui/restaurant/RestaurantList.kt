package com.tah.fourmetal.ui.restaurant

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tah.fourmetal.data.models.Restaurant

@Composable
fun RestaurantList(
    restaurantList: SnapshotStateList<Restaurant>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    val length = restaurantList.size
    val context = LocalContext.current
    Column(modifier = Modifier.padding(10.dp)) {
        SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing), onRefresh) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                itemsIndexed(restaurantList) { index, rest ->
                    RestaurantListItem(rest) {
                        val intent = Intent(context, ShowRestaurantActivity::class.java)
                        intent.putExtra("selected_restaurant", it)
                        context.startActivity(intent);
                    }
                    if (index < length - 2) {
                        Divider()

                    }

                }
            }
        }

    }

}