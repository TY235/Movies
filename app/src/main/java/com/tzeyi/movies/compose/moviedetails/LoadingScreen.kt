package com.tzeyi.movies.compose.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tzeyi.movies.R
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun LoadingScreen() {
    Box(
        Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
        
        LottieAnimation(
            modifier = Modifier.align(Alignment.Center).size(150.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    MoviesTheme { LoadingScreen() }
}
