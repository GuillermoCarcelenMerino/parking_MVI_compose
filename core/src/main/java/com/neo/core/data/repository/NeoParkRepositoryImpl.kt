package com.neo.core.data.repository

import com.neo.core.data.model.RefreshTokenResponseDto
import com.neo.core.data.network.NeoParkApi
import com.neo.core.data.network.NetworkFailure
import com.neo.core.data.network.Result
import com.neo.core.data.request.AccountRequest
import com.neo.core.data.request.LoginRequest
import com.neo.core.data.request.RefreshTokenRequest
import com.neo.core.domain.mappers.CreateAccountMapper
import com.neo.core.domain.mappers.LoginMapper
import com.neo.core.domain.mappers.RefreshTokkenMapper
import com.neo.core.domain.model.CreateAccountRespose
import com.neo.core.domain.model.LoginResponse
import com.neo.core.domain.model.RefreshTokenResponse
import com.neo.core.domain.repository.NeoParkRepository
import com.neo.core.utils.requestHandler
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

class NeoParkRepositoryImpl @Inject constructor(
    @Named("userRetrofit") private val api: NeoParkApi,
    private val mapper: LoginMapper,
    private val mapperAccount: CreateAccountMapper,
    private val refreshTokkenMapper: RefreshTokkenMapper,
) : NeoParkRepository {
    override suspend fun doLogin(
        data: LoginRequest
    ): Result<LoginResponse, NetworkFailure> =
        requestHandler(request = { api.doLogin(data) }, mapper = mapper)

    override suspend fun createAccount(data: AccountRequest): Result<CreateAccountRespose, NetworkFailure> =
        requestHandler(request = {
            api.createAccount(data)
        }, mapper = mapperAccount)

    override suspend fun refresh(data: RefreshTokenRequest): Response<RefreshTokenResponseDto> =
        api.refreshToken(data)




}