package com.tah.fourmetal.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.tah.fourmetal.R
import com.tah.fourmetal.data.models.Restaurant

sealed class NavItem(
    var title: String,
    var screen_route: String,
    var arguments: List<NamedNavArgument> = listOf()
) {
    object RestaurantDetail : NavItem(
        "View Restaurant", "restaurant/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
    )

}
