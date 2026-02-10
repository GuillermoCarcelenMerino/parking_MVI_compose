package com.neo.parking.feature.parking.vm

import com.neo.core.domain.model.ParkingResponse

data class UiState(
    val parkings: List<ParkingResponse> = emptyList()
)

sealed class UiIntent {
    object GETPARKINGSINTENT : UiIntent()
    class SEARCHPARKINGS(data: String) : UiIntent()
}

sealed class Event {

}