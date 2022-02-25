package com.tah.fourmetal.data.api.auth

data class RegisterBody(
    var login: String,
    var password: String,
    var email: String,
    var name: String,
    var firstname: String,
    var age: Int
)