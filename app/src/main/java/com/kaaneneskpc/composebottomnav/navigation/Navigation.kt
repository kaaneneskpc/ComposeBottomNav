package com.kaaneneskpc.composebottomnav.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kaaneneskpc.Screens
import com.kaaneneskpc.composebottomnav.screens.FavoriteScreen
import com.kaaneneskpc.composebottomnav.screens.HomeScreen
import com.kaaneneskpc.composebottomnav.screens.ProfileScreen

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screens.Profile.route,
        modifier = modifier
    ) {
        composable(Screens.Profile.route) { ProfileScreen() }
        composable(Screens.Favorite.route) { FavoriteScreen() }
        composable(Screens.Home.route) { HomeScreen() }
    }
}