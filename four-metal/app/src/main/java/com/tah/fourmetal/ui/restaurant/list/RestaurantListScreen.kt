package com.tah.fourmetal.ui.restaurant

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun RestaurantListScreen() {
    val rvm = getViewModel<RestaurantViewModel>()
    val isRefreshing by rvm.isRefreshing.collectAsState()
    var currSearchFilter by remember { mutableStateOf("") }
    LaunchedEffect(Unit, block = {
        rvm.getRestaurantList()
        Log.d("restaurants:", rvm.errorMsg)

    })
    Scaffold(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 50.dp),
        backgroundColor = Color(0xFFE3D3C0)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            RestaurantListTop(title = "four metal")
            SearchBar(value = currSearchFilter, onValueChange = { currSearchFilter = it })
            if (rvm.errorMsg.isEmpty()) {
                Log.d("debug", currSearchFilter)
                RestaurantList(
                    rvm.restaurantList,
                    isRefreshing,
                    currSearchFilter
                ) { rvm.refreshRestaurantList() }
            } else {
                RestaurantListError(rvm.errorMsg)

            }
        }

    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp), horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            trailingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search ")
            }
        )

    }

}

