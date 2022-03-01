package com.tah.fourmetal.ui.restaurant

import android.util.Log
import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.tah.fourmetal.R
import com.tah.fourmetal.data.models.Restaurant
import com.tah.fourmetal.ui.Utils

@Composable
fun RestaurantListItem(rest: Restaurant, onClick: (Restaurant) -> Unit) {
    val localization = rest.localization?.let { Utils.toLocalization(it) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(rest) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .background(Color.White)
                .padding(10.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.45f)
                ) {
                    Row(
                    ) {


                        Text(
                            text = rest.name.orEmpty(),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h5
                        )


                    }
                    Row() {
                        Icon(
                            painterResource(R.drawable.ic_location),
                            contentDescription = "location icon"
                        )
                        if (localization?.city != null) {
                            Text(
                                fontStyle = FontStyle.Italic,
                                text = "${localization?.city} ${localization.postalCode}",
                                maxLines = 2,
                                overflow = TextOverflow.Clip
                            )
                        }


                    }
                    Row(){
                        RatingIndicator(colorTint = Color.Gray, rating = rest.grade.toString())

                    }
                    Text(text = rest.description.orEmpty(), maxLines = 4)
                }
                Log.d("url:", rest.image_url.orEmpty());
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(300.dp)
                        .clip(RoundedCornerShape(10)),
                    contentScale = ContentScale.Crop,
                    painter = rememberImagePainter(
                        data = rest.image_url,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = "burger fond"

                )

            }
        }
    }
}