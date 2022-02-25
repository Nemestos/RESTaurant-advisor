package com.tah.fourmetal.ui.restaurant

import androidx.compose.runtime.Composable
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel

@Composable
fun RestaurantsScreen() {
    RestaurantListScreen(rvm = RestaurantViewModel())
}
