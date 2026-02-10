package com.neo.core.domain.model

data class RefreshTokenResponse(
    val token : String,
    val refreshToken: String
)
