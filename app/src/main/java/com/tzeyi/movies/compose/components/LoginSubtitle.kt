package com.tzeyi.movies.compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun LoginSubtitle(
    text: String,
    modifier: Modifier = Modifier,
    align: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        fontSize = 19.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.0.sp,
        textAlign = align,
    )
}

@PreviewLightDarkBg
@Composable
fun LoginSubtitlePreview() {
    MoviesTheme { LoginSubtitle("This is a sample subtitle") }
}
