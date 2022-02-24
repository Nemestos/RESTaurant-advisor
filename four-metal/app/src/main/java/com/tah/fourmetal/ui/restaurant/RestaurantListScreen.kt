package com.tah.fourmetal.ui.restaurant

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel


@Composable
fun RestaurantListScreen(rvm: RestaurantViewModel) {
    val isRefreshing by rvm.isRefreshing.collectAsState()
    LaunchedEffect(Unit, block = {
        rvm.getRestaurantList()
    })
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text("Restaurants")
                    }
                })
        },
        content = {
            if (rvm.errorMsg.isEmpty()) {

                RestaurantList(rvm.restaurantList, isRefreshing, { rvm.refreshRestaurantList() })


            } else {
                Text(text = rvm.errorMsg)

            }
        }
    )
}

