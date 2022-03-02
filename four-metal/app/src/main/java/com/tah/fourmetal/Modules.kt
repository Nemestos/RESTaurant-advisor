package com.tah.fourmetal

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.ImageLoader
import com.tah.fourmetal.data.SessionManager
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.ui.navigation.LocalNavController
import com.tah.fourmetal.ui.navigation.NavigationGraph
import com.tah.fourmetal.ui.viewmodels.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { SessionManager(androidContext()) }
    single { ImageLoader(androidContext()) }
    single { RetrofitInstance(get()) }

    viewModel { AuthViewModel(get(), get()) }

    viewModel { CheckRightsViewModel(get()) }
    viewModel { RestaurantViewModel(get()) }
    viewModel { MenusViewModel(get()) }
    viewModel { ManageRestaurantViewModel(androidContext(), get(), get()) }
}