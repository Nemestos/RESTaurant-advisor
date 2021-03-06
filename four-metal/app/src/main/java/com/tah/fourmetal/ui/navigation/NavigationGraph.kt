package com.tah.fourmetal.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.tah.fourmetal.ui.login.LoginScreen
import com.tah.fourmetal.ui.register.RegisterScreen
import com.tah.fourmetal.ui.restaurant.RestaurantListScreen
import com.tah.fourmetal.ui.restaurant.RestaurantsScreen
import com.tah.fourmetal.ui.restaurant.ShowRestaurantContent
import com.tah.fourmetal.ui.restaurant.details.RestaurantUpdateScreen
import com.tah.fourmetal.ui.restaurant.menu.MenuUpdateScreen
import com.tah.fourmetal.ui.restaurant.menu.ShowRestaurantMenu
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import kotlinx.coroutines.flow.onEmpty

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController, startDestination = BottomNavItem.Restaurants.screen_route) {
        composable(BottomNavItem.Restaurants.screen_route) {
            RestaurantsScreen()
        }
        composable(
            NavItem.RestaurantDetail.screen_route,
            arguments = NavItem.RestaurantDetail.arguments
        ) {
            it.arguments?.getInt("id")?.let { it1 -> ShowRestaurantContent(it1) }
        }
        composable(
            NavItem.RestaurantMenu.screen_route,
            arguments = NavItem.RestaurantMenu.arguments
        ) {
            it.arguments?.getInt("rest_id")?.let { it1 -> ShowRestaurantMenu(it1) }
        }
        composable(
            NavItem.RestaurantUpdateForm.screen_route,
            arguments = NavItem.RestaurantUpdateForm.arguments
        ) {
            it.arguments?.getInt("rest_id")?.let { it1 -> RestaurantUpdateScreen(it1) }
        }
        composable(
            NavItem.MenuUpdateForm.screen_route,
            arguments = NavItem.MenuUpdateForm.arguments
        ) {
            val rest_id = it.arguments?.getInt("rest_id")
            val menu_id = it.arguments?.getInt("menu_id")
            if (rest_id != null && menu_id != null) {
                MenuUpdateScreen(rest_id = rest_id, menu_id = menu_id)
            }
        }
        composable(BottomNavItem.Login.screen_route) {
            LoginScreen()
        }
        composable(BottomNavItem.Register.screen_route) {
            RegisterScreen()
        }

    }


}

val LocalNavController = compositionLocalOf<NavHostController> {
    error("no local nav controller provided")
}
