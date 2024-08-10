package com.kaaneneskpc.composebottomnav


import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import com.kaaneneskpc.Screens
import com.kaaneneskpc.composebottomnav.navigation.Navigation

@Composable
fun BottomNavigationMain() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.padding(all = 12.dp),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Navigation(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = remember {
        listOf(
            Screens.Profile,
            Screens.Favorite,
            Screens.Home
        )
    }
    var selectedIndex by remember { mutableIntStateOf(0) }

    AnimatedNavigationBar(
        modifier = Modifier.height(64.dp),
        selectedIndex = selectedIndex,
        cornerRadius = shapeCornerRadius(34.dp),
        ballAnimation = Parabolic(tween(300)),
        indentAnimation = Height(tween(300)),
        barColor = MaterialTheme.colorScheme.primary,
        ballColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEach { item ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .noRippleClickable {
                        selectedIndex = Screens.indexOf(item)
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = item.icon,
                    contentDescription = "Icon",
                    tint = getItemColor(isSelected = selectedIndex == Screens.indexOf(item))
                )
            }
        }
    }
}

@Composable
fun getItemColor(isSelected: Boolean) = if (isSelected) {
    MaterialTheme.colorScheme.onPrimary
} else {
    MaterialTheme.colorScheme.inversePrimary
}