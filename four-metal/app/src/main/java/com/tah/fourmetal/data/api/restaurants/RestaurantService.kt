package com.tah.fourmetal.data.api.restaurants

import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RestaurantService {
    @GET("restaurants")
    suspend fun getRestaurants(): NetworkResponse<RestaurantResp, RestaurantErrorResp>

    @GET("restaurant/{id}")
    suspend fun getRestaurantFromId(@Path("id") id: Int): NetworkResponse<SingleRestaurantResp, RestaurantErrorResp>

    @GET("restaurant/{id}/menus")
    suspend fun getMenusFromRestaurant(@Path("id") id: Int): NetworkResponse<RestaurantMenuResp, RestaurantErrorResp>
}