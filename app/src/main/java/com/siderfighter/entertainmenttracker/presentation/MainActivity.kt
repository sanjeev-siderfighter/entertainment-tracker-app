package com.siderfighter.entertainmenttracker.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.siderfighter.entertainmenttracker.presentation.homescreen.HomeScreen
import com.siderfighter.entertainmenttracker.presentation.homescreen.HomeUiState
import com.siderfighter.entertainmenttracker.presentation.homescreen.HomeViewModel
import com.siderfighter.entertainmenttracker.ui.theme.EntertainmentTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EntertainmentTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(homeUiState = HomeUiState.NoData, onNoData = {
                        Toast.makeText(this, "on no data", Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }

        getAllCategories()
    }

    private fun getAllCategories() {
        viewModel.getAllCategories()
    }
}