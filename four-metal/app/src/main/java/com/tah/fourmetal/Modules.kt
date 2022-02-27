package com.tah.fourmetal

import androidx.lifecycle.viewmodel.compose.viewModel
import com.tah.fourmetal.data.SessionManager
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single { SessionManager(androidContext()) }
    single { AuthViewModel(get()) }
    viewModel { RestaurantViewModel() }
}