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
fun LoginTitle(
    text: String,
    modifier: Modifier = Modifier,
    align: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
        fontSize = 30.sp,
        fontWeight = FontWeight.Black,
        textAlign = align,
    )
}

@PreviewLightDarkBg
@Composable
fun LoginTitlePreview() {
    MoviesTheme { LoginTitle("This is a sample title") }
}
