package com.neo.core.domain.model

data class LoginResponse(
    val token : String,
    val refreshToken : String,
)
