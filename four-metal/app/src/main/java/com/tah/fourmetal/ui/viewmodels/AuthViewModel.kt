package com.tah.fourmetal.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.SessionManager
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.auth.AuthService
import com.tah.fourmetal.data.api.auth.login.LoginBody
import com.tah.fourmetal.data.api.auth.register.RegisterBody
import com.tah.fourmetal.data.models.AuthUser
import kotlinx.coroutines.launch


class AuthViewModel constructor(
    val sessionManager: SessionManager,
    val retrofitInstance: RetrofitInstance
) :
    ViewModel() {
    var errorMsg by mutableStateOf("")
    var successMsg by mutableStateOf("")
    var errorsList by mutableStateOf(listOf<String?>())
    fun register(
        login: String,
        password: String,
        email: String,
        name: String,
        firstname: String,
        age: Int,
    ) {
        viewModelScope.launch {
            val retrofit = retrofitInstance.getInst().create(AuthService::class.java)
            val registerInfo = RegisterBody(login, password, email, name, firstname, age)
            when (val resp = retrofit.register(registerInfo)) {
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
            val retrofit = retrofitInstance.getInst().create(AuthService::class.java)
            val loginInfo = LoginBody(login, password)
            when (val resp = retrofit.login(loginInfo)) {
                is NetworkResponse.Success -> {
                    successMsg = "Success register"
                    sessionManager.login(
                        AuthUser(
                            resp.body.token,
                            resp.body.user_id
                        )
                    )
                }
                is NetworkResponse.Error -> {
                    errorMsg = resp.body?.message.orEmpty()
                    if (resp.body?.errors != null) {

                        errorsList = resp.body!!.errors!!
                    }


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