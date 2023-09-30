package com.siderfighter.entertainmenttracker.applayers.presentation.navigator

sealed class AppRoutes(val route: String) {
    data object HomeRoute : AppRoutes("home")
    data object AddCategoryRoute : AppRoutes("add_category")
    data object HomeNoDataRoute : AppRoutes("home_no_data")
}

sealed class FeatureRoutes(val route: String) {
    data object CategoryFeatureRoute : FeatureRoutes("category_feature")
}