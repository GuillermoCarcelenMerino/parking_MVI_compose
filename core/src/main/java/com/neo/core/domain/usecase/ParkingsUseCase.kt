package com.neo.core.domain.usecase

import com.neo.core.domain.repository.ParkingRepository
import javax.inject.Inject
import javax.inject.Named

class ParkingsUseCase @Inject constructor(
    @Named("ParkingRepo")
    private val parkingRepository: ParkingRepository
) {
    suspend fun getParkings() = parkingRepository.getParkings()
}