package com.nxdinh94.plantreminder.chat.presentation.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.nxdinh94.plantreminder.core.navigation.TopLevelRoute
import kotlinx.serialization.Serializable
import com.nxdinh94.plantreminder.R
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
@Composable
fun PlantsScreen(
    onChatClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Text("Chat List Screen", color = Color.White)
    }
}
