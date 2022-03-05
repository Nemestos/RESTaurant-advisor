package com.tah.fourmetal.data.api

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.tah.fourmetal.data.SessionManager
import com.tah.fourmetal.data.api.auth.AuthInterceptor
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.internal.immutableListOf
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http://192.168.1.19:81/api/"

class RetrofitInstance constructor(val sessionManager: SessionManager) {


    //singleton(une seule instance)
    fun getInst(): Retrofit {

        val logginInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient =
            OkHttpClient.Builder()
                .protocols(immutableListOf(Protocol.HTTP_1_1))
                .retryOnConnectionFailure(true)
                .apply {
                    this.addInterceptor(logginInterceptor)
                    this.addInterceptor(AuthInterceptor(sessionManager))

                }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }


}
