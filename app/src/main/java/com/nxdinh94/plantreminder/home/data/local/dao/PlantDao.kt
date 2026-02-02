package com.nxdinh94.plantreminder.home.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nxdinh94.plantreminder.home.data.local.entity.PlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants ORDER BY name ASC")
    fun getAllPlants(): Flow<List<PlantEntity>>

    @Query("SELECT * FROM plants WHERE id = :id")
    fun getPlantById(id: Long): Flow<PlantEntity?>

    @Query("SELECT * FROM plants WHERE nextWateringDate <= :currentDate ORDER BY nextWateringDate ASC")
    fun getPlantsNeedingWater(currentDate: Long): Flow<List<PlantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plant: PlantEntity): Long

    @Update
    suspend fun updatePlant(plant: PlantEntity)

    @Delete
    suspend fun deletePlant(plant: PlantEntity)

    @Query("DELETE FROM plants WHERE id = :id")
    suspend fun deletePlantById(id: Long)

    @Query("SELECT COUNT(*) FROM plants")
    suspend fun getPlantCount(): Int
}
