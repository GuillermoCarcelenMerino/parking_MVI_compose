package com.neo.core.domain.model

data class PlazaResponse(
    val id: Long,
    val code: String,
    val available: Boolean,
    val price: Double
)