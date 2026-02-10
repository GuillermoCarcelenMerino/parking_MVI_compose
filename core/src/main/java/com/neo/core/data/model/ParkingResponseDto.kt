package com.neo.core.data.model

data class ParkingResponseDto(
    val id: Long,
    val name: String,
    val image: String,
    val direction: String,
    val available: Boolean,
    val longitud: Double,
    val latitud: Double
)