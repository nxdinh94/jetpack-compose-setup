package com.nxdinh94.plantreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.nxdinh94.plantreminder.core.ui.theme.PlantReminderTheme
import com.nxdinh94.plantreminder.core.navigation.PlantReminderNavDisplay
import com.nxdinh94.plantreminder.core.navigation.TOP_LEVEL_ROUTES
import com.nxdinh94.plantreminder.core.navigation.TopLevelBackStack
import com.nxdinh94.plantreminder.home.presentation.screen.home.HomeRoute
import kotlin.getValue


import com.nxdinh94.plantreminder.core.common.AppContainer

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val topLevelBackStack = remember { TopLevelBackStack<Any>(HomeRoute) }

            PlantReminderTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar (
                            modifier = Modifier
                                .windowInsetsPadding(WindowInsets(0)).height(114.dp)
                        ){
                            TOP_LEVEL_ROUTES.forEach { topLevelRoute ->
                                val isSelected = topLevelRoute == topLevelBackStack.topLevelKey

                                CompositionLocalProvider(LocalRippleConfiguration provides null) {
                                    NavigationBarItem(
                                        selected = isSelected,
                                        onClick = {
                                            topLevelBackStack.addTopLevel(topLevelRoute)
                                        },
                                        icon = {
                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                Icon(
                                                    painter = painterResource(topLevelRoute.icon),
                                                    contentDescription = stringResource(topLevelRoute.name),
                                                    modifier = Modifier.size(24.dp)
                                                )
                                                Text(
                                                    text = stringResource(topLevelRoute.name),
                                                    fontSize = 10.sp
                                                )
                                            }
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            indicatorColor = Color.Transparent,
                                            selectedIconColor = MaterialTheme.colorScheme.primary,
                                            selectedTextColor = MaterialTheme.colorScheme.primary,
                                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    )
                                }
                            }
                        }

                    }
                ) { contentPadding ->
                    PlantReminderNavDisplay(
                        topLevelBackStack = topLevelBackStack,
                        modifier = Modifier.padding(bottom = contentPadding.calculateBottomPadding())
                    )
                }
            }
        }
    }
}
