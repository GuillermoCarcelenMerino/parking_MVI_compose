package com.neo.core.di

import com.neo.core.BuildConfig
import com.neo.core.data.network.ApiConfig
import com.neo.core.di.autenticator.TokenAuthenticator
import com.neo.core.di.interceptor.AuthInterceptor
import com.neo.core.utils.buildWithFactories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    //Interceptor de loggers para llamadas
    @Singleton
    @Provides
    @Named("basicClient")
    fun providesLogger() =
        OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS) // tiempo para conectar
            .readTimeout(30, TimeUnit.SECONDS)    // tiempo para leer respuesta
            .writeTimeout(30, TimeUnit.SECONDS).addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }).build()

    @Singleton
    @Provides
    @Named("authClient")
    fun providesAuthenticatedClient(
        tokenInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG)
                        HttpLoggingInterceptor.Level.BODY
                    else
                        HttpLoggingInterceptor.Level.NONE
                }
            )
            .addInterceptor(tokenInterceptor)
            .authenticator(tokenAuthenticator)
            .build()


    @Singleton
    @Provides
    @Named("userApi")
    fun providesNeoApi(@Named("basicClient") client: OkHttpClient) =
        Retrofit.Builder().baseUrl(ApiConfig.NEOPARK_BASE_URL).client(client).buildWithFactories()

    @Singleton
    @Provides
    @Named("parkingApi")
    fun providesParkingApi(@Named("authClient") client: OkHttpClient) =
        Retrofit.Builder().baseUrl(ApiConfig.NEOPARK_BASE_URL).client(client).buildWithFactories()
}