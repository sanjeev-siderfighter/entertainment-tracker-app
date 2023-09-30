package com.siderfighter.entertainmenttracker.applayers.presentation.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNoData: () -> Unit
) {
    val homeUiState by viewModel.categoriesFlow.collectAsStateWithLifecycle()

    viewModel.getAllCategories()

    if (homeUiState is HomeUiState.NoData) {
        onNoData()
        HomeNoDataScreen(
            onButtonClick = { viewModel.getAllCategories() }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(onNoData = {})
}