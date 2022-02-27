package com.tah.fourmetal.ui.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TopBarNavigation(name: String) {
    val avm = getViewModel<AuthViewModel>()
    val currUser = avm.sessionManager.currentUserFlow.collectAsState(initial = null)
    TopAppBar() {

        Row() {

            Text(text = name)
            if (currUser.value != null) {
                Text(text = currUser.toString())
            }
        }
    }

}
