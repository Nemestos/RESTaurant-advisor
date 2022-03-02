package com.tah.fourmetal.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.api.restaurants.RestaurantResp
import com.tah.fourmetal.data.api.restaurants.RestaurantService
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.auth.AuthService
import com.tah.fourmetal.data.models.Menu
import com.tah.fourmetal.data.models.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class RestaurantViewModel constructor(
    val retrofitInstance: RetrofitInstance
) : ViewModel() {
    var errorMsg by mutableStateOf("")
    val restaurantList = mutableStateListOf<Restaurant>()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    suspend fun getRestaurantFromId(id: Int): Restaurant? {
        val retrofit = retrofitInstance.getInst().create(RestaurantService::class.java)
        return when (val restaurant = retrofit.getRestaurantFromId(id)) {
            is NetworkResponse.Success -> {
                restaurant.body.data
            }
            is NetworkResponse.Error -> {
                null
            }
        }

    }


    suspend fun getRestaurantList() {

        val retrofit = retrofitInstance.getInst().create(RestaurantService::class.java)
        when (val restaurants = retrofit.getRestaurants()) {

            is NetworkResponse.Success -> {
                Log.d("restaurants:", restaurants.toString())
                if (restaurants.body.data.isEmpty()) {
                    errorMsg = "Aucun restaurants"
                } else {
                    errorMsg = ""
                    restaurantList.clear()
                    restaurantList.addAll(restaurants.body.data)
                }
            }
            is NetworkResponse.Error -> {
                errorMsg = "Erreur lors de la récupéraration des restaurants"
            }

        }

    }

    fun refreshRestaurantList() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            getRestaurantList()
            _isRefreshing.emit(false)
        }
    }

}