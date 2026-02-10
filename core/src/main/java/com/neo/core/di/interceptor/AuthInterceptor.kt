package com.neo.core.di.interceptor

import com.neo.core.utils.cache.LocalCache
import com.neo.core.utils.cache.LocalKey
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val localCache: LocalCache
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = localCache.getString(LocalKey.TOKEN.name) ?: ""
        val request = chain.request().newBuilder().apply {
            if (token.isNotEmpty()) {
                addHeader("Authorization", "Bearer $token")
            }
        }.build()

        return chain.proceed(request)
    }
}