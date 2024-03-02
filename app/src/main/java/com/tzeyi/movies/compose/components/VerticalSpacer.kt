package com.tzeyi.movies.compose.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(space: Int = 20) {
    Spacer(Modifier.padding(top = space.dp))
}
