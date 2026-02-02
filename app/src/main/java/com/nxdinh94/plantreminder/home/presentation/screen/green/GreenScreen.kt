package com.nxdinh94.plantreminder.home.presentation.screen.green

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nxdinh94.plantreminder.core.navigation.TopLevelRoute
import kotlinx.serialization.Serializable
import com.nxdinh94.plantreminder.R
@Serializable
data object TimeLineRoute : TopLevelRoute {
    override val icon: Int = R.drawable.timeline
    override val name: Int = R.string.nav_item_timeline
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TimeLineScreen() {
    val plants = remember {
        listOf(
            PlantUiModel(
                name = "Monstera Deliciosa",
                scientificName = "Monstera",
                status = PlantStatus.Healthy(tasks = 1, isDueToday = true)
            ),
            PlantUiModel(
                name = "Snake Plant",
                scientificName = "Sansevieria",
                status = PlantStatus.Overdue(days = 1)
            ),
            PlantUiModel(
                name = "Little Succulent",
                scientificName = "Echeveria",
                status = PlantStatus.Healthy(tasks = 0, isDueToday = true)
            ),
            PlantUiModel(
                name = "Fiddle Leaf Fig",
                scientificName = "Ficus lyrata",
                status = PlantStatus.Healthy(tasks = 1, isDueToday = true)
            ),
            PlantUiModel(
                name = "Peace Lily",
                scientificName = "Spathiphyllum",
                status = PlantStatus.Overdue(days = 2)
            ),
            PlantUiModel(
                name = "Pothos",
                scientificName = "Epipremnum aureum",
                status = PlantStatus.Healthy(tasks = 1, isDueToday = false)
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F4)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(plants) { plant ->
            PlantCard(plant = plant)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlantCard(plant: PlantUiModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Plant Image Placeholder
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                 Icon(
                     imageVector = Icons.Default.Eco, // Placeholder
                     contentDescription = null,
                     tint = Color.Gray,
                     modifier = Modifier.size(32.dp)
                 )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = plant.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF292524),
                    fontSize = 18.sp
                )
                Text(
                    text = plant.scientificName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF78716B),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                // Status Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    when (val status = plant.status) {
                        is PlantStatus.Healthy -> {
                            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                if (status.tasks > 0) {
                                    StatusBadge(
                                        icon = Icons.Default.CheckCircle,
                                        text = "${status.tasks} Task Today",
                                        color = Color(0xFF009966)
                                    )
                                }
                                if (status.isDueToday) {
                                    StatusBadge(
                                        icon = Icons.Default.WaterDrop,
                                        text = "Due Today",
                                        color = Color(0xFF009966)
                                    )
                                }
                            }
                        }
                        is PlantStatus.Overdue -> {
                            StatusBadge(
                                icon = Icons.Default.Warning,
                                text = "Overdue by ${status.days}d",
                                color = Color(0xFFFF2056)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatusBadge(icon: ImageVector, text: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = color,
            fontWeight = FontWeight.Medium
        )
    }
}

data class PlantUiModel(
    val name: String,
    val scientificName: String,
    val status: PlantStatus
)

sealed class PlantStatus {
    data class Healthy(val tasks: Int, val isDueToday: Boolean) : PlantStatus()
    data class Overdue(val days: Int) : PlantStatus()
}
