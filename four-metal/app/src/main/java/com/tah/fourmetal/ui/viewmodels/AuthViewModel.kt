package com.tah.fourmetal.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.auth.AuthService
import com.tah.fourmetal.data.api.auth.RegisterBody
import com.tah.fourmetal.data.api.restaurants.RestaurantResp
import com.tah.fourmetal.data.api.restaurants.RestaurantService
import com.tah.fourmetal.data.models.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthViewModel : ViewModel() {


    fun register(
        login: String,
        password: String,
        email: String,
        name: String,
        firstname: String,
        age: Int,
    ) {
        viewModelScope.launch {
            val retrofitInstance = RetrofitInstance.getInst().create(AuthService::class.java)
            val registerInfo = RegisterBody()
            try {
                restaurantList.clear()
                val resp = retrofitInstance.getRestaurants()
                val items: RestaurantResp? = resp.body()
                Log.d("restaurants:", items.toString())
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

    fun refreshRestaurantList() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            getRestaurantList()
            _isRefreshing.emit(false)
        }
    }

}