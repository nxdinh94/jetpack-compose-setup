package com.nxdinh94.plantreminder.home.domain.usecase

import com.nxdinh94.plantreminder.home.domain.repository.PlantRepository

class DeletePlantUseCase(
    private val repository: PlantRepository
) {
    suspend operator fun invoke(plantId: Long): Result<Unit> {
        return try {
            repository.deletePlantById(plantId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
