package com.tah.fourmetal.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.SessionManager
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.auth.AuthService
import com.tah.fourmetal.data.api.auth.login.AbilitiesBody
import com.tah.fourmetal.data.api.auth.register.RegisterBody
import com.tah.fourmetal.data.models.AuthUser
import com.tah.fourmetal.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

class CheckRightsViewModel constructor(val retrofitInstance: RetrofitInstance) : ViewModel() {
    var errorMsg by mutableStateOf("")
    var currState by mutableStateOf(false)

    fun checkRights(user: AuthUser?, abilities: List<String>) {

        Log.d("user", user.toString());
        if (user == null) {
            currState = false;
            return
        }
        viewModelScope.launch {
            val retrofit = retrofitInstance.getInst().create(AuthService::class.java)
            val checkInfo = AbilitiesBody(user.token, abilities)
            when (val resp = retrofit.check(checkInfo)) {
                is NetworkResponse.Success -> {

                    currState = resp.body.message
                }
                is NetworkResponse.Error -> {
                    currState = false
                    errorMsg = resp.body?.message.orEmpty()
                }
            }
        }

    }
}