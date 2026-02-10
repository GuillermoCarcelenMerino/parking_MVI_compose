package com.neo.core.domain.usecase

import com.neo.core.data.request.LoginRequest
import com.neo.core.domain.repository.NeoParkRepository
import javax.inject.Inject
import javax.inject.Named

class LoginUseCase @Inject constructor(
    @Named("NeoParkRepo")
    private val neoParkApi: NeoParkRepository,
) {
    suspend fun doLogin(email: String, password: String) =
        neoParkApi.doLogin(LoginRequest(email, password))
    
}