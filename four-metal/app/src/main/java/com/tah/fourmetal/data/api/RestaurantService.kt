package com.tah.fourmetal.data.api

import com.google.gson.Gson
import com.tah.fourmetal.data.models.Restaurant
import com.tah.fourmetal.data.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://rest-etna-42.loca.lt/api/"

interface RestaurantService {
    @GET("restaurants")
    suspend fun getRestaurants(): Response<RestaurantResp>

    companion object {
        var restaurantService: RestaurantService? = null

        //singleton(une seule instance)
        fun getInst(): RestaurantService {
            if (restaurantService == null) {
                restaurantService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RestaurantService::class.java)
            }
            return restaurantService!!
        }
    }
}