package com.tah.fourmetal.data.api.reviews

import com.tah.fourmetal.data.models.Review

data class ReviewResp(
    var data: List<Review>
)
data class ReviewErrorResp(
    var message: String?
)
