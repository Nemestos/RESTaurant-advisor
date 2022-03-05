package com.tah.fourmetal.data.api.restaurants

data class RestaurantUpdateBody(
    var name: String?,
    var description: String?,
    var grade: Float?,
    var image_url: String?,
    var localization: String?,
    var phone_number: String?,
    var website: String?,
    var hours: String?
)
