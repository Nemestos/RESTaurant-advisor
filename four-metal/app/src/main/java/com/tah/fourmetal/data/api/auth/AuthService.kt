package com.tah.fourmetal.data.api.auth

import com.tah.fourmetal.data.api.auth.login.LoginBody
import com.tah.fourmetal.data.api.auth.register.RegisterBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type:application/json")
    @POST("auth")
    suspend fun login(@Body info: LoginBody): Response<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("register")
    suspend fun register(@Body info: RegisterBody): Response<ResponseBody>

}