package com.nxdinh94.plantreminder.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class PlantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val species: String,
    val wateringIntervalDays: Int,
    val lastWateredDate: Long,
    val nextWateringDate: Long,
    val notes: String,
    val imageUrl: String
)
