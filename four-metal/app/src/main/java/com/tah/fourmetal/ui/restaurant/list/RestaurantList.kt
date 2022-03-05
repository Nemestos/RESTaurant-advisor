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
import androidx.compose.runtime.snapshots.SnapshotMutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tah.fourmetal.data.models.Restaurant
import com.tah.fourmetal.ui.navigation.LocalNavController
import com.tah.fourmetal.ui.navigation.NavItem

@Composable
fun RestaurantList(
    restaurantList: SnapshotStateList<Restaurant>,
    isRefreshing: Boolean,
    filter: String,
    onRefresh: () -> Unit
) {
    val length = restaurantList.size
    val navController = LocalNavController.current
    val filtered = restaurantList.filter {
        it.name.orEmpty().lowercase().contains(filter.lowercase())
    }
    Column(modifier = Modifier.padding(2.dp)) {
        SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing), onRefresh) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                itemsIndexed(
                    filtered
                ) { index, rest ->
                    RestaurantListItem(rest) {

                        navController.navigate("${NavItem.RestaurantDetail.route_base}/${rest.id}")
                    }
                    if (index < length - 2) {
                        Divider()

                    }

                }
            }
        }

    }

}