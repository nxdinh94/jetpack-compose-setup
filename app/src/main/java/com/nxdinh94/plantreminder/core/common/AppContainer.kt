package com.nxdinh94.plantreminder.core.common

import android.app.Application
import com.nxdinh94.plantreminder.home.data.local.database.PlantDatabase
import com.nxdinh94.plantreminder.home.data.repository.PlantRepositoryImpl
import com.nxdinh94.plantreminder.home.domain.repository.PlantRepository
import com.nxdinh94.plantreminder.home.domain.usecase.AddPlantUseCase
import com.nxdinh94.plantreminder.home.domain.usecase.DeletePlantUseCase
import com.nxdinh94.plantreminder.home.domain.usecase.GetPlantsUseCase


object AppContainer {
    private lateinit var application: Application

    val database: PlantDatabase
        get() = PlantDatabase.getInstance(application)

    val plantRepository: PlantRepository
        get() = PlantRepositoryImpl(database.plantDao())

    val getPlantsUseCase: GetPlantsUseCase
        get() = GetPlantsUseCase(plantRepository)

    val addPlantUseCase: AddPlantUseCase
        get() = AddPlantUseCase(plantRepository)

    val deletePlantUseCase: DeletePlantUseCase
        get() = DeletePlantUseCase(plantRepository)

    fun initialize(app: Application) {
        application = app
    }
}
