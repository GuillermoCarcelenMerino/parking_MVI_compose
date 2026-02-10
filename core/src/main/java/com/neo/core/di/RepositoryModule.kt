package com.neo.core.di

import com.neo.core.data.repository.NeoParkRepositoryImpl
import com.neo.core.data.repository.ParkingRepositoryImpl
import com.neo.core.domain.repository.NeoParkRepository
import com.neo.core.domain.repository.ParkingRepository
import com.neo.core.utils.DataStore.DataStore
import com.neo.core.utils.DataStore.DataStoreImp
import com.neo.core.utils.cache.LocalCache
import com.neo.core.utils.cache.LocalCacheImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@InstallIn(SingletonComponent::class)
@Module(includes = [ApiModule::class])
abstract class RepositoryModule {

    @Binds
    @Named("NeoParkRepo")
    abstract fun providesNeoParkRepository(
        neoParkApi: NeoParkRepositoryImpl,
    ): NeoParkRepository

    @Binds
    @Named("ParkingRepo")
    abstract fun providesParkingRepository(
        parkingApi: ParkingRepositoryImpl,
    ): ParkingRepository

    @Binds
    abstract fun providesLocalCache(
        localCacheImp: LocalCacheImp
    ): LocalCache

    @Binds
    abstract fun providesDataStore(
        dataStoreImp: DataStoreImp
    ): DataStore
}
