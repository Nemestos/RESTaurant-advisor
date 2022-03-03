package com.tah.fourmetal.ui.restaurant

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.compose.rememberImagePainter
import com.tah.fourmetal.data.models.Menu
import com.tah.fourmetal.data.models.Restaurant
import com.tah.fourmetal.ui.navigation.BottomNavItem
import com.tah.fourmetal.ui.navigation.LocalNavController
import com.tah.fourmetal.ui.navigation.NavItem
import com.tah.fourmetal.ui.theme.Reenie
import com.tah.fourmetal.ui.theme.Roboto
import com.tah.fourmetal.ui.viewmodels.AuthViewModel
import com.tah.fourmetal.ui.viewmodels.CheckRightsViewModel
import com.tah.fourmetal.ui.viewmodels.ManageRestaurantViewModel
import com.tah.fourmetal.ui.viewmodels.RestaurantViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun ShowRestaurantContent(id: Int) {
    val rvm = getViewModel<RestaurantViewModel>()
    val navController = LocalNavController.current
    val coroutineScope = rememberCoroutineScope()
    var restaurant by remember { mutableStateOf<Restaurant?>(null) }
    LaunchedEffect(key1 = Unit) {
        restaurant = rvm.getRestaurantFromId(id)
    }
    val colors = listOf(
        MaterialTheme.colors.background,
        Color.Transparent
    )
    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),

        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        Image(
            painter = rememberImagePainter(
                data = restaurant?.image_url,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = "image restaurant",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .graphicsLayer {
                    alpha = 0.99f
                }
                .height(300.dp)
                .fillMaxWidth()
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(colors),
                        blendMode = BlendMode.DstIn
                    )
                }
        )
        Column(
            Modifier.padding(bottom = 100.dp, start = 10.dp, top = 10.dp, end = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = restaurant?.name.orEmpty(),
                fontFamily = Reenie,
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center

            )
            Row(Modifier.fillMaxWidth()) {

                RatingIndicator(colorTint = Color.Black, rating = restaurant?.grade.toString())
            }
            Text(
                text = restaurant?.localization.orEmpty(),
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                fontFamily = Roboto,
            )
            Text(
                text = restaurant?.description.orEmpty(),
                fontWeight = FontWeight.Light,
                fontFamily = Roboto,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = restaurant?.website.orEmpty(),
            )
            Text(
                text = restaurant?.phone_number.orEmpty(),
                fontFamily = Roboto,
                fontWeight = FontWeight.Thin
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Card(
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .wrapContentSize()

                        .padding(10.dp)
                ) {
                    Text(
                        text = restaurant?.hours.orEmpty(),
                        modifier = Modifier
                            .fillMaxWidth(0.60f)
                            .background(Color.White)
                            .padding(16.dp),
                        textAlign = TextAlign.Justify
                    )

                }
                RestaurantDetailsBottom(
                    restaurant = restaurant,
                    btnModifier = Modifier.wrapContentSize(),
                    fontSize = 30
                )
            }
        }

    }


}

@Composable
fun RestaurantDetailsBottom(
    btnModifier: Modifier = Modifier,
    fontSize: Int,
    restaurant: Restaurant?
) {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val mrvm = getViewModel<ManageRestaurantViewModel>()
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = btnModifier,
            onClick = {
                navController.navigate("${NavItem.RestaurantMenu.route_base}/${restaurant?.id}/menus")

            },

            ) {
            Text(
                text = "MENU",
                fontFamily = Reenie,
                textAlign = TextAlign.Center,
                fontSize = fontSize.sp
            )
        }
        Button(
            modifier = btnModifier,

            onClick = {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${restaurant?.phone_number}")
                context.startActivity(intent)
            },

            ) {
            Text(
                text = "RESERVER",
                fontFamily = Reenie,
                textAlign = TextAlign.Center,
                fontSize = fontSize.sp
            )

        }
        AbilityButton(
            textString = "Delete",
            fontSize = fontSize,
            onClick = {
                restaurant?.id?.let { mrvm.deleteRestaurant(it) }
                navController.navigate(BottomNavItem.Restaurants.screen_route)
            },
            abilities = listOf("delete_restaurant")
        )
        AbilityButton(
            textString = "Update",
            fontSize = fontSize,
            onClick = {
                navController.navigate("${NavItem.RestaurantUpdateForm.route_base}/${restaurant?.id}")
            },
            abilities = listOf("put_restaurant")
        )
    }
}

@Composable
fun AbilityButton(
    modifier: Modifier = Modifier,
    textString: String,
    fontSize: Int,
    onClick: () -> Unit,
    abilities: List<String>
) {
    val cvm = getViewModel<CheckRightsViewModel>()
    val avm = getViewModel<AuthViewModel>()
    val currUser = avm.sessionManager.currentUserFlow.collectAsState(initial = null)
    val currState = cvm.currState
    LaunchedEffect(key1 = Unit) {
        cvm.checkRights(currUser.value, abilities)
    }
    Log.d("currstate", currState.toString())
    if (currState) {
        Button(
            modifier = modifier,

            onClick = onClick,

            ) {
            Text(
                text = textString,
                fontFamily = Reenie,
                textAlign = TextAlign.Center,
                fontSize = fontSize.sp
            )
        }
    }

}