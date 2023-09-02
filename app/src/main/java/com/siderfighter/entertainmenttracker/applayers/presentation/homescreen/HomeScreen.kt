package com.siderfighter.entertainmenttracker.applayers.presentation.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(homeUiState: HomeUiState, onNoData: () -> Unit) {
    if (homeUiState is HomeUiState.NoData) {
        HomeNoDataScreen {
            onNoData()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(homeUiState = HomeUiState.NoData, onNoData = {})
}