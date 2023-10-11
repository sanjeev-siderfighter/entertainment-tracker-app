package com.siderfighter.entertainmenttracker.applayers.presentation.homescreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.siderfighter.entertainmenttracker.applayers.presentation.navigator.AppRoutes
import com.siderfighter.entertainmenttracker.applayers.utils.LOG_TAG

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    val homeUiState by viewModel.categoriesFlow.collectAsStateWithLifecycle()
    println("$LOG_TAG  HomeScreen $homeUiState")

    when (homeUiState) {
        is HomeUiState.NoData -> {
            LaunchedEffect(key1 = homeUiState) { // wrapping in LaunchedEffect since it involves transition animation
                println("$LOG_TAG when NoData")
                navController.navigate(AppRoutes.HomeNoDataRoute.route)
            }
        }

        is HomeUiState.Loading -> {
            AppLoader()
        }

        else -> {
            Toast.makeText(context, "$homeUiState", Toast.LENGTH_SHORT).show()
        }
    }

    DisposableEffect(key1 = "HomeScreen") {
        onDispose {
            println("$LOG_TAG HomeScreen onDispose")
        }
    }
}