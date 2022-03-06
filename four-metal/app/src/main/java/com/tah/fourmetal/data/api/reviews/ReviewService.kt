package com.tah.fourmetal.data.api.reviews

import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewService {
    @GET("reviews")
    suspend fun getReviews(): NetworkResponse<ReviewResp, ReviewErrorResp>

    @GET("reviews/{rest_id}")
    suspend fun getReviewsOfRestaurant(@Path("rest_id") rest_id: Int): NetworkResponse<ReviewResp, ReviewErrorResp>
}