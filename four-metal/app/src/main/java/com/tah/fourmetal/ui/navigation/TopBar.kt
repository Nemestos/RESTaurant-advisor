package com.tah.fourmetal.ui.navigation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.tah.fourmetal.R
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TopBarNavigation(name: String) {
    val avm = getViewModel<AuthViewModel>()
    val currUser = avm.sessionManager.currentUserFlow.collectAsState(initial = null)
    val context = LocalContext.current
    val navController = LocalNavController.current
    TopAppBar(
        title = { Text(text = name) },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
            if (currUser.value != null) {
                LogoutBtn {
                    avm.logout()
                }
            }
        }
    )

}

@Composable
fun LogoutBtn(onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        Icon(
            Icons.Filled.Logout,
            contentDescription = "Logout from account",
            tint = Color.LightGray
        )

    }
}
