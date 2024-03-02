package com.tzeyi.movies.compose.navigation

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.android.gms.common.api.ApiException
import com.tzeyi.movies.compose.authentication.LoginLandingScreen
import com.tzeyi.movies.compose.authentication.LoginScreen
import com.tzeyi.movies.compose.authentication.SignUpScreen
import com.tzeyi.movies.compose.moviedetails.MovieDetailsScreen
import com.tzeyi.movies.compose.movielist.MovieListScreen
import com.tzeyi.movies.utilities.GoogleAuthUiClient
import com.tzeyi.movies.viewmodels.AuthenticationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MoviesNavHost(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient,
) {
    val context = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = Screen.LoginLanding.route,
    ) {
        loginLanding(
            navigateToLogin = { navController.navigate(Screen.Login.route) },
            navigateToSignUp = { navController.navigate(Screen.SignUp.route) },
        )
        login(
            navigateBack = { navController.popBackStack() },
            navigateToMovieList = { navController.navigateWithFreshStack(Screen.MovieList.route) },
            navigateToSignUp = { navController.navigate(Screen.SignUp.route) },
            context = context,
            coroutineScope = coroutineScope,
            googleAuthUiClient = googleAuthUiClient,
        )
        signUp(
            navigateBack = { navController.popBackStack() },
            navigateToLogin = { navController.navigate(Screen.Login.route) },
        )
        movieList(
            navigateToMovieDetails = { imdbId ->
                navController.navigate(Screen.MovieDetails.createRoute(imdbId))
            },
        )
        movieDetails(
            navigateBack = { navController.navigateUp() },
        )
    }
}

fun NavGraphBuilder.loginLanding(
    navigateToLogin: () -> Unit,
    navigateToSignUp: () -> Unit,
) =
    composable(Screen.LoginLanding.route) {
        LoginLandingScreen(
            onLoginClick = navigateToLogin,
            onSignUpClick = navigateToSignUp,
        )
    }

fun NavGraphBuilder.login(
    navigateBack: () -> Unit,
    navigateToMovieList: () -> Unit,
    navigateToSignUp: () -> Unit,
    context: Context,
    coroutineScope: CoroutineScope,
    googleAuthUiClient: GoogleAuthUiClient,
) =
    composable(Screen.Login.route) {
        LaunchedEffect(Unit) {
            if (googleAuthUiClient.getSignedInUser() != null) {
                navigateToMovieList()
            }
        }
        val viewModel: AuthenticationViewModel = viewModel()
        val loadingState by viewModel.loadingState.collectAsStateWithLifecycle()
        val googleSignInState by viewModel.googleSignInState.collectAsStateWithLifecycle()
        val credentialsSignInState by viewModel.credentialsSignInState.collectAsStateWithLifecycle()

        val launcher =
            rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK) {
                        coroutineScope.launch {
                            val signInResult =
                                googleAuthUiClient.signInWithIntent(
                                    intent = result.data ?: return@launch)
                            viewModel.onSignInResult(signInResult)
                        }
                    } else {
                        viewModel.setLoading(false)
                    }
                },
            )
        LaunchedEffect(googleSignInState.isSignInSuccessful) {
            if (googleSignInState.isSignInSuccessful) {
                googleAuthUiClient.getSignedInUser()?.username?.run {
                    Toast.makeText(context, "Welcome, $this!", Toast.LENGTH_LONG).show()
                } ?: Toast.makeText(context, "Welcome!", Toast.LENGTH_LONG).show()
                navigateToMovieList()
                viewModel.resetSignInState()
            }
        }

        LoginScreen(
            loadingState = loadingState,
            onBackClick = navigateBack,
            onLoginClick = { username, password -> viewModel.login(username, password) },
            onSignUpClick = navigateToSignUp,
            onGoogleButtonClick = {
                viewModel.setLoading(true)
                coroutineScope.launch {
                    try {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(signInIntentSender ?: return@launch)
                                .build())
                    } catch (e: ApiException) {
                        viewModel.setLoading(false)
                        Toast.makeText(
                                context,
                                "Please enter *#*#66382723#*#* in Dialer app to turn off cool down.",
                                Toast.LENGTH_LONG,
                            )
                            .show()
                    }
                }
            },
        )

        LaunchedEffect(credentialsSignInState.isSignInSuccessful) {
            if (credentialsSignInState.isSignInSuccessful != null) {
                Toast.makeText(
                        context,
                        "Welcome, ${credentialsSignInState.isSignInSuccessful}!",
                        Toast.LENGTH_LONG)
                    .show()
                navigateToMovieList()
                viewModel.resetSignInState()
            }
        }

        if (credentialsSignInState.signInError != null) {
            Toast.makeText(context, credentialsSignInState.signInError, Toast.LENGTH_LONG).show()
            viewModel.resetSignInState()
        }
    }

fun NavGraphBuilder.signUp(
    navigateBack: () -> Unit,
    navigateToLogin: () -> Unit,
) =
    composable(Screen.SignUp.route) {
        SignUpScreen(
            onBackClick = navigateBack,
            onSignUpClick = navigateToLogin,
        )
    }

fun NavGraphBuilder.movieList(navigateToMovieDetails: (String) -> Unit) =
    composable(Screen.MovieList.route) {
        MovieListScreen(
            onMovieClick = navigateToMovieDetails,
        )
    }

fun NavGraphBuilder.movieDetails(navigateBack: () -> Unit) =
    composable(
        route = Screen.MovieDetails.routeWithArgs,
        arguments = Screen.MovieDetails.arguments,
    ) {
        val imdbId = it.arguments?.getString(Screen.MovieDetails.MOVIE_DETAILS_ARG)
        MovieDetailsScreen(
            imdbId = imdbId,
            onBackClick = navigateBack,
        )
    }

fun NavHostController.navigateWithFreshStack(route: String) =
    navigate(route) {
        popUpTo(this@navigateWithFreshStack.graph.findStartDestination().id) { inclusive = true }
    }
