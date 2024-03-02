package com.tzeyi.movies.compose.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    data object LoginLanding : Screen("loginlanding")

    data object Login : Screen("login")

    data object SignUp : Screen("signup")

    data object MovieList : Screen("movielist")

    data object MovieDetails : Screen("moviedetails") {
        const val MOVIE_DETAILS_ARG = "imdbId"
        val routeWithArgs = "$route/{$MOVIE_DETAILS_ARG}"
        val arguments =
            listOf(
                navArgument(MOVIE_DETAILS_ARG) { type = NavType.StringType },
            )

        fun createRoute(imdbId: String) = "$route/$imdbId"
    }
}
