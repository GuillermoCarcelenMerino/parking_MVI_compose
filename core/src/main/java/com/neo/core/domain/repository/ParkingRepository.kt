package com.neo.core.domain.repository

import com.neo.core.data.network.NetworkFailure
import com.neo.core.data.network.Result
import com.neo.core.data.request.AccountRequest
import com.neo.core.data.request.LoginRequest
import com.neo.core.domain.model.CreateAccountRespose
import com.neo.core.domain.model.LoginResponse
import com.neo.core.domain.model.ParkingResponse
import com.neo.core.domain.model.PlantaResponse
import com.neo.core.domain.model.PlazaResponse

interface ParkingRepository {

    suspend fun getParkings(): Result<List<ParkingResponse>, NetworkFailure>

    suspend fun getPlantParkings(id: Long): Result<List<PlantaResponse>, NetworkFailure>

    suspend fun getPlazasParking(id: Long): Result<List<PlazaResponse>, NetworkFailure>
}