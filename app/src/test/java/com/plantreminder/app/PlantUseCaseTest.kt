package com.nxdinh94.plantreminder

import com.nxdinh94.plantreminder.domain.model.Plant
import com.nxdinh94.plantreminder.domain.repository.PlantRepository
import com.nxdinh94.plantreminder.domain.usecase.AddPlantUseCase
import com.nxdinh94.plantreminder.domain.usecase.DeletePlantUseCase
import com.nxdinh94.plantreminder.domain.usecase.GetPlantsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class PlantUseCaseTest {

    private lateinit var fakeRepository: FakePlantRepository
    private lateinit var getPlantsUseCase: GetPlantsUseCase
    private lateinit var addPlantUseCase: AddPlantUseCase
    private lateinit var deletePlantUseCase: DeletePlantUseCase

    @Before
    fun setup() {
        fakeRepository = FakePlantRepository()
        getPlantsUseCase = GetPlantsUseCase(fakeRepository)
        addPlantUseCase = AddPlantUseCase(fakeRepository)
        deletePlantUseCase = DeletePlantUseCase(fakeRepository)
    }

    @Test
    fun `add plant with valid data returns success`() = runTest {
        val plant = Plant(
            name = "Test Plant",
            species = "Test Species",
            wateringIntervalDays = 7,
            lastWateredDate = System.currentTimeMillis(),
            nextWateringDate = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L
        )

        val result = addPlantUseCase(plant)

        assertTrue(result.isSuccess)
        assertEquals(1L, result.getOrNull())
    }

    @Test
    fun `add plant with empty name returns failure`() = runTest {
        val plant = Plant(
            name = "",
            species = "Test Species",
            wateringIntervalDays = 7,
            lastWateredDate = System.currentTimeMillis(),
            nextWateringDate = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L
        )

        val result = addPlantUseCase(plant)

        assertTrue(result.isFailure)
    }

    @Test
    fun `add plant with invalid interval returns failure`() = runTest {
        val plant = Plant(
            name = "Test Plant",
            species = "Test Species",
            wateringIntervalDays = 0,
            lastWateredDate = System.currentTimeMillis(),
            nextWateringDate = System.currentTimeMillis()
        )

        val result = addPlantUseCase(plant)

        assertTrue(result.isFailure)
    }

    @Test
    fun `delete plant returns success`() = runTest {
        val result = deletePlantUseCase(1L)

        assertTrue(result.isSuccess)
    }
}

class FakePlantRepository : PlantRepository {
    private val plants = mutableListOf<Plant>()
    private var nextId = 1L

    override fun getAllPlants(): Flow<List<Plant>> = flowOf(plants.toList())

    override fun getPlantById(id: Long): Flow<Plant?> = flowOf(plants.find { it.id == id })

    override suspend fun insertPlant(plant: Plant): Long {
        val newPlant = plant.copy(id = nextId++)
        plants.add(newPlant)
        return newPlant.id
    }

    override suspend fun updatePlant(plant: Plant) {
        val index = plants.indexOfFirst { it.id == plant.id }
        if (index >= 0) {
            plants[index] = plant
        }
    }

    override suspend fun deletePlant(plant: Plant) {
        plants.removeAll { it.id == plant.id }
    }

    override suspend fun deletePlantById(id: Long) {
        plants.removeAll { it.id == id }
    }

    override fun getPlantsNeedingWater(): Flow<List<Plant>> = flowOf(
        plants.filter { it.nextWateringDate <= System.currentTimeMillis() }
    )
}
