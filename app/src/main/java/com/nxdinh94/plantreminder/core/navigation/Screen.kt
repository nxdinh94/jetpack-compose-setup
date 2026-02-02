package com.nxdinh94.plantreminder.core.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.nxdinh94.plantreminder.camera.presentation.screen.CameraRoute
import com.nxdinh94.plantreminder.chat.presentation.screen.list.ChatListRoute
import com.nxdinh94.plantreminder.home.presentation.screen.home.HomeRoute

/**
 * Base interface for top-level navigation routes that appear in bottom navigation
 */
interface TopLevelRoute : NavKey {
    val icon: ImageVector
}

/**
 * List of all top-level routes for bottom navigation bar
 */
val TOP_LEVEL_ROUTES: List<TopLevelRoute> = listOf(HomeRoute, ChatListRoute, CameraRoute)

/**
 * Manages back stacks for each top-level navigation destination.
 * Each top-level route maintains its own independent back stack,
 * allowing users to navigate within a tab and preserve state when switching tabs.
 */
class TopLevelBackStack<T : Any>(startKey: T) {

    // Maintain a stack for each top level route
    private var topLevelStacks: LinkedHashMap<T, SnapshotStateList<T>> = linkedMapOf(
        startKey to mutableStateListOf(startKey)
    )

    // Expose the current top level route for consumers
    var topLevelKey by mutableStateOf(startKey)
        private set

    // Expose the back stack so it can be rendered by the NavDisplay
    val backStack = mutableStateListOf(startKey)

    private fun updateBackStack() =
        backStack.apply {
            clear()
            addAll(topLevelStacks.flatMap { it.value })
        }

    /**
     * Switch to a top-level destination. If the destination doesn't exist,
     * it creates a new stack. If it exists, it moves it to the top.
     */
    fun addTopLevel(key: T) {
        if (topLevelStacks[key] == null) {
            topLevelStacks[key] = mutableStateListOf(key)
        } else {
            // Move existing stack to the end (top of the order)
            topLevelStacks.apply {
                remove(key)?.let {
                    put(key, it)
                }
            }
        }
        topLevelKey = key
        updateBackStack()
    }

    /**
     * Add a screen to the current top-level destination's stack
     */
    fun add(key: T) {
        topLevelStacks[topLevelKey]?.add(key)
        updateBackStack()
    }

    /**
     * Remove the top screen from the current stack.
     * If the removed screen was a top-level destination, also remove that stack.
     */
    fun removeLast() {
        val removedKey = topLevelStacks[topLevelKey]?.removeLastOrNull()
        // If the removed key was a top level key, remove the associated top level stack
        topLevelStacks.remove(removedKey)
        topLevelKey = topLevelStacks.keys.last()
        updateBackStack()
    }
}