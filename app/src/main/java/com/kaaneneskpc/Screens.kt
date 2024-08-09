package com.kaaneneskpc

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val title: String, val icon: ImageVector) {
    object Profile : Screens("profile", "Profile", Icons.Filled.Person)
    object Register : Screens("register", "Register", Icons.Filled.Favorite)
    object Login : Screens("login", "Login", Icons.Filled.Home)
}