package com.tah.fourmetal.data.models

data class Review(
    val id: Int?,
    val restaurant: Restaurant?,
    val user: User?,
    val description: String?,
    val grade: Float?

)

