package com.tzeyi.movies.compose.moviedetails

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tzeyi.movies.compose.annotations.PreviewBg
import com.tzeyi.movies.compose.components.BackButton
import com.tzeyi.movies.compose.components.RatingBar
import com.tzeyi.movies.compose.components.VerticalSpacer
import com.tzeyi.movies.data.dto.MovieDetailsResponse
import com.tzeyi.movies.ui.theme.MoviesTheme
import com.tzeyi.movies.utilities.Result
import com.tzeyi.movies.viewmodels.MovieDetailsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(
    imdbId: String? = "",
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val result by viewModel.movieDetails.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { if (imdbId != null) viewModel.getMovieDetails(imdbId) }

    when (result) {
        is Result.Loading -> LoadingScreen()
        is Result.Error -> NetworkErrorScreen(onBackClick = onBackClick)
        is Result.Success -> {
            MovieDetailsContent(
                movie = (result as Result.Success).data as MovieDetailsResponse,
                onBackClick = onBackClick,
            )
        }
    }
}

@Composable
fun MovieDetailsContent(movie: MovieDetailsResponse, onBackClick: () -> Unit) {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        content = { innerPadding ->
            val startPadding = 18.dp + innerPadding.calculateStartPadding(LayoutDirection.Ltr)
            val endPadding = 18.dp + innerPadding.calculateEndPadding(LayoutDirection.Ltr)

            Column(
                Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                MoviePoster(
                    insetsStart = innerPadding.calculateStartPadding(LayoutDirection.Ltr),
                    insetsTop = innerPadding.calculateTopPadding(),
                    insetsEnd = innerPadding.calculateEndPadding(LayoutDirection.Ltr),
                    poster = movie.poster,
                    title = movie.title,
                    onBackClick = onBackClick,
                )
                VerticalSpacer(30)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RatingBar(
                        modifier = Modifier.padding(start = startPadding, end = 13.dp),
                        rating = movie.imdbRating.toFloat() / 2,
                        spaceBetween = 1.dp,
                    )
                    Text(
                        text = "${movie.imdbRating}/10",
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        text = "${movie.imdbVotes} Ratings",
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        lineHeight = 15.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        modifier = Modifier.padding(start = 13.dp, end = 13.dp),
                    )
                }
                VerticalSpacer()
                Text(
                    text = "${movie.title} (${movie.year})",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 27.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    modifier = Modifier.padding(start = startPadding, end = endPadding),
                )
                VerticalSpacer(15)
                Text(
                    text = movie.genre,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = startPadding, end = endPadding),
                )
                VerticalSpacer(35)
                Text(
                    text = "Plot Summary",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 23.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    modifier = Modifier.padding(start = startPadding, end = endPadding),
                )
                VerticalSpacer(15)
                Text(
                    text = movie.plot,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    modifier = Modifier.padding(start = startPadding, end = endPadding),
                )
                VerticalSpacer(35)
                Text(
                    text = "Other Ratings",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 23.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    modifier = Modifier.padding(start = startPadding, end = endPadding),
                )
                VerticalSpacer(15)
                LazyRow(
                    contentPadding = PaddingValues(start = startPadding, end = endPadding),
                    horizontalArrangement = Arrangement.spacedBy(18.dp),
                ) {
                    items(
                        items = movie.ratings,
                        key = { it.source },
                    ) { rating ->
                        RatingsItem(rating)
                    }
                }
                VerticalSpacer(50)
            }
        },
    )
}
