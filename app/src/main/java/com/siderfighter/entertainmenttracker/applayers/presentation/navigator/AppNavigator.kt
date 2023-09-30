package com.siderfighter.entertainmenttracker.applayers.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.siderfighter.entertainmenttracker.applayers.presentation.addcategoryscreen.AddCategoryScreen
import com.siderfighter.entertainmenttracker.applayers.presentation.homescreen.HomeNoDataScreen
import com.siderfighter.entertainmenttracker.applayers.presentation.homescreen.HomeScreen

@Composable
fun AppNavigator(onNoData: () -> Unit, onCategoryAdded: (category: String) -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FeatureRoutes.CategoryFeatureRoute.route
    ) {
        categoryGraph(navController, onNoData, onCategoryAdded)
    }
}

private fun NavGraphBuilder.categoryGraph(
    navController: NavController,
    onNoData: () -> Unit,
    onCategoryAdded: (category: String) -> Unit
) {
    navigation(
        startDestination = AppRoutes.HomeRoute.route,
        route = FeatureRoutes.CategoryFeatureRoute.route
    ) {
        composable(route = AppRoutes.HomeRoute.route) {
            HomeScreen(navController = navController)
        }

        composable(route = AppRoutes.AddCategoryRoute.route) {
            AddCategoryScreen(onConfirmed = onCategoryAdded)
        }

        composable(route = AppRoutes.HomeNoDataRoute.route) {
            HomeNoDataScreen(navController = navController)
        }
    }
}