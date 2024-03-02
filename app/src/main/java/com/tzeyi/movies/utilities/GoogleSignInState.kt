package com.tzeyi.movies.utilities

data class GoogleSignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
)

data class CredentialsSignInState(
    val isSignInSuccessful: String? = null,
    val signInError: String? = null,
)
