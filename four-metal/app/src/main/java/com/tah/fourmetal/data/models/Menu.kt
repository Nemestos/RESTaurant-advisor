package com.tah.fourmetal.data.models

data class Menu(
    var id: Int,
    var name: String,
    var restaurant: Restaurant,
    var description: String,
    var price: Float
)
