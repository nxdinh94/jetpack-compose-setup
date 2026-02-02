package com.nxdinh94.plantreminder.home.presentation.screen.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.nxdinh94.plantreminder.core.navigation.TopLevelRoute
import kotlinx.serialization.Serializable

/**
 * Navigation key for Home Screen (top-level)
 * This is the entry point for the Home feature
 */
@Serializable
data object HomeRoute : TopLevelRoute {
    override val icon: ImageVector = Icons.Default.Home
}
