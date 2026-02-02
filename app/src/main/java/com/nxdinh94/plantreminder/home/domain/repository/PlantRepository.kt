package com.nxdinh94.plantreminder.home.domain.repository

import com.nxdinh94.plantreminder.home.domain.model.Plant
import kotlinx.coroutines.flow.Flow

interface PlantRepository {
    fun getAllPlants(): Flow<List<Plant>>
    fun getPlantById(id: Long): Flow<Plant?>
    suspend fun insertPlant(plant: Plant): Long
    suspend fun updatePlant(plant: Plant)
    suspend fun deletePlant(plant: Plant)
    suspend fun deletePlantById(id: Long)
    fun getPlantsNeedingWater(): Flow<List<Plant>>
}
