package com.nxdinh94.plantreminder.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.nxdinh94.plantreminder.camera.presentation.screen.NotesRoute
import com.nxdinh94.plantreminder.camera.presentation.screen.NotesScreen
import com.nxdinh94.plantreminder.chat.presentation.screen.detail.ChatDetailRoute
import com.nxdinh94.plantreminder.chat.presentation.screen.detail.ChatDetailScreen
import com.nxdinh94.plantreminder.chat.presentation.screen.list.PlantsRoute
import com.nxdinh94.plantreminder.chat.presentation.screen.list.PlantsScreen
import com.nxdinh94.plantreminder.home.presentation.screen.detail.PlantDetailRoute
import com.nxdinh94.plantreminder.home.presentation.screen.detail.PlantDetailScreen
import com.nxdinh94.plantreminder.home.presentation.screen.green.TimeLineRoute
import com.nxdinh94.plantreminder.home.presentation.screen.green.TimeLineScreen
import com.nxdinh94.plantreminder.home.presentation.screen.home.HomeRoute
import com.nxdinh94.plantreminder.home.presentation.screen.home.HomeScreen
import com.nxdinh94.plantreminder.home.presentation.screen.home.HomeViewModel
import com.nxdinh94.plantreminder.home.presentation.screen.home.Plants


@Composable
fun PlantReminderNavDisplay(
    topLevelBackStack: TopLevelBackStack<Any>,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    NavDisplay(
        backStack = topLevelBackStack.backStack,
        onBack = { topLevelBackStack.removeLast() },
        modifier = modifier,
        entryProvider = entryProvider {
            // Home feature screens
            entry<HomeRoute> {
                HomeScreen(
                    viewModel = homeViewModel,
                    onPlantClick = { plantId ->
                         topLevelBackStack.add(PlantDetailRoute(plantId = plantId))
                    }
                )
            }

            entry<Plants> {
                com.nxdinh94.plantreminder.home.presentation.screen.home.Plants(
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

            // Chat feature screens (mapped to PlantsRoute as per ChatListScreen.kt)
            entry<PlantsRoute> {
                PlantsScreen(
                    onChatClick = { } // Mock chatId
                )
            }

            entry<ChatDetailRoute> {
                ChatDetailScreen(
                    onBackClick = { topLevelBackStack.removeLast() }
                )
            }

            // Camera feature screens (mapped to NotesRoute as per TimeLineScreen.kt)
            entry<NotesRoute> {
                NotesScreen()
            }

            // Green feature screens (mapped to TimeLineRoute as per GreenScreen.kt)
            entry<TimeLineRoute> {
                TimeLineScreen()
            }
        }
    )
}

