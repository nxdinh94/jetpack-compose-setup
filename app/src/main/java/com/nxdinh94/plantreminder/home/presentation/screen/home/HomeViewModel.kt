package com.nxdinh94.plantreminder.home.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nxdinh94.plantreminder.home.domain.model.Plant
import com.nxdinh94.plantreminder.home.domain.usecase.AddPlantUseCase
import com.nxdinh94.plantreminder.home.domain.usecase.DeletePlantUseCase
import com.nxdinh94.plantreminder.home.domain.usecase.GetPlantsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPlantsUseCase: GetPlantsUseCase,
    private val addPlantUseCase: AddPlantUseCase,
    private val deletePlantUseCase: DeletePlantUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadPlants()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadPlants -> loadPlants()
            is HomeEvent.SelectPlant -> selectPlant(event.plant)
            is HomeEvent.ShowAddPlantDialog -> showAddPlantDialog()
            is HomeEvent.DismissAddPlantDialog -> dismissAddPlantDialog()
            is HomeEvent.AddPlant -> addPlant(
                event.name,
                event.species,
                event.wateringIntervalDays,
                event.notes
            )
            is HomeEvent.DeletePlant -> deletePlant(event.plantId)
            is HomeEvent.WaterPlant -> waterPlant(event.plantId)
            is HomeEvent.DismissError -> dismissError()
        }
    }

    private fun loadPlants() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getPlantsUseCase()
                .catch { e ->
                    _uiState.update {
                        it.copy(isLoading = false, error = e.message)
                    }
                }
                .collect { plants ->
                    _uiState.update {
                        it.copy(plants = plants, isLoading = false, error = null)
                    }
                }
        }
    }

    private fun selectPlant(plant: Plant) {
        _uiState.update { it.copy(selectedPlant = plant) }
    }

    private fun showAddPlantDialog() {
        _uiState.update { it.copy(isAddingPlant = true) }
    }

    private fun dismissAddPlantDialog() {
        _uiState.update { it.copy(isAddingPlant = false) }
    }

    private fun addPlant(
        name: String,
        species: String,
        wateringIntervalDays: Int,
        notes: String
    ) {
        viewModelScope.launch {
            val currentTime = System.currentTimeMillis()
            val nextWateringDate = currentTime + (wateringIntervalDays * 24 * 60 * 60 * 1000L)
            val plant = Plant(
                name = name,
                species = species,
                wateringIntervalDays = wateringIntervalDays,
                lastWateredDate = currentTime,
                nextWateringDate = nextWateringDate,
                notes = notes
            )

            addPlantUseCase(plant)
                .onSuccess {
                    _uiState.update { it.copy(isAddingPlant = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message) }
                }
        }
    }

    private fun deletePlant(plantId: Long) {
        viewModelScope.launch {
            deletePlantUseCase(plantId)
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message) }
                }
        }
    }

    private fun waterPlant(plantId: Long) {
        viewModelScope.launch {
            _uiState.value.plants.find { it.id == plantId }?.let { plant ->
                val currentTime = System.currentTimeMillis()
                val nextWateringDate = currentTime + (plant.wateringIntervalDays * 24 * 60 * 60 * 1000L)
                val updatedPlant = plant.copy(
                    lastWateredDate = currentTime,
                    nextWateringDate = nextWateringDate
                )
                // Update plant in database through repository
                // This would be implemented via an UpdatePlantUseCase
            }
        }
    }

    private fun dismissError() {
        _uiState.update { it.copy(error = null) }
    }

    class Factory(
        private val getPlantsUseCase: GetPlantsUseCase,
        private val addPlantUseCase: AddPlantUseCase,
        private val deletePlantUseCase: DeletePlantUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(getPlantsUseCase, addPlantUseCase, deletePlantUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
