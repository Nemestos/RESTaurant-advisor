package com.tah.fourmetal.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.auth.AuthService
import com.tah.fourmetal.data.api.auth.BaseAuthResp
import com.tah.fourmetal.data.api.auth.register.RegisterBody
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthViewModel : ViewModel() {
    lateinit var respState: MutableState<ResourceResp<BaseAuthResp>>;

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
            val resp = retrofitInstance.register(registerInfo)
            val result = resp.body()
            try {
                if (resp.isSuccessful && result != null) {
                    respState = ResourceResp.Success(result)
                }
            } catch (e: Exception)
        }
    }


}