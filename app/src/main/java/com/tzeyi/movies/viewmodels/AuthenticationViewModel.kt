package com.tzeyi.movies.viewmodels

import androidx.lifecycle.ViewModel
import com.tzeyi.movies.utilities.CredentialsSignInState
import com.tzeyi.movies.utilities.SignInResult
import com.tzeyi.movies.utilities.GoogleSignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthenticationViewModel : ViewModel() {
    private val _googleSignInState = MutableStateFlow(GoogleSignInState())
    val googleSignInState = _googleSignInState.asStateFlow()
    
    private val _credentialsSignInState = MutableStateFlow(CredentialsSignInState())
    val credentialsSignInState = _credentialsSignInState.asStateFlow()

    private val _loadingState = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    fun setLoading(loading: Boolean) {
        _loadingState.value = loading
    }

    fun onSignInResult(result: SignInResult) {
        setLoading(false)
        _googleSignInState.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage,
            )
        }
    }

    fun resetSignInState() {
        _googleSignInState.update { GoogleSignInState() }
        _credentialsSignInState.update { CredentialsSignInState() }
    }

    fun login(username: String, password: String) {
        setLoading(true)
        if (!validateInputs(username, password)) return
        if (!authenticateUser(username, password)) return
        setLoading(false)
        _credentialsSignInState.update {
            it.copy(
                isSignInSuccessful = username,
                signInError = null,
            )
        }
    }

    private fun validateInputs(username: String, password: String): Boolean =
        when {
            username.isBlank() -> {
                setLoading(false)
                _credentialsSignInState.update {
                    it.copy(
                        isSignInSuccessful = null,
                        signInError = "Username cannot be empty.",
                    )
                }
                false
            }
            password.isEmpty() -> {
                setLoading(false)
                _credentialsSignInState.update {
                    it.copy(
                        isSignInSuccessful = null,
                        signInError = "Password cannot be empty.",
                    )
                }
                false
            }
            else -> true
        }

    private fun authenticateUser(username: String, password: String): Boolean {
        if (username == "VVVBB," && password == "@bcd1234") return true
        setLoading(false)
        _credentialsSignInState.update {
            it.copy(
                isSignInSuccessful = null,
                signInError = "Invalid username or password.",
            )
        }

        return false
    }
}
