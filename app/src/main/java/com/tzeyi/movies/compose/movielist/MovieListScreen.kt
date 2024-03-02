package com.tzeyi.movies.compose.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.compose.components.SearchBar
import com.tzeyi.movies.compose.components.VerticalSpacer
import com.tzeyi.movies.ui.theme.MoviesTheme
import com.tzeyi.movies.viewmodels.MovieListViewModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    onMovieClick: (String) -> Unit,
) {
    val movies by viewModel.movies.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    var hideKeyboard by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        content = { innerPadding ->
            Column(
                modifier =
                    Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) {
                            hideKeyboard = true
                        }
                        .padding(
                            start = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                            top = innerPadding.calculateTopPadding(),
                            end = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                        ),
            ) {
                VerticalSpacer(5)
                SearchBar(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    searchText = searchText,
                    onSearchChange = viewModel::onSearchTextChange,
                    hideKeyboard = hideKeyboard,
                    onFocusClear = { hideKeyboard = false },
                )
                VerticalSpacer(5)
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxHeight().weight(1F),
                    columns = GridCells.Fixed(2),
                    contentPadding =
                        PaddingValues(
                            start = 15.dp,
                            top = 15.dp,
                            end = 15.dp,
                            bottom = innerPadding.calculateBottomPadding(),
                        ),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                ) {
                    items(
                        items = movies,
                        key = { it.imdbId },
                    ) {
                        MovieListItem(
                            imdbId = it.imdbId,
                            title = it.title,
                            imageUrl = it.poster,
                            onClick = onMovieClick,
                        )
                    }
                }
            }
        },
    )
}

@PreviewLightDarkBg
@Composable
fun MovieListScreenPreview() {
    MoviesTheme { MovieListScreen {} }
}
