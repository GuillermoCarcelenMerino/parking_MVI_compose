package com.neo.core.domain.mappers

import com.neo.core.data.model.ParkingResponseDto
import com.neo.core.domain.model.ParkingResponse
import javax.inject.Inject

class ParkingMapper @Inject constructor() :
    ResultMapper<ParkingResponseDto, ParkingResponse> {
    override fun map(data: ParkingResponseDto): ParkingResponse =
        ParkingResponse(
            data.id,
            data.name,
            data.image,
            data.direction,
            data.available,
            data.longitud,
            data.latitud
        )
}