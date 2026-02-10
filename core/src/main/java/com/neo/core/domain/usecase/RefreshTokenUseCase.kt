package com.neo.core.domain.usecase

import com.neo.core.data.request.RefreshTokenRequest
import com.neo.core.domain.repository.NeoParkRepository
import com.neo.core.utils.DataStore.DataStore
import com.neo.core.utils.DataStore.DataStoreKey
import com.neo.core.utils.cache.LocalCache
import com.neo.core.utils.cache.LocalKey
import javax.inject.Inject
import javax.inject.Named

class RefreshTokenUseCase @Inject constructor(
    @Named("NeoParkRepo")
    private val neoParkApi: NeoParkRepository,
    private val dataStore: DataStore,
    private val localCache: LocalCache
) {
    suspend operator fun invoke(): String? {
        val refreshToken =
            localCache.getString(LocalKey.REFRESH_TOKEN.name) ?: dataStore.getString(
                DataStoreKey.REFRESH_TOKEN.name
            ) ?: return null
        val response = neoParkApi.refresh(RefreshTokenRequest(refreshToken))

        return if (response.isSuccessful) {
            val token = response.body()?.token ?: null
            token?.let {
                localCache.saveString(LocalKey.TOKEN.name, token)
            }
            return token
        } else {
            null
        }

    }

}