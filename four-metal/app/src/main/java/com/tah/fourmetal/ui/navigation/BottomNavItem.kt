package com.tah.fourmetal.ui.navigation

import com.tah.fourmetal.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Restaurants : BottomNavItem("Restaurants", R.drawable.ic_restaurant, "restaurants")
    object Login : BottomNavItem("Login", R.drawable.ic_login, "login")
    object Register : BottomNavItem("Register", R.drawable.ic_register, "register")
}
