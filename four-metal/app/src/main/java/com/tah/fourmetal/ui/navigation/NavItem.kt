package com.tah.fourmetal.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.tah.fourmetal.R
import com.tah.fourmetal.data.models.Restaurant

sealed class NavItem(
    var title: String,
    var route_base: String,
    var screen_route: String,
    var arguments: List<NamedNavArgument> = listOf()
) {
    object RestaurantDetail : NavItem(
        "View Restaurant", "restaurant", "restaurant/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
    )

    object RestaurantMenu : NavItem(
        "View Menus", "restaurant", "restaurant/{rest_id}/menus",
        arguments = listOf(
            navArgument("rest_id") { type = NavType.IntType }
        ),
    )

    object RestaurantUpdateForm : NavItem(
        "Update Restaurant", "restaurant/update", "restaurant/update/{rest_id}", arguments = listOf(
            navArgument("rest_id") { type = NavType.IntType }
        )
    )

    object MenuUpdateForm : NavItem(
        "Update menu",
        "restaurant/menu/update",
        "restaurant/menu/update/{rest_id}/{menu_id}",
        arguments = listOf(
            navArgument("rest_id") { type = NavType.IntType },
            navArgument("menu_id") { type = NavType.IntType }
        )
    )

}
