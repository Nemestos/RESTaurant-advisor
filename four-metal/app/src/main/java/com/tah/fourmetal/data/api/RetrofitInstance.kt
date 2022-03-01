package com.tah.fourmetal.data.api

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.internal.immutableListOf
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RetrofitInstance {


    companion object {
        const val BASE_URL = "http://192.168.56.1:81/api/"

        //singleton(une seule instance)
        fun getInst(): Retrofit {

            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client: OkHttpClient =
                OkHttpClient.Builder()
                    .protocols(immutableListOf(Protocol.HTTP_1_1))
                    .retryOnConnectionFailure(true)
                    .apply {
                        this.addInterceptor(interceptor)
                    }.build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .build()
        }

    }
}
