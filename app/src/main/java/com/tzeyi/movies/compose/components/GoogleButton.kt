package com.tzeyi.movies.compose.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tzeyi.movies.R
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    normalText: String = "Sign in with Google",
    loadingText: String = "Please wait...",
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
    progressIndicatorColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
) {
    var buttonText by remember { mutableStateOf(normalText) }

    LaunchedEffect(isLoading) {
        buttonText = if (isLoading) loadingText else normalText
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = !isLoading,
        shape = MaterialTheme.shapes.medium,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
    ) {
        Row(
            modifier =
                Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                    .animateContentSize(
                        animationSpec =
                            tween(
                                durationMillis = 300,
                                easing = LinearOutSlowInEasing,
                            ),
                    ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.logo_google),
                contentDescription = "Google Logo",
                tint = Color.Unspecified,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = buttonText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
            if (isLoading) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = progressIndicatorColor,
                    strokeWidth = 2.dp,
                )
            }
        }
    }
}

@PreviewLightDarkBg
@Composable
fun GoogleButtonPreview() {
    MoviesTheme { GoogleButton {} }
}

@PreviewLightDarkBg
@Composable
fun GoogleButtonLoadingPreview() {
    MoviesTheme { GoogleButton(isLoading = true) {} }
}
