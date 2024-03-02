package com.tzeyi.movies.compose.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tzeyi.movies.R
import com.tzeyi.movies.ui.theme.MoviesTheme
import com.tzeyi.movies.utilities.debugPlaceholder

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    imdbId: String = "",
    title: String = "",
    imageUrl: String = "",
    onClick: (String) -> Unit = {},
) {
    ElevatedCard(
        onClick = { onClick(imdbId) },
        modifier = modifier.aspectRatio(2 / 3f),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box {
            AsyncImage(
                model =
                    ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                placeholder = debugPlaceholder(R.drawable.captain_marvel),
                error = painterResource(R.drawable.poster_not_yet_available),
                contentDescription = "Poster of $title",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
            )
            Text(
                text = title,
                color = Color.White,
                modifier =
                    Modifier.fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.BottomStart)
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                1F to Color.Black.copy(alpha = 1F),
                            ),
                        )
                        .padding(
                            start = 12.dp,
                            top = 100.dp,
                            bottom = 12.dp,
                            end = 12.dp,
                        ),
                maxLines = 2,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
fun MovieListItemPreview() {
    MoviesTheme { MovieListItem(title = "Captain Marvel") }
}
