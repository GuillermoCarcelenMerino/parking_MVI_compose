package com.neo.core.domain.mappers

import com.neo.core.data.model.PlantaResponseDto
import com.neo.core.domain.model.PlantaResponse
import javax.inject.Inject

class PlantaMapper @Inject constructor() :
    ResultMapper<PlantaResponseDto, PlantaResponse> {
    override fun map(data: PlantaResponseDto): PlantaResponse =
        PlantaResponse(
            data.id,
            data.name,
        )
}