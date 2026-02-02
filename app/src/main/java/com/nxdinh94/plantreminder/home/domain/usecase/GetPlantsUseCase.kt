package com.nxdinh94.plantreminder.home.domain.usecase

import com.nxdinh94.plantreminder.home.domain.model.Plant
import com.nxdinh94.plantreminder.home.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow

class GetPlantsUseCase(
    private val repository: PlantRepository
) {
    operator fun invoke(): Flow<List<Plant>> {
        return repository.getAllPlants()
    }

    fun getById(id: Long): Flow<Plant?> {
        return repository.getPlantById(id)
    }

    fun getNeedingWater(): Flow<List<Plant>> {
        return repository.getPlantsNeedingWater()
    }
}
