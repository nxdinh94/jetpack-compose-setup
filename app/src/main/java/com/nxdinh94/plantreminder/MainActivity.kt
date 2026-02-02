package com.nxdinh94.plantreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.nxdinh94.plantreminder.home.presentation.screen.home.HomeRoute
import com.nxdinh94.plantreminder.core.ui.theme.PlantReminderTheme
import com.nxdinh94.plantreminder.core.navigation.PlantReminderNavDisplay
import com.nxdinh94.plantreminder.core.navigation.TOP_LEVEL_ROUTES
import com.nxdinh94.plantreminder.core.navigation.TopLevelBackStack


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val topLevelBackStack = remember { TopLevelBackStack<Any>(HomeRoute) }
            PlantReminderTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            TOP_LEVEL_ROUTES.forEach { topLevelRoute ->

                                val isSelected = topLevelRoute == topLevelBackStack.topLevelKey
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = {
                                        topLevelBackStack.addTopLevel(topLevelRoute)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = topLevelRoute.icon,
                                            contentDescription = null
                                        )
                                    }
                                )
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
