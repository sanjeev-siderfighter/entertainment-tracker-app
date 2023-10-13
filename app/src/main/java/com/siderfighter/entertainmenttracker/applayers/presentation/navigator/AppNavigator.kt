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
import com.siderfighter.entertainmenttracker.applayers.presentation.screens.addcategory.AddCategoryScreen
import com.siderfighter.entertainmenttracker.applayers.presentation.screens.nodata.HomeNoDataScreen
import com.siderfighter.entertainmenttracker.applayers.presentation.splash.SplashScreen

private const val ANIMATION_DURATION_MILLIS = 500

@Composable
fun AppNavigator(onCategoryAdded: (category: String) -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FeatureRoutes.CategoryFeatureRoute.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        categoryGraph(navController, onCategoryAdded)
    }
}

private fun NavGraphBuilder.categoryGraph(
    navController: NavController,
    onCategoryAdded: (category: String) -> Unit
) {
    navigation(
        startDestination = HomeRoutes.SplashRoute.route,
        route = FeatureRoutes.CategoryFeatureRoute.route,
    ) {
        composable(
            route = HomeRoutes.SplashRoute.route
        ) {
            SplashScreen(navController = navController)
        }

        composable(
            route = HomeRoutes.AddCategoryRoute.route,
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
            popEnterTransition = {
                fadeIn() + slideIntoContainer(
                    animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            popExitTransition = {
                fadeOut() + slideOutOfContainer(
                    animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                )
            }
        ) {
            AddCategoryScreen(navController = navController, onConfirmed = onCategoryAdded)
        }

        composable(
            route = HomeRoutes.HomeNoDataRoute.route,
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
            popEnterTransition = {
                fadeIn() + slideIntoContainer(
                    animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            popExitTransition = {
                fadeOut() + slideOutOfContainer(
                    animationSpec = tween(ANIMATION_DURATION_MILLIS, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
        ) {
            HomeNoDataScreen(navController = navController)
        }
    }
}