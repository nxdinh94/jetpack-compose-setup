package com.nxdinh94.plantreminder.home.data.mapper

import com.nxdinh94.plantreminder.home.data.local.entity.PlantEntity
import com.nxdinh94.plantreminder.home.domain.model.Plant

object PlantMapper {
    fun PlantEntity.toDomain(): Plant {
        return Plant(
            id = id,
            name = name,
            species = species,
            wateringIntervalDays = wateringIntervalDays,
            lastWateredDate = lastWateredDate,
            nextWateringDate = nextWateringDate,
            notes = notes,
            imageUrl = imageUrl
        )
    }

    fun Plant.toEntity(): PlantEntity {
        return PlantEntity(
            id = id,
            name = name,
            species = species,
            wateringIntervalDays = wateringIntervalDays,
            lastWateredDate = lastWateredDate,
            nextWateringDate = nextWateringDate,
            notes = notes,
            imageUrl = imageUrl
        )
    }

    fun List<PlantEntity>.toDomainList(): List<Plant> {
        return map { it.toDomain() }
    }
}
