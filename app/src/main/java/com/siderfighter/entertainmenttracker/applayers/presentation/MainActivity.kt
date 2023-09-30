package com.siderfighter.entertainmenttracker.applayers.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.siderfighter.entertainmenttracker.applayers.presentation.navigator.AppNavigator
import com.siderfighter.entertainmenttracker.applayers.presentation.ui.theme.EntertainmentTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val addCategoryViewModel: AddCategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EntertainmentTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(
                        onNoData = {
                            Toast.makeText(this, "on no data", Toast.LENGTH_SHORT).show()
                        },
                        onCategoryAdded = { category ->
                            Toast.makeText(
                                this,
                                "category $category has been added",
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                }
            }
        }
    }
}