package com.nxdinh94.plantreminder.home.domain.usecase

import com.nxdinh94.plantreminder.home.domain.model.Plant
import com.nxdinh94.plantreminder.home.domain.repository.PlantRepository

class AddPlantUseCase(
    private val repository: PlantRepository
) {
    suspend operator fun invoke(plant: Plant): Result<Long> {
        return try {
            if (plant.name.isBlank()) {
                Result.failure(IllegalArgumentException("Plant name cannot be empty"))
            } else if (plant.wateringIntervalDays <= 0) {
                Result.failure(IllegalArgumentException("Watering interval must be positive"))
            } else {
                val id = repository.insertPlant(plant)
                Result.success(id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
