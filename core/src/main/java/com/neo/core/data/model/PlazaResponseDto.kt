package com.neo.core.data.model

data class PlazaResponseDto(
    val id: Long,
    val code: String,
    val available: Boolean,
    val price: Double
)