package com.tzeyi.movies.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tzeyi.movies.R
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun LoginPrompt(
    modifier: Modifier = Modifier,
    isLogin: Boolean = true,
    onTextClicked: () -> Unit,
) {
    val prompt = if (isLogin) R.string.login_prompt else R.string.sign_up_prompt
    val clickableText = if (isLogin) R.string.login else R.string.sign_up
    Row(modifier = modifier) {
        Text(text = stringResource(prompt))
        Text(
            text = stringResource(clickableText),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 6.dp).clickable { onTextClicked() },
        )
    }
}

@PreviewLightDarkBg
@Composable
fun LoginPromptPreview() {
    MoviesTheme { LoginPrompt(isLogin = false) {} }
}
