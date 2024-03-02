package com.tzeyi.movies.compose.components

import android.view.KeyEvent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onSearchChange: (String) -> Unit = {},
    hideKeyboard: Boolean = false,
    onFocusClear: () -> Unit = {},
    placeholderText: String = "Search movies"
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchChange,
        placeholder = { Text(placeholderText) },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search logo") },
        modifier =
            modifier.fillMaxWidth().onKeyEvent { event ->
                if (event.key.nativeKeyCode == KeyEvent.KEYCODE_BACK) {
                    focusManager.clearFocus()
                    true
                } else {
                    false
                }
            },
        shape = MaterialTheme.shapes.extraLarge,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        singleLine = true,
    )

    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@PreviewLightDarkBg
@Composable
private fun SearchBarPreview() {
    MoviesTheme { SearchBar() }
}
