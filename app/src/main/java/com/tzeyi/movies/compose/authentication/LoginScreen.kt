package com.tzeyi.movies.compose.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tzeyi.movies.R
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.compose.components.DividerText
import com.tzeyi.movies.compose.components.GoogleButton
import com.tzeyi.movies.compose.components.LoginPrompt
import com.tzeyi.movies.compose.components.LoginSubtitle
import com.tzeyi.movies.compose.components.LoginTextField
import com.tzeyi.movies.compose.components.LoginTitle
import com.tzeyi.movies.compose.components.PasswordTextField
import com.tzeyi.movies.compose.components.PrimaryButton
import com.tzeyi.movies.compose.components.SimpleTopAppBar
import com.tzeyi.movies.compose.components.VerticalSpacer
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun LoginScreen(
    loadingState: Boolean,
    onBackClick: () -> Unit,
    onLoginClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
    onGoogleButtonClick: () -> Unit,
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var hideKeyboard by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        modifier =
            Modifier.background(MaterialTheme.colorScheme.surface)
                .statusBarsPadding()
                .navigationBarsPadding(),
        topBar = { SimpleTopAppBar(onBackClick = onBackClick) },
        content = { innerPadding ->
            Column(
                modifier =
                    Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            hideKeyboard = true
                        }
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(horizontal = 22.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                VerticalSpacer()
                LoginTitle(text = stringResource(R.string.login_title))
                VerticalSpacer()
                LoginSubtitle(text = stringResource(R.string.login_subtitle))
                VerticalSpacer()
                LoginTextField(
                    value = username,
                    label = "Username",
                    onValueChange = { username = it },
                    hideKeyboard = hideKeyboard,
                    onFocusClear = { hideKeyboard = false })
                VerticalSpacer()
                PasswordTextField(
                    value = password,
                    label = "Password",
                    onValueChange = { password = it },
                    hideKeyboard = hideKeyboard,
                    onFocusClear = { hideKeyboard = false })
                VerticalSpacer()
                PrimaryButton(
                    text = stringResource(R.string.login),
                    onClick = { onLoginClick(username, password) },
                )
                VerticalSpacer()
                DividerText(text = "or")
                VerticalSpacer()
                GoogleButton(
                    isLoading = loadingState,
                    onClick = onGoogleButtonClick,
                )
                VerticalSpacer()
                LoginPrompt(
                    isLogin = false,
                    onTextClicked = onSignUpClick,
                )
            }
        },
    )
}

@PreviewLightDarkBg
@Composable
fun LoginScreenPreview() {
    MoviesTheme {
        LoginScreen(
            loadingState = false,
            onBackClick = {},
            onLoginClick = { _, _ -> },
            onSignUpClick = {},
            onGoogleButtonClick = {},
        )
    }
}
