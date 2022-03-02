package com.tah.fourmetal.data.api.auth.login

import com.tah.fourmetal.data.models.User

data class AbilitiesResp(val message: Boolean)
data class AbilitiesErrorResp(
    val message: String?,
    val errors: List<String?>?
)