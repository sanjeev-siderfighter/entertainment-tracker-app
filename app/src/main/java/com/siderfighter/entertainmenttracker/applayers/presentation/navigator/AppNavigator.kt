package com.siderfighter.entertainmenttracker.applayers.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.siderfighter.entertainmenttracker.applayers.presentation.addcategoryscreen.AddCategoryScreen
import com.siderfighter.entertainmenttracker.applayers.presentation.homescreen.HomeScreen

@Composable
fun AppNavigator(onNoData: () -> Unit, onCategoryAdded: (category: String) -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoutes.HomeRoute.route) {
        composable(route = AppRoutes.HomeRoute.route) {
            HomeScreen(onNoData = onNoData)
        }

        composable(route = AppRoutes.AddCategoryRoute.route) {
            AddCategoryScreen(onConfirmed = onCategoryAdded)
        }
    }
}