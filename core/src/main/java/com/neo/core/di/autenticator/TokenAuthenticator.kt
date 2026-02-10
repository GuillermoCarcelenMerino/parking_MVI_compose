package com.neo.core.di.autenticator

import com.neo.core.domain.usecase.RefreshTokenUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val refreshTokenUseCase: RefreshTokenUseCase
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val token = refreshTokenUseCase() ?: return@runBlocking null

            response.request.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        }
    }


}
