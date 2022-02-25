package com.tah.fourmetal.data.api.auth

import com.tah.fourmetal.data.api.restaurants.RestaurantResp
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type:application/json")
    @POST("auth")
    suspend fun login(@Body info: LoginBody): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("register")
    suspend fun register(@Body info: RegisterBody): Call<ResponseBody>

}