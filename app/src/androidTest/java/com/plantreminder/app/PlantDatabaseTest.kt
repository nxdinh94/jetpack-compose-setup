package com.nxdinh94.plantreminder

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nxdinh94.plantreminder.data.local.dao.PlantDao
import com.nxdinh94.plantreminder.data.local.database.PlantDatabase
import com.nxdinh94.plantreminder.data.local.entity.PlantEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlantDatabaseTest {

    private lateinit var database: PlantDatabase
    private lateinit var plantDao: PlantDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PlantDatabase::class.java
        ).allowMainThreadQueries().build()

        plantDao = database.plantDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndRetrievePlant() = runTest {
        val plant = PlantEntity(
            name = "Test Plant",
            species = "Test Species",
            wateringIntervalDays = 7,
            lastWateredDate = System.currentTimeMillis(),
            nextWateringDate = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L,
            notes = "Test Notes",
            imageUrl = ""
        )

        val id = plantDao.insertPlant(plant)
        val retrieved = plantDao.getPlantById(id).first()

        assertNotNull(retrieved)
        assertEquals("Test Plant", retrieved?.name)
        assertEquals("Test Species", retrieved?.species)
    }

    @Test
    fun getAllPlants() = runTest {
        val plant1 = PlantEntity(
            name = "Plant 1",
            species = "Species 1",
            wateringIntervalDays = 7,
            lastWateredDate = System.currentTimeMillis(),
            nextWateringDate = System.currentTimeMillis(),
            notes = "",
            imageUrl = ""
        )

        val plant2 = PlantEntity(
            name = "Plant 2",
            species = "Species 2",
            wateringIntervalDays = 14,
            lastWateredDate = System.currentTimeMillis(),
            nextWateringDate = System.currentTimeMillis(),
            notes = "",
            imageUrl = ""
        )

        plantDao.insertPlant(plant1)
        plantDao.insertPlant(plant2)

        val allPlants = plantDao.getAllPlants().first()

        assertEquals(2, allPlants.size)
    }

    @Test
    fun deletePlant() = runTest {
        val plant = PlantEntity(
            name = "Test Plant",
            species = "Test Species",
            wateringIntervalDays = 7,
            lastWateredDate = System.currentTimeMillis(),
            nextWateringDate = System.currentTimeMillis(),
            notes = "",
            imageUrl = ""
        )

        val id = plantDao.insertPlant(plant)
        val insertedPlant = plantDao.getPlantById(id).first()
        assertNotNull(insertedPlant)

        plantDao.deletePlant(insertedPlant!!)
        val deletedPlant = plantDao.getPlantById(id).first()

        assertNull(deletedPlant)
    }

    @Test
    fun updatePlant() = runTest {
        val plant = PlantEntity(
            name = "Original Name",
            species = "Species",
            wateringIntervalDays = 7,
            lastWateredDate = System.currentTimeMillis(),
            nextWateringDate = System.currentTimeMillis(),
            notes = "",
            imageUrl = ""
        )

        val id = plantDao.insertPlant(plant)
        val insertedPlant = plantDao.getPlantById(id).first()!!

        val updatedPlant = insertedPlant.copy(name = "Updated Name")
        plantDao.updatePlant(updatedPlant)

        val retrievedPlant = plantDao.getPlantById(id).first()

        assertEquals("Updated Name", retrievedPlant?.name)
    }
}
