package com.neo.core.di

import com.neo.core.data.network.NeoParkApi
import com.neo.core.data.network.ParkingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    @Named("userRetrofit")
    fun providesApiRetrofit( @Named("userApi")retrofit: Retrofit) : NeoParkApi =
        retrofit.create(NeoParkApi::class.java)

    @Provides
    @Singleton
    @Named("parkingRetrofit")
    fun providesParkingRetrofit(@Named("parkingApi") retrofit: Retrofit) : ParkingApi =
        retrofit.create(ParkingApi::class.java)
}