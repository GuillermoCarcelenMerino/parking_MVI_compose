package com.neo.core.data.repository

import com.neo.core.data.network.NetworkFailure
import com.neo.core.data.network.ParkingApi
import com.neo.core.data.network.Result
import com.neo.core.domain.mappers.ParkingMapper
import com.neo.core.domain.mappers.PlantaMapper
import com.neo.core.domain.mappers.PlazaMapper
import com.neo.core.domain.model.ParkingResponse
import com.neo.core.domain.model.PlantaResponse
import com.neo.core.domain.model.PlazaResponse
import com.neo.core.domain.repository.ParkingRepository
import com.neo.core.utils.requestHandlerList
import javax.inject.Inject
import javax.inject.Named

class ParkingRepositoryImpl @Inject constructor(
    @Named("parkingRetrofit") private val api: ParkingApi,
    private val parkingMapper: ParkingMapper,
    private val PlantMapper: PlantaMapper,
    private val PlazaMapper: PlazaMapper,
) : ParkingRepository {

    override suspend fun getParkings(): Result<List<ParkingResponse>, NetworkFailure> =
        requestHandlerList(
            request = {
                api.getParkings()
            },
            mapper = parkingMapper
        )


    override suspend fun getPlantParkings(id: Long): Result<List<PlantaResponse>, NetworkFailure> =
        requestHandlerList(
            request = {
                api.getPlantsParking(id)
            },
            mapper = PlantMapper
        )

    override suspend fun getPlazasParking(id: Long): Result<List<PlazaResponse>, NetworkFailure> =
        requestHandlerList(
            request = {
                api.getPlazasPlant(id)
            },
            mapper = PlazaMapper
        )


}