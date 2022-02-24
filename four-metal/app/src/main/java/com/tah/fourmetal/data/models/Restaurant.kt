package com.tah.fourmetal.data.models

data class Restaurant(
    var id: Int,
    var name: String,
    var description: String,
    var grade: Float,
    var localization: String,
    var phone_number: String,
    var website: String,
    var hours: String
)