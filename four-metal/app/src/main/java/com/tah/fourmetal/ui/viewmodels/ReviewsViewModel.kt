package com.tah.fourmetal.ui.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.haroldadmin.cnradapter.NetworkResponse
import com.tah.fourmetal.data.api.RetrofitInstance
import com.tah.fourmetal.data.api.restaurants.RestaurantService
import com.tah.fourmetal.data.api.reviews.ReviewService
import com.tah.fourmetal.data.models.Review

class ReviewsViewModel @SuppressLint("StaticFieldLeak") constructor(
    val retrofitInstance: RetrofitInstance,
    val context: Context
) : ViewModel() {
    val reviewsList = mutableStateListOf<Review>()

    suspend fun getReviewList(rest_id: Int) {

        val retrofit = retrofitInstance.getInst().create(ReviewService::class.java)
        when (val reviews = retrofit.getReviewsOfRestaurant(rest_id)) {

            is NetworkResponse.Success -> {
                reviewsList.clear()
                reviewsList.addAll(reviews.body.data)

            }
            is NetworkResponse.Error -> {
                Toast.makeText(
                    context,
                    "erreur lors de la recuperation des menus",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }
}