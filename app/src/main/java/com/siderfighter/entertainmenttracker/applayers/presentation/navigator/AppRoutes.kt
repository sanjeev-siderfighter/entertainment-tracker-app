package com.siderfighter.entertainmenttracker.applayers.presentation.navigator

sealed class AppRoutes(val route: String) {
    data object HomeRoute : AppRoutes("home")
    data object AddCategoryRoute : AppRoutes("add_category")
}