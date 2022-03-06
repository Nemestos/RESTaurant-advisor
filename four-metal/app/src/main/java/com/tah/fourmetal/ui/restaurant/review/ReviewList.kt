package com.tah.fourmetal.ui.restaurant.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tah.fourmetal.data.models.Review
import com.tah.fourmetal.ui.navigation.NavItem
import com.tah.fourmetal.ui.restaurant.RatingIndicator
import com.tah.fourmetal.ui.restaurant.RestaurantListItem
import com.tah.fourmetal.ui.viewmodels.ReviewsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ReviewList(rest_id: Int) {
    val reviewViewModel = getViewModel<ReviewsViewModel>()
    LaunchedEffect(key1 = Unit) {
        reviewViewModel.getReviewList(rest_id)
    }
    Column(modifier = Modifier.padding(10.dp)) {

        reviewViewModel.reviewsList.forEach { review ->
            ReviewItem(review)
            Spacer(modifier = Modifier.height(10.dp))

        }


    }
}

@Composable
fun ReviewItem(review: Review) {

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10))

            .background(Color.White)
            .padding(10.dp)
    ) {
        Column() {
            Row(horizontalArrangement = Arrangement.Center) {
                Text(text = review.user?.name.orEmpty())
            }
            Row() {
                RatingIndicator(colorTint = Color.Black, rating = review.grade.toString())
            }
            Row() {
                Text(text = review.description.orEmpty(), textAlign = TextAlign.Justify)
            }

        }
    }


}