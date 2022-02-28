package com.tah.fourmetal.data.api.auth.login

import com.tah.fourmetal.data.models.User

data class LoginResp(val token: String?, val user_id: Int?)
data class LoginErrorResp(
    val message: String?,
    val errors: List<String?>?
)