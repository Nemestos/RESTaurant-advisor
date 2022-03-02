package com.tah.fourmetal

import androidx.navigation.NavController
import coil.ImageLoader
import com.tah.fourmetal.data.SessionManager
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import com.tah.fourmetal.ui.viewmodels.CheckRightsViewModel
import com.tah.fourmetal.ui.viewmodels.MenusViewModel
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { SessionManager(androidContext()) }
    single { ImageLoader(androidContext()) }
    single { AuthViewModel(get()) }
    viewModel { CheckRightsViewModel(get()) }
    viewModel { RestaurantViewModel() }
    viewModel { MenusViewModel() }
}