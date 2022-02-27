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

    ) {
        if (rvm.errorMsg.isEmpty()) {

            RestaurantList(rvm.restaurantList, isRefreshing) { rvm.refreshRestaurantList() }


        } else {
            RestaurantListError(rvm.errorMsg)

        }
    }
}

