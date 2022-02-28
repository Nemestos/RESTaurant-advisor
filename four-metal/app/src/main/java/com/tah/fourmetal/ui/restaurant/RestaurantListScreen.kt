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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.get


@Composable
fun RestaurantListScreen() {
    val rvm = getViewModel<RestaurantViewModel>()
    val isRefreshing by rvm.isRefreshing.collectAsState()
    LaunchedEffect(Unit, block = {
        rvm.getRestaurantList()
        Log.d("restaurants:", rvm.errorMsg)

    })
    Scaffold(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 50.dp),
        backgroundColor = Color(0xFFE3D3C0)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            RestaurantListTop(title = "four metal")
            if (rvm.errorMsg.isEmpty()) {

                RestaurantList(rvm.restaurantList, isRefreshing) { rvm.refreshRestaurantList() }
            } else {
                RestaurantListError(rvm.errorMsg)

            }
        }

    }
}

