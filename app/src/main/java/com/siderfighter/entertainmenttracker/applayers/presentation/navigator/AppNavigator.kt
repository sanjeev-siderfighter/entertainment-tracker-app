package com.siderfighter.entertainmenttracker.applayers.presentation.navigator

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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

private const val ANIMATION_DURATION_MILLIS = 500

@Composable
fun AppNavigator(onNoData: () -> Unit, onCategoryAdded: (category: String) -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FeatureRoutes.CategoryFeatureRoute.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
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
        route = FeatureRoutes.CategoryFeatureRoute.route,
    ) {
        composable(
            route = AppRoutes.HomeRoute.route,
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = AppRoutes.AddCategoryRoute.route,
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
        ) {
            AddCategoryScreen(navController = navController, onConfirmed = onCategoryAdded)
        }

        composable(
            route = AppRoutes.HomeNoDataRoute.route,
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }) {
            HomeNoDataScreen(navController = navController)
        }
    }
}