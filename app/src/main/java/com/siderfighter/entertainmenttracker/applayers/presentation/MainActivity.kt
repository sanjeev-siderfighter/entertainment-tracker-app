package com.siderfighter.entertainmenttracker.applayers.presentation

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.siderfighter.entertainmenttracker.applayers.presentation.addcategoryscreen.AddCategoryViewModel
import com.siderfighter.entertainmenttracker.applayers.presentation.broadcastreceiver.SmsBroadcastReceiver
import com.siderfighter.entertainmenttracker.applayers.presentation.homescreen.HomeScreen
import com.siderfighter.entertainmenttracker.applayers.presentation.homescreen.HomeUiState
import com.siderfighter.entertainmenttracker.applayers.presentation.homescreen.HomeViewModel
import com.siderfighter.entertainmenttracker.applayers.presentation.ui.theme.EntertainmentTrackerTheme
import com.siderfighter.entertainmenttracker.application.AppSignatureHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val addCategoryViewModel: AddCategoryViewModel by viewModels()

    private val smsBroadcastReceiver = SmsBroadcastReceiver()

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

        registerReceiver(smsBroadcastReceiver, IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION), RECEIVER_NOT_EXPORTED)
        getAllCategories()
        initSmsRetriever()
    }

    private fun getAllCategories() {
        homeViewModel.getAllCategories()
    }

    private fun initSmsRetriever() {
        val appSignatureHelper = AppSignatureHelper(this).appSignatures
        Log.d("AppSignatureHelper", appSignatureHelper.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(smsBroadcastReceiver)
    }

    override fun onPause() {
        super.onPause()
    }
}