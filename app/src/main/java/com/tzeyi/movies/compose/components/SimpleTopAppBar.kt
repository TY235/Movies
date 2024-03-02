package com.tzeyi.movies.compose.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.ui.theme.MoviesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(title: String = "", onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        navigationIcon = { BackButton(onBackClick = onBackClick) },
    )
}

@PreviewLightDarkBg
@Composable
fun SimpleTopAppBarPreview() {
    MoviesTheme { SimpleTopAppBar {} }
}
