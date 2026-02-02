package com.nxdinh94.plantreminder.camera.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.nxdinh94.plantreminder.core.navigation.TopLevelRoute
import kotlinx.serialization.Serializable

/**
 * Navigation key for Camera Screen
 */
@Serializable
data object CameraRoute : TopLevelRoute {
    override val icon: ImageVector = Icons.Default.PlayArrow
}

/**
 * Camera Screen - displays camera view
 */
@Composable
fun CameraScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Text("Camera Screen", color = Color.White)
    }
}
