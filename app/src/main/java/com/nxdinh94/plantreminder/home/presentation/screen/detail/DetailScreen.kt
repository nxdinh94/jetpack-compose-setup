package com.nxdinh94.plantreminder.home.presentation.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.nxdinh94.plantreminder.R
import com.nxdinh94.plantreminder.core.common.AppContainer
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Navigation key for Plant Detail Screen
 */
@Serializable
data class PlantDetailRoute(val plantId: Long) : NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantDetailScreen(
    plantId: Long,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val getPlantsUseCase = AppContainer.getPlantsUseCase

    val plantFlow = remember { getPlantsUseCase.getById(plantId) }
    val plant by plantFlow.collectAsState(initial = null)

    val dateFormat = remember { SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(plant?.name ?: stringResource(R.string.plant_details)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            plant?.let {
                FloatingActionButton(
                    onClick = { /* Water plant logic */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.WaterDrop,
                        contentDescription = stringResource(R.string.water_plant)
                    )
                }
            }
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            plant?.let { currentPlant ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    DetailCard(
                        title = stringResource(R.string.plant_info),
                        content = {
                            DetailRow(
                                label = stringResource(R.string.species),
                                value = currentPlant.species
                            )
                            DetailRow(
                                label = stringResource(R.string.watering_interval_days),
                                value = currentPlant.wateringIntervalDays.toString()
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DetailCard(
                        title = stringResource(R.string.watering_schedule),
                        content = {
                            DetailRow(
                                label = stringResource(R.string.last_watered),
                                value = dateFormat.format(Date(currentPlant.lastWateredDate))
                            )
                            DetailRow(
                                label = stringResource(R.string.next_watering),
                                value = dateFormat.format(Date(currentPlant.nextWateringDate))
                            )
                        }
                    )

                    if (currentPlant.notes.isNotBlank()) {
                        Spacer(modifier = Modifier.height(16.dp))

                        DetailCard(
                            title = stringResource(R.string.notes),
                            content = {
                                Text(
                                    text = currentPlant.notes,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        )
                    }
                }
            } ?: run {
                Text(
                    text = stringResource(R.string.plant_not_found),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun DetailCard(
    title: String,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
