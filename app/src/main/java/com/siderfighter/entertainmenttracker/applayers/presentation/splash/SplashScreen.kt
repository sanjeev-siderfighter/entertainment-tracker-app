package com.siderfighter.entertainmenttracker.applayers.presentation.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.siderfighter.entertainmenttracker.applayers.presentation.commoncomponent.AppLoader
import com.siderfighter.entertainmenttracker.applayers.presentation.navigator.HomeRoutes
import com.siderfighter.entertainmenttracker.applayers.presentation.screens.home.HomeUiState
import com.siderfighter.entertainmenttracker.applayers.presentation.screens.home.HomeViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val uiState by viewModel.categoriesFlow.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) {
        delay(1000) // todo: this delay can be removed after ktor is completed
        val destination = when (uiState) {
            is HomeUiState.Error -> HomeRoutes.HomeNoDataRoute.route

            HomeUiState.Loading -> null

            HomeUiState.NoData -> HomeRoutes.HomeNoDataRoute.route

            is HomeUiState.Success -> HomeRoutes.CategoryListRoute.route
        }
        destination?.let { route ->
            navController.navigate(route) {
                popUpTo(HomeRoutes.SplashRoute.route) {
                    inclusive = true
                }
            }
        }
    }
    SplashLoading()
}

@Composable
private fun SplashLoading() {
    AppLoader(
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showSystemUi = true)
@Composable
private fun SplashLoadingPreview() {
    SplashLoading()
}