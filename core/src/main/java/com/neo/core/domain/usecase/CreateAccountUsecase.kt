package com.neo.core.domain.usecase

import com.neo.core.data.request.AccountRequest
import com.neo.core.domain.repository.NeoParkRepository
import javax.inject.Inject
import javax.inject.Named

class CreateAccountUsecase @Inject constructor(
    @Named("NeoParkRepo")
    private val neoParkApi: NeoParkRepository
) {
    suspend fun createAccount(email: String, password: String, name: String) =
        neoParkApi.createAccount(
            AccountRequest(email, password, name)
        )
}