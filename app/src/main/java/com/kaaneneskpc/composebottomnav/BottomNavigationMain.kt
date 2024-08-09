package com.kaaneneskpc.composebottomnav


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kaaneneskpc.Screens
import com.kaaneneskpc.composebottomnav.screens.LoginScreen
import com.kaaneneskpc.composebottomnav.screens.ProfileScreen
import com.kaaneneskpc.composebottomnav.screens.RegisterScreen

@Composable
fun BottomNavigationMain() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Navigation(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screens.Profile.route,
        modifier = modifier
    ) {
        composable(Screens.Profile.route) { ProfileScreen() }
        composable(Screens.Register.route) { RegisterScreen() }
        composable(Screens.Login.route) { LoginScreen() }
    }
}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screens.Profile,
        Screens.Register,
        Screens.Login
    )
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            val isSelected = currentRoute == screen.route
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = screen.icon, contentDescription = "Icon", tint = getItemColor(
                            isSelected = isSelected
                        )
                    )
                },
                label = { Text(screen.title, color = getItemColor(isSelected = isSelected)) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun getItemColor(isSelected: Boolean) = if (isSelected) {
    Color.Gray
} else {
    Color.LightGray
}