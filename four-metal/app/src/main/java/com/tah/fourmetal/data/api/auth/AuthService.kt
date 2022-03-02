package com.tah.fourmetal.data.api.auth

import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.api.auth.login.*
import com.tah.fourmetal.data.api.auth.register.RegisterBody
import com.tah.fourmetal.data.api.auth.register.RegisterErrorResp
import com.tah.fourmetal.data.api.auth.register.RegisterResp
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type:application/json")
    @POST("auth")
    suspend fun login(@Body info: LoginBody): NetworkResponse<LoginResp, LoginErrorResp>

    @Headers("Content-Type:application/json")
    @POST("register")
    suspend fun register(@Body info: RegisterBody): NetworkResponse<RegisterResp, RegisterErrorResp>

    @Headers("Content-Type:application/json")
    @POST("checkrights")
    suspend fun check(@Body info: AbilitiesBody): NetworkResponse<AbilitiesResp, AbilitiesErrorResp>

}