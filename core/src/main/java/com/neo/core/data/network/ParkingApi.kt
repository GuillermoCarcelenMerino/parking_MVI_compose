package com.neo.core.data.network

import com.neo.core.data.model.ParkingResponseDto
import com.neo.core.data.model.PlantaResponseDto
import com.neo.core.data.model.PlazaResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface ParkingApi {


    @GET("/api/v1/parkings")
    suspend fun getParkings(): Response<List<ParkingResponseDto>>

    @GET("/api/v1/plantas/Parking/{id}")
    suspend fun getPlantsParking(@Path("id") id: Long): Response<List<PlantaResponseDto>>

    @GET("/api/v1/plazas/Planta/{id}")
    suspend fun getPlazasPlant(@Path("id") id: Long): Response<List<PlazaResponseDto>>

    @GET("/api/v1/auth/refresh")
    suspend fun getPlazasPlant(@Body refreshToken : String): Response<List<PlazaResponseDto>>



}