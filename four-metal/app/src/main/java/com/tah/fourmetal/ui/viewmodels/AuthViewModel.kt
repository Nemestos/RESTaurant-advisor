package com.tah.fourmetal.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.*
import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.SessionManager

import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.auth.AuthService
import com.tah.fourmetal.data.api.auth.login.LoginBody
import com.tah.fourmetal.data.api.auth.register.RegisterBody
import com.tah.fourmetal.data.models.AuthUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton


class AuthViewModel constructor(val sessionManager: SessionManager) : ViewModel() {
    var errorMsg by mutableStateOf("")
    var successMsg by mutableStateOf("")
    var errorsList by mutableStateOf(listOf<String>())
    fun register(
        login: String,
        password: String,
        email: String,
        name: String,
        firstname: String,
        age: Int,
    ) {
        viewModelScope.launch {
            val retrofitInstance = RetrofitInstance.getInst().create(AuthService::class.java)
            val registerInfo = RegisterBody(login, password, email, name, firstname, age)
            when (val resp = retrofitInstance.register(registerInfo)) {
                is NetworkResponse.Success -> {
                    Log.d("register:", resp.response.code().toString())
                    successMsg = "Successful register"
                    errorMsg = ""
                }
                is NetworkResponse.Error -> {
                    errorsList = resp.body!!.errors
                    errorMsg = resp.body?.message.orEmpty()
                    Log.d("register:", resp.body!!.errors.toString())
                }

            }
        }
    }

    fun login(
        login: String,
        password: String,
    ) {
        viewModelScope.launch {
            val retrofitInstance = RetrofitInstance.getInst().create(AuthService::class.java)
            val loginInfo = LoginBody(login, password)
            when (val resp = retrofitInstance.login(loginInfo)) {
                is NetworkResponse.Success -> {
                    sessionManager.login(
                        AuthUser(
                            resp.body.token,
                            resp.body.user_id
                        )
                    )
                }
                is NetworkResponse.Error -> {
                    errorMsg = resp.body?.message.orEmpty()

                }

            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            sessionManager.logout()
        }
    }

    fun clear() {
        errorMsg = ""
        successMsg = ""
        errorsList = listOf()
    }


}