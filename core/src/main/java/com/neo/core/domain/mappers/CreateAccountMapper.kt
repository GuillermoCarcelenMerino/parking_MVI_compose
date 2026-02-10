package com.neo.core.domain.mappers

import com.neo.core.data.model.CreateAccountResposeDto
import com.neo.core.data.model.LoginResponseDto
import com.neo.core.domain.model.CreateAccountRespose
import com.neo.core.domain.model.LoginResponse
import javax.inject.Inject

class CreateAccountMapper @Inject constructor() :
    ResultMapper<CreateAccountResposeDto, CreateAccountRespose> {
    override fun map(data: CreateAccountResposeDto): CreateAccountRespose =
        CreateAccountRespose(data.id, data.name, data.email)
}