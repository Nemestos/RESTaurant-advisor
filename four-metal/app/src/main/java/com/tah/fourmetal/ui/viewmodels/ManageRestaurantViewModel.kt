package com.tah.fourmetal.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.auth.AuthService
import com.tah.fourmetal.data.api.auth.register.RegisterBody
import com.tah.fourmetal.data.api.restaurants.RestaurantService
import kotlinx.coroutines.launch

class ManageRestaurantViewModel constructor(
    val restaurantViewModel: RestaurantViewModel,
) :
    ViewModel() {

    var currState by mutableStateOf("")

    fun deleteRestaurant(id: Int) {
        viewModelScope.launch {
            val retrofitInstance = RetrofitInstance.getInst().create(RestaurantService::class.java)
            when (val resp = retrofitInstance.deleteRestaurantFromId(id)) {
                is NetworkResponse.Success -> {
                    currState = "sucessful delete"
                    restaurantViewModel.refreshRestaurantList()
                }
                is NetworkResponse.Error -> {
                    currState = "can't delete"
                }

            }
        }
    }
}