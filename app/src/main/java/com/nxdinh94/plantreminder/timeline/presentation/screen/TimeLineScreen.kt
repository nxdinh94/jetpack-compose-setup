package com.nxdinh94.plantreminder.camera.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nxdinh94.plantreminder.core.navigation.TopLevelRoute
import kotlinx.serialization.Serializable

import com.nxdinh94.plantreminder.R

/**
 * Navigation key for Camera Screen
 */
@Serializable
data object NotesRoute : TopLevelRoute {
    override val icon: Int = R.drawable.notes
    override val name: Int = R.string.nav_item_notes
}

/**
 * Camera Screen - displays camera view
 */
@Composable
fun NotesScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Text("Notes Screen", color = Color.White)
    }
}
