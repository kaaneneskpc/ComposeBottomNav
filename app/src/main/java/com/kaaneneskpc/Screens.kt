package com.kaaneneskpc

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val title: String, val icon: ImageVector) {
    data object Profile : Screens("profile", "Profile", Icons.Filled.Person)
    data object Favorite : Screens("favorite", "Favorite", Icons.Filled.Favorite)
    data object Home : Screens("home", "Home", Icons.Filled.Home)

    companion object {
        fun indexOf(screen: Screens): Int = when (screen) {
            Profile -> 0
            Favorite -> 1
            Home -> 2
        }
    }

}