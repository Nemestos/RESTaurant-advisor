package com.tah.fourmetal.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
    var errorMsg by mutableStateOf("")
    val restaurantList = mutableStateListOf<Restaurant>()

    fun getRestaurantList() {
        viewModelScope.launch {
            val restService = RestaurantService.getInst()
            try {
                restaurantList.clear()
                val resp = restService.getRestaurants()
                val items: RestaurantResp? = resp.body()
                if (items != null) {
                    restaurantList.addAll(items.data)


                } else {
                    errorMsg = "Aucun restaurants"
                }
            } catch (e: Exception) {
                errorMsg = e.message.toString()
            }
        }
    }

}