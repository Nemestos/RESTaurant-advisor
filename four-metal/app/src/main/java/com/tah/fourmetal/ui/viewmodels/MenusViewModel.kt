package com.tah.fourmetal.ui.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.auth.AuthService
import com.tah.fourmetal.data.api.restaurants.RestaurantService
import com.tah.fourmetal.data.api.restaurants.update.MenuUpdateBody
import com.tah.fourmetal.data.models.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenusViewModel constructor(
    val context: Context,

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
                    Toast.makeText(context, "can't get menus", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    suspend fun getMenu(rest_id: Int, menu_id: Int): Menu? {
        val retrofit = retrofitInstance.getInst().create(RestaurantService::class.java)
        return when (val menu = retrofit.getMenuFromId(rest_id, menu_id)) {
            is NetworkResponse.Success -> {
                menu.body.data
            }
            is NetworkResponse.Error -> {
                null
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

    fun onMenuRemove(rest_id: Int, menu_id: Int) {
        viewModelScope.launch {
            val retrofit = retrofitInstance.getInst().create(RestaurantService::class.java)
            when (val resp = retrofit.deleteMenuFromId(rest_id, menu_id)) {
                is NetworkResponse.Success -> {
                    Toast.makeText(context, "success for delete menu", Toast.LENGTH_SHORT).show()
                    //on refresh l'etat(on ne supprime par l'element directement de la liste : seul le back est une source
                    //de confiance
                    getMenus(rest_id)
                }
                is NetworkResponse.Error -> {
                    Toast.makeText(context, "can't delete menu", Toast.LENGTH_SHORT).show()

                }
            }

        }
    }

    fun onMenuUpdate(rest_id: Int, menu_id: Int, menuBody: MenuUpdateBody) {
        viewModelScope.launch {
            val retrofit = retrofitInstance.getInst().create(RestaurantService::class.java)
            when (val resp = retrofit.updateMenu(rest_id, menu_id, menuBody)) {
                is NetworkResponse.Success -> {
                    Toast.makeText(context, resp.body.message, Toast.LENGTH_SHORT).show()
                    getMenus(rest_id)
                }
                is NetworkResponse.Error -> {
                    Toast.makeText(context, resp.body?.message, Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }
}