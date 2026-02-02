package com.nxdinh94.plantreminder

import com.nxdinh94.plantreminder.data.local.entity.PlantEntity
import com.nxdinh94.plantreminder.data.mapper.PlantMapper.toDomain
import com.nxdinh94.plantreminder.data.mapper.PlantMapper.toDomainList
import com.nxdinh94.plantreminder.data.mapper.PlantMapper.toEntity
import com.nxdinh94.plantreminder.domain.model.Plant
import org.junit.Assert.assertEquals
import org.junit.Test

class PlantMapperTest {

    @Test
    fun `entity to domain mapping works correctly`() {
        val entity = PlantEntity(
            id = 1L,
            name = "Test Plant",
            species = "Test Species",
            wateringIntervalDays = 7,
            lastWateredDate = 1000L,
            nextWateringDate = 2000L,
            notes = "Test Notes",
            imageUrl = "http://test.com/image.png"
        )

        val domain = entity.toDomain()

        assertEquals(entity.id, domain.id)
        assertEquals(entity.name, domain.name)
        assertEquals(entity.species, domain.species)
        assertEquals(entity.wateringIntervalDays, domain.wateringIntervalDays)
        assertEquals(entity.lastWateredDate, domain.lastWateredDate)
        assertEquals(entity.nextWateringDate, domain.nextWateringDate)
        assertEquals(entity.notes, domain.notes)
        assertEquals(entity.imageUrl, domain.imageUrl)
    }

    @Test
    fun `domain to entity mapping works correctly`() {
        val domain = Plant(
            id = 1L,
            name = "Test Plant",
            species = "Test Species",
            wateringIntervalDays = 7,
            lastWateredDate = 1000L,
            nextWateringDate = 2000L,
            notes = "Test Notes",
            imageUrl = "http://test.com/image.png"
        )

        val entity = domain.toEntity()

        assertEquals(domain.id, entity.id)
        assertEquals(domain.name, entity.name)
        assertEquals(domain.species, entity.species)
        assertEquals(domain.wateringIntervalDays, entity.wateringIntervalDays)
        assertEquals(domain.lastWateredDate, entity.lastWateredDate)
        assertEquals(domain.nextWateringDate, entity.nextWateringDate)
        assertEquals(domain.notes, entity.notes)
        assertEquals(domain.imageUrl, entity.imageUrl)
    }

    @Test
    fun `list mapping works correctly`() {
        val entities = listOf(
            PlantEntity(1L, "Plant 1", "Species 1", 7, 1000L, 2000L, "", ""),
            PlantEntity(2L, "Plant 2", "Species 2", 14, 1000L, 2000L, "", "")
        )

        val domainList = entities.toDomainList()

        assertEquals(2, domainList.size)
        assertEquals("Plant 1", domainList[0].name)
        assertEquals("Plant 2", domainList[1].name)
    }
}
