package com.siderfighter.entertainmenttracker.applayers.presentation.homescreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.siderfighter.entertainmenttracker.applayers.presentation.navigator.AppRoutes

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    val homeUiState by viewModel.categoriesFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getAllCategories()
    }
    Toast.makeText(context, "$homeUiState", Toast.LENGTH_SHORT).show()

    when (homeUiState) {
        is HomeUiState.NoData -> {
            navController.navigate(AppRoutes.HomeNoDataRoute.route)
        }

        is HomeUiState.Loading -> {
            HomeLoader()
        }

        else -> {
            Toast.makeText(context, "$homeUiState", Toast.LENGTH_SHORT).show()
        }
    }
}