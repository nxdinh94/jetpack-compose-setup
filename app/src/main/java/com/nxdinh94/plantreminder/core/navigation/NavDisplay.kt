package com.nxdinh94.plantreminder.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.nxdinh94.plantreminder.camera.presentation.screen.CameraRoute
import com.nxdinh94.plantreminder.camera.presentation.screen.CameraScreen
import com.nxdinh94.plantreminder.chat.presentation.screen.detail.ChatDetailRoute
import com.nxdinh94.plantreminder.chat.presentation.screen.detail.ChatDetailScreen
import com.nxdinh94.plantreminder.chat.presentation.screen.list.ChatListRoute
import com.nxdinh94.plantreminder.chat.presentation.screen.list.ChatListScreen
import com.nxdinh94.plantreminder.home.presentation.screen.detail.PlantDetailRoute
import com.nxdinh94.plantreminder.home.presentation.screen.detail.PlantDetailScreen
import com.nxdinh94.plantreminder.home.presentation.screen.home.HomeRoute
import com.nxdinh94.plantreminder.home.presentation.screen.home.Plants

@Composable
fun PlantReminderNavDisplay(
    topLevelBackStack: TopLevelBackStack<Any>,
    modifier: Modifier = Modifier
) {
    NavDisplay(
        backStack = topLevelBackStack.backStack,
        onBack = { topLevelBackStack.removeLast() },
        modifier = modifier,
        entryProvider = entryProvider {
            // Home feature screens
            entry<HomeRoute> {
                Plants(
                    onPlantClick = { plantId ->
                        topLevelBackStack.add(PlantDetailRoute(plantId = plantId))
                    }
                )
            }

            entry<Plants> {
                Plants(
                    onPlantClick = { plantId ->
                        topLevelBackStack.add(PlantDetailRoute(plantId = plantId))
                    }
                )
            }

            entry<PlantDetailRoute> { route ->
                PlantDetailScreen(
                    plantId = route.plantId,
                    onBackClick = { topLevelBackStack.removeLast() }
                )
            }

            // Chat feature screens
            entry<ChatListRoute> {
                ChatListScreen(
                    onChatClick = { topLevelBackStack.add(ChatDetailRoute) }
                )
            }

            entry<ChatDetailRoute> {
                ChatDetailScreen(
                    onBackClick = { topLevelBackStack.removeLast() }
                )
            }

            // Camera feature screens
            entry<CameraRoute> {
                CameraScreen()
            }
        }
    )
}

@Composable
private fun SettingsScreenPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Settings")
    }
}
