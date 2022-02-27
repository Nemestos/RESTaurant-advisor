package com.tah.fourmetal.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tah.fourmetal.ui.login.LoginScreen
import com.tah.fourmetal.ui.register.RegisterScreen
import com.tah.fourmetal.ui.restaurant.RestaurantListScreen
import com.tah.fourmetal.ui.restaurant.RestaurantsScreen
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import kotlinx.coroutines.flow.onEmpty

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
