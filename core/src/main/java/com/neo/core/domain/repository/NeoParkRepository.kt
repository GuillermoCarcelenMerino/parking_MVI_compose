package com.neo.core.domain.repository

import com.neo.core.data.model.RefreshTokenResponseDto
import com.neo.core.data.network.NetworkFailure
import com.neo.core.data.network.Result
import com.neo.core.data.request.AccountRequest
import com.neo.core.data.request.LoginRequest
import com.neo.core.data.request.RefreshTokenRequest
import com.neo.core.domain.model.CreateAccountRespose
import com.neo.core.domain.model.LoginResponse
import com.neo.core.domain.model.ParkingResponse
import com.neo.core.domain.model.PlantaResponse
import com.neo.core.domain.model.PlazaResponse
import com.neo.core.domain.model.RefreshTokenResponse
import retrofit2.Response

interface NeoParkRepository {

    suspend fun doLogin(data: LoginRequest): Result<LoginResponse, NetworkFailure>

    suspend fun createAccount(data: AccountRequest): Result<CreateAccountRespose, NetworkFailure>

    suspend fun refresh(data: RefreshTokenRequest): Response<RefreshTokenResponseDto>

}