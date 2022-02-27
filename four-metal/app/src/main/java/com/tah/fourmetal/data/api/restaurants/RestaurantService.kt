package com.tah.fourmetal.data.api.restaurants

import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantService {
    @GET("restaurants")
    suspend fun getRestaurants(): NetworkResponse<RestaurantResp, RestaurantErrorResp>
}