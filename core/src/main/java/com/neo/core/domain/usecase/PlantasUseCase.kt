package com.neo.core.domain.usecase

import com.neo.core.domain.repository.ParkingRepository
import javax.inject.Inject
import javax.inject.Named

class PlantasUseCase @Inject constructor(
    @Named("ParkingRepo")
    private val parkingRepository: ParkingRepository
) {
    suspend fun getPlantas(id: Long) = parkingRepository.getPlantParkings(id)
}