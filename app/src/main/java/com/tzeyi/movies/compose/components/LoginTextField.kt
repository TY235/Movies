package com.tzeyi.movies.compose.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.tzeyi.movies.R
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun LoginTextField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    hideKeyboard: Boolean = false,
    onFocusClear: () -> Unit = {},
) {
    val containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    val labelColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
    val focusManager = LocalFocusManager.current

    TextField(
        label = { Text(text = label) },
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.widthIn(0.dp, 400.dp).fillMaxWidth(),
        keyboardOptions =
            KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
        keyboardActions =
            KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        singleLine = true,
        colors =
            TextFieldDefaults.colors()
                .copy(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = labelColor,
                ),
    )
    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@Composable
fun PasswordTextField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    hideKeyboard: Boolean = false,
    onFocusClear: () -> Unit = {},
) {
    val containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    val labelColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
    val localFocusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var passwordVisible: Boolean by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    TextField(
        label = { Text(text = label) },
        value = value,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        modifier = modifier.widthIn(0.dp, 400.dp).fillMaxWidth(),
        visualTransformation =
            if (isFocused && passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
        trailingIcon = {
            if (isFocused) {
                val painterResourceId =
                    if (passwordVisible) R.drawable.ic_visibility_on
                    else R.drawable.ic_visibility_off
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(painterResourceId),
                        contentDescription = description,
                    )
                }
            }
        },
        keyboardOptions =
            if (isFocused)
                KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                )
            else KeyboardOptions.Default,
        keyboardActions = KeyboardActions(onDone = { localFocusManager.clearFocus() }),
        singleLine = true,
        colors =
            TextFieldDefaults.colors()
                .copy(
                    focusedContainerColor = containerColor,
                    unfocusedContainerColor = containerColor,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = labelColor,
                ),
    )
    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@PreviewLightDarkBg
@Composable
fun LoginTextFieldPreview() {
    MoviesTheme { LoginTextField("Username", "") {} }
}

@PreviewLightDarkBg
@Composable
fun PasswordTextFieldPreview() {
    MoviesTheme { PasswordTextField("Password", "") {} }
}
