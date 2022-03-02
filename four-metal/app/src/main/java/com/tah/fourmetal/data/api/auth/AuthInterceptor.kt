package com.tah.fourmetal.data.api.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.asLiveData
import com.tah.fourmetal.data.SessionManager
import com.tah.fourmetal.data.models.AuthUser
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor constructor(val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val reqBuilder = chain.request().newBuilder()
        val currUser: AuthUser?

        runBlocking(Dispatchers.IO) {
            currUser = sessionManager.currentUserFlow.first()
        }
        Log.d("enbas", currUser.toString())
        if (currUser != null) {
            Log.d("token", currUser.token.toString())
            reqBuilder.addHeader("Authorization", "Bearer ${currUser.token}")
        }

        return chain.proceed(reqBuilder.build())
    }

}