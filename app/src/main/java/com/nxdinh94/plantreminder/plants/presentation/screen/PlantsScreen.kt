package com.nxdinh94.plantreminder.plants.presentation.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.EntryProviderScope
import com.nxdinh94.plantreminder.core.navigation.TopLevelRoute
import kotlinx.serialization.Serializable
import com.nxdinh94.plantreminder.R
import com.nxdinh94.plantreminder.plants.presentation.screen.list.detail.PlantDetailRoute
import com.nxdinh94.plantreminder.plants.presentation.screen.list.detail.PlantDetailScreen

/**
 * Navigation key for Chat List Screen
 */
@Serializable
data object PlantsRoute : TopLevelRoute {
    override val icon: Int = R.drawable.plant
    override val name: Int = R.string.nav_item_plants
}

/**
 * Chat List Screen - displays list of conversations
 */

fun EntryProviderScope<Any>.plantsEntryBuilder(){
    entry<PlantsRoute> {
        PlantsScreen()
    }
    entry<PlantDetailRoute> { route ->
        PlantDetailScreen()
    }
}

@Composable
fun PlantsScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Text("Plans Screen", color = Color.White)
    }
}
