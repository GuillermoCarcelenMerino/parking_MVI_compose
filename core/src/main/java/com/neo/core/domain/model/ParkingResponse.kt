package com.neo.core.domain.model

data class ParkingResponse(
    val id: Long,
    val name: String,
    val image: String,
    val direction: String,
    val available: Boolean,
    val longitud: Double,
    val latitud: Double
)