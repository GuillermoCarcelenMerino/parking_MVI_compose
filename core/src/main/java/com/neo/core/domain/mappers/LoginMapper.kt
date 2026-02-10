package com.neo.core.domain.mappers

import com.neo.core.data.model.LoginResponseDto
import com.neo.core.domain.model.LoginResponse
import javax.inject.Inject

class LoginMapper @Inject constructor (): ResultMapper<LoginResponseDto, LoginResponse> {
    override fun map(data: LoginResponseDto): LoginResponse = LoginResponse(data.token,data.refreshToken)
}