package com.neo.core.domain.mappers

import com.neo.core.data.model.PlazaResponseDto
import com.neo.core.domain.model.PlazaResponse
import javax.inject.Inject

class PlazaMapper @Inject constructor() :
    ResultMapper<PlazaResponseDto, PlazaResponse> {
    override fun map(data: PlazaResponseDto): PlazaResponse =
        PlazaResponse(
            data.id,
            data.code,
            data.available,
            data.price
        )
}