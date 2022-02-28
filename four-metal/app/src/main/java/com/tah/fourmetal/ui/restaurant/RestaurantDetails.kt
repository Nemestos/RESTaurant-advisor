package com.tah.fourmetal.ui.restaurant

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.compose.rememberNavController
import com.tah.fourmetal.data.models.Menu
import com.tah.fourmetal.data.models.Restaurant
import com.tah.fourmetal.ui.navigation.LocalNavController
import com.tah.fourmetal.ui.navigation.TopBarNavigation
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import kotlinx.coroutines.coroutineScope
import org.koin.androidx.compose.getViewModel


@Composable
fun ShowRestaurantContent(id: Int) {
    val rvm = getViewModel<RestaurantViewModel>()
    val navController = LocalNavController.current
    val coroutineScope = rememberCoroutineScope()
    var restaurant by remember { mutableStateOf<Restaurant?>(null) }
    var menus = remember {
        mutableStateListOf<Menu>()
    }
    LaunchedEffect(Unit, block = {
        restaurant = rvm.getRestaurantFromId(id)
        rvm.getMenus(id)?.let { menus.clear();menus.addAll(it) }
    })


    Column() {
        if (restaurant != null) {
            Text(text = restaurant?.name.orEmpty())
        }
        if (menus != null) {
            menus.forEach {
                Text(text = it.name)
            }
        } else {
            Text(text = "Aucun menus")
        }
    }


}