package com.tah.fourmetal.ui.restaurant.menu

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.tah.fourmetal.data.api.restaurants.update.MenuUpdateBody
import com.tah.fourmetal.data.api.restaurants.update.RestaurantUpdateBody
import com.tah.fourmetal.data.models.Menu
import com.tah.fourmetal.data.models.Restaurant
import com.tah.fourmetal.ui.Utils
import com.tah.fourmetal.ui.form.Field
import com.tah.fourmetal.ui.form.Form
import com.tah.fourmetal.ui.form.FormState
import com.tah.fourmetal.ui.form.Validator
import com.tah.fourmetal.ui.navigation.BottomNavItem
import com.tah.fourmetal.ui.navigation.LocalNavController
import com.tah.fourmetal.ui.navigation.NavItem
import com.tah.fourmetal.ui.viewmodels.ManageRestaurantViewModel
import com.tah.fourmetal.ui.viewmodels.MenusViewModel
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun MenuUpdateScreen(rest_id: Int, menu_id: Int) {
    val mvm = getViewModel<MenusViewModel>()
    val state by remember {
        mutableStateOf(FormState())
    }
    val navController = LocalNavController.current
    val context = LocalContext.current
    var menu by remember { mutableStateOf<Menu?>(null) }
    LaunchedEffect(key1 = Unit) {
        menu = mvm.getMenu(rest_id, menu_id)
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Form(
            state = state,
            fields = listOf(
                Field(
                    name = "name",
                    label = "Name:",
                    defaultValue = menu?.name.orEmpty(),
                    validators = listOf(Validator.Required())
                ),
                Field(
                    name = "description",
                    label = "Description:",
                    defaultValue = menu?.description.orEmpty(),
                    validators = listOf(Validator.Required())
                ),
                Field(
                    name = "price",
                    label = "Price:",
                    defaultValue = menu?.price.toString(),
                    autoComplete = false,
                    keyboardType = KeyboardType.Number,
                    validators = listOf(
                        Validator.Required(),
                        Validator.Regex(
                            regex = "^\\d*(\\.\\d*)?\$",
                            message = "Veuiller entrer un flotant valide"
                        )
                    )
                ),

                ),
            onSubmitClick = {
                if (state.validate()) {
                    val values = state.getData()
                    val bodyValue = MenuUpdateBody(
                        values["name"],
                        values["description"],
                        values["price"]?.toFloat(),
                        )
                    mvm.onMenuUpdate(rest_id, menu_id, bodyValue)
                    navController.navigateUp()


                }
            },
            onClearClick = {
                state.clear()
            }
        )

    }

}