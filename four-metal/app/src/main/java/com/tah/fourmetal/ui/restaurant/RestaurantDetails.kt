package com.tah.fourmetal.ui.restaurant

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.tah.fourmetal.data.models.Restaurant
import com.tah.fourmetal.ui.navigation.LocalNavController
import com.tah.fourmetal.ui.navigation.TopBarNavigation
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun ShowRestaurantContent(id: Int) {
    val rvm = getViewModel<RestaurantViewModel>()
    val
    Scaffold(
        topBar = {
            TopBarNavigation(name = restaurant?.name.orEmpty())
        }
    ) {
        Text(text = restaurant?.name.orEmpty())

    }


}