package com.tah.fourmetal.ui.restaurant.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.tah.fourmetal.data.models.Menu
import com.tah.fourmetal.data.models.Restaurant
import com.tah.fourmetal.ui.HeaderTitle
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ShowRestaurantMenu(id: Int) {
    val restaurantViewModel = getViewModel<RestaurantViewModel>()
    val menus = remember { mutableStateListOf<Menu>() }
    LaunchedEffect(key1 = Unit) {
        restaurantViewModel.getMenus(id)?.let {
            menus.clear()
            menus.addAll(it)
        }
    }
    HeaderTitle(text = "Nos Menus")
}