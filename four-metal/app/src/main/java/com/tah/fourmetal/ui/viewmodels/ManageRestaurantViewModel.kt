package com.tah.fourmetal.ui.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
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
    val context: Context,
    val restaurantViewModel: RestaurantViewModel,
    val retrofitInstance: RetrofitInstance
) :
    ViewModel() {


    fun deleteRestaurant(id: Int) {
        viewModelScope.launch {
            val retrofit = retrofitInstance.getInst().create(RestaurantService::class.java)
            when (val resp = retrofit.deleteRestaurantFromId(id)) {
                is NetworkResponse.Success -> {
                    Toast.makeText(context, "success for delete ", Toast.LENGTH_SHORT).show()

                    restaurantViewModel.refreshRestaurantList()
                }
                is NetworkResponse.Error -> {
                    Toast.makeText(context, "you don't have the rights", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}