package com.tzeyi.movies.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun DividerText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth().weight(1f),
            thickness = 1.dp,
        )
        Text(
            text = text,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth().weight(1f),
            thickness = 1.dp,
        )
    }
}

@PreviewLightDarkBg
@Composable
fun DividerTextPreview() {
    MoviesTheme { DividerText(text = "or") }
}
