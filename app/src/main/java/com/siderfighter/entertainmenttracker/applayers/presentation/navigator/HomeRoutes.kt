package com.siderfighter.entertainmenttracker.applayers.presentation.navigator

sealed class HomeRoutes(val route: String) {
    data object SplashRoute : HomeRoutes("splash_screen")
    data object AddCategoryRoute : HomeRoutes("add_category")
    data object HomeNoDataRoute : HomeRoutes("home_no_data") // can be start destination for category feature route
    data object CategoryListRoute : HomeRoutes("category_list") // can be start destination for category feature route
    data object CategoryDetailRoute : HomeRoutes("category_detail")
}

sealed class FeatureRoutes(val route: String) {
    data object CategoryFeatureRoute : FeatureRoutes("category_feature")
}