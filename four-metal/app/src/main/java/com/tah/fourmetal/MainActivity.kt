package com.tah.fourmetal

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tah.fourmetal.ui.navigation.*
import com.tah.fourmetal.ui.restaurant.RestaurantListScreen
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.compose.getViewModel
import java.util.prefs.Preferences
import javax.inject.Inject


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(LocalNavController provides rememberNavController()) {

                MainScreenContent()
            }
        }
    }
}

@Composable
fun MainScreenContent() {

    val authViewModel = getViewModel<AuthViewModel>()
    val authUser by authViewModel.sessionManager.currentUserFlow.collectAsState(initial = null)
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val items = when (authUser) {
        null -> listOf<BottomNavItem>(
            BottomNavItem.Restaurants,
            BottomNavItem.Login,
            BottomNavItem.Register,
        )
        else -> listOf<BottomNavItem>(
            BottomNavItem.Restaurants,

            )
    }
    Scaffold(
        topBar = {
            TopBarNavigation(name = navBackStackEntry?.destination?.displayName.orEmpty())
        },
        bottomBar = {
            BottomNavigation(navController = navController, items = items)
        },
        backgroundColor = Color.Black
    ) {
        NavigationGraph(
            navController = navController,
        )
    }
}


