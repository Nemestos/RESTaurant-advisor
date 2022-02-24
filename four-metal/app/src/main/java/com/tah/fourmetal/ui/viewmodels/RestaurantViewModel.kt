package com.tah.fourmetal.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tah.fourmetal.data.api.RestaurantResp
import com.tah.fourmetal.data.api.RestaurantService
import com.tah.fourmetal.data.models.Restaurant
import kotlinx.coroutines.launch
import java.lang.Exception

class RestaurantViewModel : ViewModel() {
    private val _restaurantList = mutableListOf<Restaurant>()
    var errorMsg by mutableStateOf("")
    val restaurantList: List<Restaurant>
        get() = _restaurantList

    fun getRestaurantList() {
        viewModelScope.launch {
            val restService = RestaurantService.getInst()
            try {
                _restaurantList.clear()
                val resp = restService.getRestaurants()
                val items: RestaurantResp? = resp.body()
                if (items != null) {
                    _restaurantList.addAll(items.data)
                }else{
                    errorMsg = "Aucun restaurants"
                }
            } catch (e: Exception) {
                errorMsg = e.message.toString()
            }
        }
    }

}