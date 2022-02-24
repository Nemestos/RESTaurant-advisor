package com.tah.fourmetal.ui.restaurant

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import org.intellij.lang.annotations.JdkConstants

@Composable
fun RestaurantList(rvm: RestaurantViewModel) {
    LaunchedEffect(Unit, block = {
        rvm.getRestaurantList()
    })
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text("Restaurants")
                    }
                })
        },
        content = {
            if (rvm.errorMsg.isEmpty()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()) {
                        items(rvm.restaurantList) { rest ->
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box() {
                                        Text(
                                            text = rest.name,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                                Divider()
                            }

                        }
                    }
                }
            } else {
                Text(text = rvm.errorMsg)
            }
        }
    )
}