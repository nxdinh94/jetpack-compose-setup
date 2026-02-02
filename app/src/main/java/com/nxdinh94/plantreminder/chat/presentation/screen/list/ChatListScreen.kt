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

/**
 * Navigation key for Chat List Screen
 */
@Serializable
data object ChatListRoute : TopLevelRoute {
    override val icon: ImageVector = Icons.Default.Face
}

/**
 * Chat List Screen - displays list of conversations
 */
@Composable
fun ChatListScreen(
    onChatClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Chat List Screen", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onChatClick) {
                Text("Go to conversation")
            }
        }
    }
}
