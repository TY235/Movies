package com.tzeyi.movies.compose.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tzeyi.movies.R
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun PrimaryButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 7.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@PreviewLightDarkBg
@Composable
fun PrimaryButtonPreview() {
    MoviesTheme { PrimaryButton(stringResource(R.string.login)) {} }
}
