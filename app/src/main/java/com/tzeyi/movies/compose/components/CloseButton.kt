package com.tzeyi.movies.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.tzeyi.movies.R
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun CloseButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Box(
        modifier =
            modifier
                .size(48.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = CircleShape,
                ),
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Close button",
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CloseButtonPreview() {
    MoviesTheme { CloseButton() }
}
