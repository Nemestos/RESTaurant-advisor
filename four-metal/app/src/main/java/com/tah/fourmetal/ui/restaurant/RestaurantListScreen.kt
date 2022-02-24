package com.tah.fourmetal.ui.restaurant

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel


@Composable
fun RestaurantListScreen(rvm: RestaurantViewModel) {
    LaunchedEffect(Unit, block = {
        rvm.getRestaurantList()
        Log.d("restaurant:", rvm.restaurantList.toString())
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

                RestaurantList(rvm.restaurantList)
            } else {
                Text(text = rvm.errorMsg)

            }
        }
    )
}

