package com.tah.fourmetal.ui.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.restaurants.RestaurantService
import com.tah.fourmetal.data.api.restaurants.RestaurantUpdateBody
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
                    Toast.makeText(context, resp.body.message, Toast.LENGTH_SHORT).show()

                    restaurantViewModel.refreshRestaurantList()
                }
                is NetworkResponse.Error -> {
                    Toast.makeText(context, "you don't have the rights", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    fun updateRestaurant(id: Int, updatedRestaurant: RestaurantUpdateBody) {
        viewModelScope.launch {
            val retrofit = retrofitInstance.getInst().create(RestaurantService::class.java)
            when (val resp = retrofit.updateRestaurant(id, updatedRestaurant)) {
                is NetworkResponse.Success -> {
                    Toast.makeText(context, resp.body.message, Toast.LENGTH_SHORT).show()
                    restaurantViewModel.refreshRestaurantList()
                }
                is NetworkResponse.Error -> {
                    Toast.makeText(context, "Can't update this restaurant", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }
}