package com.tzeyi.movies.compose.moviedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.compose.components.SimpleTopAppBar
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun NetworkErrorScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    Scaffold(
        modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = { SimpleTopAppBar(onBackClick = onBackClick) },
        content = {
            Column(
                modifier =
                    Modifier.fillMaxSize()
                        .padding(
                            start = 24.dp + it.calculateStartPadding(LayoutDirection.Ltr),
                            end = 24.dp + it.calculateEndPadding(LayoutDirection.Ltr),
                        ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Network Unavailable",
                    style =
                        TextStyle(
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                        ),
                )
                Text(
                    modifier = Modifier.padding(start = 50.dp, top = 6.dp, end = 50.dp),
                    text = "Please check your internet connection and try again.",
                    style =
                        TextStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Medium,
                        ),
                    textAlign = TextAlign.Center,
                )
            }
        },
    )
}

@PreviewLightDarkBg
@Composable
private fun NetworkErrorScreenPreview() {
    MoviesTheme { NetworkErrorScreen() }
}
