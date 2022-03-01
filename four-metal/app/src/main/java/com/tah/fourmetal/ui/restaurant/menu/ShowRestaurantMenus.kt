package com.tah.fourmetal.ui.restaurant.menu

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tah.fourmetal.data.models.Menu
import com.tah.fourmetal.data.models.Restaurant
import com.tah.fourmetal.ui.HeaderTitle
import com.tah.fourmetal.ui.viewmodels.MenusViewModel
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import org.koin.androidx.compose.getViewModel

const val EXPAND_DURATION = 500
const val FADE_IN_DURATION = 300
const val FADE_OUT_DURATION = 700
const val COLLAPSE_DURATION = 500
val card_expanded = Color.Green
val card_collapsed = Color.White

@Composable
fun ShowRestaurantMenu(id: Int) {

    val menusViewModel = getViewModel<MenusViewModel>()
    val menus = menusViewModel.menus.collectAsState()
    val expandableMenus = menusViewModel.expandableMenus.collectAsState()
    LaunchedEffect(key1 = Unit) {
        menusViewModel.getMenus(id)
    }
    HeaderTitle(text = "Nos Menus")
    LazyColumn {
        itemsIndexed(menus.value) { _, menu ->
            ExpandableMenu(
                menu = menu,
                onMenuClicked = {
                    menusViewModel.onMenuClicked(menu.id)
                    Log.d("debug:", "clicked")
                },
                expanded = expandableMenus.value.contains(menu.id)
            )
        }
    }
}

@Composable
fun MenuTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MenuContent(
    menu: Menu,
    visible: Boolean = true,
    initialVisibility: Boolean = false
) {
    val enterFadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = FADE_IN_DURATION,
                easing = FastOutLinearInEasing
            )
        )
    }
    val enterExpand = remember {
        expandVertically(animationSpec = tween(EXPAND_DURATION))
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = FADE_OUT_DURATION,
                easing = LinearOutSlowInEasing
            )
        )
    }
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(COLLAPSE_DURATION))
    }

    AnimatedVisibility(
        visible = visible,
        initiallyVisible = initialVisibility,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
//            Spacer(modifier = Modifier.heightIn(100.dp))
            Text(
                "Description:${menu.description}",
                textAlign = TextAlign.Center
            )
            Text(
                "Prix:${menu.price}\$",
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun ExpandableMenu(menu: Menu, onMenuClicked: () -> Unit, expanded: Boolean) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState != !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "transition menu")
    val menudBgColor by transition.animateColor({
        tween(durationMillis = EXPAND_DURATION)
    }, label = "") {
        if (it) card_expanded else card_collapsed
    }
    val menuElevation by transition.animateDp({
        tween(durationMillis = EXPAND_DURATION)
    }, label = "") {
        if (it) 48.dp else 24.dp
    }

    val menuRoundCorners by transition.animateDp({
        tween(durationMillis = EXPAND_DURATION, easing = FastOutSlowInEasing)
    }, label = "") {
        if (it) 0.dp else 16.dp

    }

    Card(
        backgroundColor = menudBgColor,
        elevation = menuElevation,
        shape = RoundedCornerShape(menuRoundCorners),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Box(modifier = Modifier.clickable { onMenuClicked() }) {
                MenuTitle(title = menu.name)

            }
            MenuContent(menu = menu, visible = expanded, initialVisibility = expanded)
        }

    }
}