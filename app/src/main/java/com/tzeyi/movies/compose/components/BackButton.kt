package com.tzeyi.movies.compose.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun BackButton(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    IconButton(modifier = modifier, onClick = onBackClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back button",
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@PreviewLightDarkBg
@Composable
fun BackButtonPreview() {
    MoviesTheme { BackButton() }
}
