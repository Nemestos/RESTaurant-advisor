package com.tah.fourmetal.data.api.menus

import com.tah.fourmetal.data.models.Restaurant

data class MenuUpdateBody(
    var name: String?,
    var description: String?,
    var price: Float?
)
