package com.tah.fourmetal.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.auth.AuthService
import com.tah.fourmetal.data.api.restaurants.RestaurantService
import com.tah.fourmetal.data.models.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenusViewModel constructor(
    val retrofitInstance: RetrofitInstance
) : ViewModel() {

    private val _menus = MutableStateFlow(listOf<Menu>())
    val menus: StateFlow<List<Menu>> get() = _menus

    private val _expandableMenus = MutableStateFlow(listOf<Int>())
    val expandableMenus: StateFlow<List<Int>> get() = _expandableMenus

    suspend fun getMenus(id: Int) {
        viewModelScope.launch(Dispatchers.Default) {

            val retrofit = retrofitInstance.getInst().create(RestaurantService::class.java)
            when (val menus = retrofit.getMenusFromRestaurant(id)) {
                is NetworkResponse.Success -> {
                    _menus.emit(menus.body.data)
                }
                is NetworkResponse.Error -> {

                }
            }
        }

    }

    fun onMenuClicked(id: Int) {
        _expandableMenus.value = _expandableMenus.value.toMutableList().also { list ->
            if (list.contains(id)) {
                list.remove(id)
            } else {
                list.add(id)
            }

        }
    }
}