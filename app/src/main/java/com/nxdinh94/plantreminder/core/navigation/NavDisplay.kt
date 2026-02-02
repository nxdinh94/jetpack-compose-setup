package com.nxdinh94.plantreminder.core.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.nxdinh94.plantreminder.camera.presentation.screen.NotesRoute
import com.nxdinh94.plantreminder.camera.presentation.screen.NotesScreen
import com.nxdinh94.plantreminder.home.presentation.screen.green.TimeLineRoute
import com.nxdinh94.plantreminder.home.presentation.screen.green.TimeLineScreen
import com.nxdinh94.plantreminder.home.presentation.screen.home.homeEntryBuilder
import com.nxdinh94.plantreminder.plants.presentation.screen.list.plantsEntryBuilder

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
            homeEntryBuilder()

            // Chat feature screens (mapped to PlantsRoute as per ChatListScreen.kt)
            plantsEntryBuilder()

            // Camera feature screens (mapped to NotesRoute as per TimeLineScreen.kt)
            entry<NotesRoute> {
                NotesScreen()
            }

            // Green feature screens (mapped to TimeLineRoute as per GreenScreen.kt)
            entry<TimeLineRoute> {
                TimeLineScreen()
            }
        },
        transitionSpec = {
            // Slide in from right when navigating forward
            slideInHorizontally(initialOffsetX = { it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
        },
        predictivePopTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally(initialOffsetX = { -it }) togetherWith
                    slideOutHorizontally(targetOffsetX = { it })
        },
    )
}

