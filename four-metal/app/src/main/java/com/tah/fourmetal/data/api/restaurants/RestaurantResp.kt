package com.tah.fourmetal.data.api.restaurants

import com.tah.fourmetal.data.models.Menu
import com.tah.fourmetal.data.models.Restaurant

data class RestaurantResp(
    var data: List<Restaurant>
)

data class SingleRestaurantResp(
    var data: Restaurant
)

data class RestaurantMenuResp(
    var data: List<Menu>
)

data class RestaurantErrorResp(
    var message: String?
)