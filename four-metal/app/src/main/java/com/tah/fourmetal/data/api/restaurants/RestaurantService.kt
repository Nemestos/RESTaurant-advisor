package com.tah.fourmetal.data.api.restaurants

import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.api.restaurants.update.MenuUpdateBody
import com.tah.fourmetal.data.api.restaurants.update.RestaurantUpdateBody
import retrofit2.Response
import retrofit2.http.*

interface RestaurantService {
    @GET("restaurants")
    suspend fun getRestaurants(): NetworkResponse<RestaurantResp, RestaurantErrorResp>

    @GET("restaurant/{id}")
    suspend fun getRestaurantFromId(@Path("id") id: Int): NetworkResponse<SingleRestaurantResp, RestaurantErrorResp>

    @GET("restaurant/{id}/menus")
    suspend fun getMenusFromRestaurant(@Path("id") id: Int): NetworkResponse<RestaurantMenuResp, RestaurantErrorResp>

    @GET("restaurant/{rest_id}/menu/{menu_id}")
    suspend fun getMenuFromId(
        @Path("rest_id") rest_id: Int,
        @Path("menu_id") menu_id: Int
    ): NetworkResponse<SingleMenuResp, RestaurantErrorResp>

    @DELETE("restaurant/{rest_id}")
    suspend fun deleteRestaurantFromId(@Path("rest_id") rest_id: Int): NetworkResponse<RestaurantDeleteResp, RestaurantErrorResp>

    @DELETE("restaurant/{rest_id}/menu/{menu_id}")
    suspend fun deleteMenuFromId(
        @Path("rest_id") rest_id: Int,
        @Path("menu_id") menu_id: Int
    ): NetworkResponse<RestaurantDeleteResp, RestaurantErrorResp>

    @PUT("restaurant/{rest_id}")
    suspend fun updateRestaurant(
        @Path("rest_id") rest_id: Int,
        @Body info: RestaurantUpdateBody
    ): NetworkResponse<RestaurantUpdateResp, RestaurantErrorResp>

    @PUT("restaurant/{rest_id}/menu/{menu_id}")
    suspend fun updateMenu(
        @Path("rest_id") rest_id: Int,
        @Path("menu_id") menu_id: Int,
        @Body info: MenuUpdateBody
    ): NetworkResponse<RestaurantUpdateResp, RestaurantErrorResp>
}