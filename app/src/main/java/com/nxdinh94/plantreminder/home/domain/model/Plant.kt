package com.nxdinh94.plantreminder.home.domain.model

data class Plant(
    val id: Long = 0,
    val name: String,
    val species: String,
    val wateringIntervalDays: Int,
    val lastWateredDate: Long,
    val nextWateringDate: Long,
    val notes: String = "",
    val imageUrl: String = ""
)
