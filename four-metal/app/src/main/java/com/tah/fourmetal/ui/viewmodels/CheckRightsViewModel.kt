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
import com.tah.fourmetal.data.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

class CheckRightsViewModel constructor(authViewModel: AuthViewModel) : ViewModel() {
    val currUser = authViewModel.sessionManager.currentUserFlow.asLiveData()
    var errorMsg by mutableStateOf("")

    init {
        currUser.obse
    }

    fun checkRights(abilities: List<String>): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>(false);
        Log.d("user", currUser.toString());
        if (currUser.value == null) {
            return result
        }
        viewModelScope.launch {
            val retrofitInstance = RetrofitInstance.getInst().create(AuthService::class.java)
            val checkInfo = AbilitiesBody(currUser.value!!.token, abilities)
            when (val resp = retrofitInstance.check(checkInfo)) {
                is NetworkResponse.Success -> {
                    result.postValue(resp.body.message)
                }
                is NetworkResponse.Error -> {
                    errorMsg = resp.body?.message.orEmpty()
                }
            }
        }
        return result
    }
}