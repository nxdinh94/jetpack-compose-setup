package com.nxdinh94.plantreminder.home.presentation.screen.home

import com.nxdinh94.plantreminder.home.domain.model.Plant

data class HomeUiState(
    val plants: List<Plant> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedPlant: Plant? = null,
    val isAddingPlant: Boolean = false
)

sealed interface HomeEvent {
    data object LoadPlants : HomeEvent
    data class SelectPlant(val plant: Plant) : HomeEvent
    data object ShowAddPlantDialog : HomeEvent
    data object DismissAddPlantDialog : HomeEvent
    data class AddPlant(
        val name: String,
        val species: String,
        val wateringIntervalDays: Int,
        val notes: String
    ) : HomeEvent
    data class DeletePlant(val plantId: Long) : HomeEvent
    data class WaterPlant(val plantId: Long) : HomeEvent
    data object DismissError : HomeEvent
}
