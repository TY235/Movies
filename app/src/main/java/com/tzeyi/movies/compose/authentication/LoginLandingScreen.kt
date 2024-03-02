package com.tzeyi.movies.compose.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tzeyi.movies.R
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.compose.components.LoginSubtitle
import com.tzeyi.movies.compose.components.LoginTitle
import com.tzeyi.movies.compose.components.PrimaryButton
import com.tzeyi.movies.compose.components.SecondaryButton
import com.tzeyi.movies.compose.components.VerticalSpacer
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun LoginLandingScreen(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    Scaffold(
        modifier =
            Modifier.background(MaterialTheme.colorScheme.surface)
                .statusBarsPadding()
                .navigationBarsPadding(),
    ) {
        Column(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.weight(9f).fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(R.drawable.illus_watch_movie),
                    contentDescription = "Watching movie illustration",
                )
                VerticalSpacer(5)
                LoginTitle(
                    text = stringResource(R.string.login_landing_title),
                    modifier = Modifier.padding(horizontal = 40.dp),
                    align = TextAlign.Center,
                )
                LoginSubtitle(
                    text = stringResource(R.string.login_landing_subtitle),
                    modifier = Modifier.padding(start = 40.dp, top = 10.dp, end = 40.dp),
                    align = TextAlign.Center,
                )
            }

            Column(
                modifier = Modifier.weight(weight = 2f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PrimaryButton(
                    text = stringResource(id = R.string.login),
                    modifier = Modifier.padding(horizontal = 20.dp),
                    onClick = onLoginClick,
                )
                SecondaryButton(
                    text = stringResource(R.string.sign_up),
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    onClick = onSignUpClick,
                )
            }
        }
    }
}

@PreviewLightDarkBg
@Composable
fun LoginLandingScreenPreview() {
    MoviesTheme {
        LoginLandingScreen(
            onLoginClick = {},
            onSignUpClick = {},
        )
    }
}
