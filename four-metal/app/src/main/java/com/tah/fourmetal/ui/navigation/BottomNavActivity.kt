package com.tah.fourmetal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tah.fourmetal.ui.login.LoginScreen
import com.tah.fourmetal.ui.register.RegisterScreen
import com.tah.fourmetal.ui.restaurant.RestaurantListScreen
import com.tah.fourmetal.ui.restaurant.RestaurantsScreen
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Restaurants.screen_route) {
        composable(BottomNavItem.Restaurants.screen_route) {
            RestaurantsScreen()
        }
        composable(BottomNavItem.Login.screen_route) {
            LoginScreen()
        }
        composable(BottomNavItem.Register.screen_route) {
            RegisterScreen()
        }

    }
}