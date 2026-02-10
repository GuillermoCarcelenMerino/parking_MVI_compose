package com.neo.core.domain.mappers

import com.neo.core.data.model.RefreshTokenResponseDto
import com.neo.core.domain.model.RefreshTokenResponse
import javax.inject.Inject

class RefreshTokkenMapper @Inject constructor() :
    ResultMapper<RefreshTokenResponseDto, RefreshTokenResponse> {
    override fun map(data: RefreshTokenResponseDto): RefreshTokenResponse =
        RefreshTokenResponse(
            data.token,
            data.refreshToken
        )
}