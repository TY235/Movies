package com.tzeyi.movies.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import com.tzeyi.movies.compose.navigation.MoviesNavHost
import com.tzeyi.movies.utilities.GoogleAuthUiClient

@Composable
fun MoviesApp() {
    val context = LocalContext.current.applicationContext
    val navController = rememberNavController()
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context),
        )
    }
    // Remove this line if auto login is required
    LaunchedEffect(Unit) { googleAuthUiClient.signOut() }
    MoviesNavHost(navController, googleAuthUiClient)
}
