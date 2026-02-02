package com.nxdinh94.plantreminder.home.data.repository

import com.nxdinh94.plantreminder.home.data.local.dao.PlantDao
import com.nxdinh94.plantreminder.home.data.mapper.PlantMapper.toDomain
import com.nxdinh94.plantreminder.home.data.mapper.PlantMapper.toDomainList
import com.nxdinh94.plantreminder.home.data.mapper.PlantMapper.toEntity
import com.nxdinh94.plantreminder.home.domain.model.Plant
import com.nxdinh94.plantreminder.home.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlantRepositoryImpl(
    private val plantDao: PlantDao
) : PlantRepository {

    override fun getAllPlants(): Flow<List<Plant>> {
        return plantDao.getAllPlants().map { entities ->
            entities.toDomainList()
        }
    }

    override fun getPlantById(id: Long): Flow<Plant?> {
        return plantDao.getPlantById(id).map { entity ->
            entity?.toDomain()
        }
    }

    override suspend fun insertPlant(plant: Plant): Long {
        return plantDao.insertPlant(plant.toEntity())
    }

    override suspend fun updatePlant(plant: Plant) {
        plantDao.updatePlant(plant.toEntity())
    }

    override suspend fun deletePlant(plant: Plant) {
        plantDao.deletePlant(plant.toEntity())
    }

    override suspend fun deletePlantById(id: Long) {
        plantDao.deletePlantById(id)
    }

    override fun getPlantsNeedingWater(): Flow<List<Plant>> {
        return plantDao.getPlantsNeedingWater(System.currentTimeMillis()).map { entities ->
            entities.toDomainList()
        }
    }
}
