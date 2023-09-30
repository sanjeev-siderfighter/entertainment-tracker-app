package com.siderfighter.entertainmenttracker.applayers.presentation.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.siderfighter.entertainmentprogress.R

@Composable
fun HomeLoader() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loader_lottie))
    LottieAnimation(
        composition = composition,
        restartOnPlay = false,
        iterations = LottieConstants.IterateForever
    )
}

@Preview(showSystemUi = true)
@Composable
private fun HomeLoaderScreenPreview() {
    HomeLoader()
}