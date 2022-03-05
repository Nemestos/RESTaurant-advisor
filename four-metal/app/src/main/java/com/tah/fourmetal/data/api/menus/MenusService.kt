package com.tah.fourmetal.data.api.menus

import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.api.restaurants.RestaurantErrorResp
import com.tah.fourmetal.data.api.restaurants.RestaurantMenuResp
import com.tah.fourmetal.data.api.restaurants.RestaurantUpdateResp
import com.tah.fourmetal.data.api.restaurants.SingleMenuResp
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface MenusService {
    @GET("restaurant/{id}/menus")
    suspend fun getMenusFromRestaurant(@Path("id") id: Int): NetworkResponse<RestaurantMenuResp, RestaurantErrorResp>

    @GET("restaurant/{rest_id}/menu/{menu_id}")
    suspend fun getMenuFromId(
        @Path("rest_id") rest_id: Int,
        @Path("menu_id") menu_id: Int
    ): NetworkResponse<SingleMenuResp, RestaurantErrorResp>

    @PUT("restaurant/{rest_id}/menu/{menu_id}")
    suspend fun updateMenu(
        @Path("rest_id") rest_id: Int,
        @Path("menu_id") menu_id: Int,
        @Body info: MenuUpdateBody
    ): NetworkResponse<RestaurantUpdateResp, RestaurantErrorResp>
}