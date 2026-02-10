package com.neo.core.data.network

import com.neo.core.data.model.CreateAccountResposeDto
import com.neo.core.data.model.LoginResponseDto
import com.neo.core.data.model.RefreshTokenResponseDto
import com.neo.core.data.request.AccountRequest
import com.neo.core.data.request.LoginRequest
import com.neo.core.data.request.RefreshTokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NeoParkApi {

    @POST("/api/v1/auth")
    suspend fun doLogin(@Body loginRequest: LoginRequest): Response<LoginResponseDto>

    @POST("/api/v1/users")
    suspend fun createAccount(@Body data: AccountRequest): Response<CreateAccountResposeDto>

    @POST("/api/v1/auth/refreshToken")
    suspend fun refreshToken(@Body refreshTokenRequest : RefreshTokenRequest): Response<RefreshTokenResponseDto>

}