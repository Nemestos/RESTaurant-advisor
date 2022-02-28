package com.tah.fourmetal.data.api.auth.register

data class RegisterResp(val token: String?, val user_id: Int?)
data class RegisterErrorResp(
    val message: String?,
    val errors: List<String>
)