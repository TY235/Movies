package com.tzeyi.movies.compose.moviedetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tzeyi.movies.R
import com.tzeyi.movies.compose.components.CloseButton
import com.tzeyi.movies.ui.theme.MoviesTheme
import com.tzeyi.movies.utilities.debugPlaceholder

@Composable
fun MoviePoster(
    insetsStart: Dp = 0.dp,
    insetsTop: Dp = 0.dp,
    insetsEnd: Dp = 0.dp,
    poster: String = "",
    title: String = "",
    onBackClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxWidth().aspectRatio(1.3f),
    ) {
        BlurPoster(poster, title)
        TriangleOverlay()
        CloseButton(
            modifier =
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = 18.dp + insetsTop,
                        end = 18.dp + insetsEnd,
                    ),
            onClick = onBackClick,
        )
        SmallPoster(
            poster,
            title,
            modifier =
                Modifier.align(Alignment.BottomStart)
                    .padding(start = 20.dp + insetsStart, bottom = 0.dp)
                    .fillMaxHeight(.8f),
        )
    }
}

@Composable
fun BlurPoster(poster: String, title: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(poster).crossfade(true).build(),
        placeholder = debugPlaceholder(R.drawable.captain_marvel),
        error = painterResource(R.drawable.poster_not_yet_available),
        contentDescription = "Poster of $title",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize().blur(10.dp),
    )
}

@Composable
fun TriangleOverlay() {
    val surfaceColor = MaterialTheme.colorScheme.surface
    Spacer(
        modifier =
            Modifier.fillMaxSize().drawWithContent {
                drawContent()
                val path =
                    Path().apply {
                        moveTo(0f, size.height * .6f)
                        lineTo(0f, size.height)
                        lineTo(size.width, size.height)
                        lineTo(0f, size.height * .6f)
                        close()
                    }
                drawPath(path = path, color = surfaceColor)
            },
    )
}

@Composable
fun SmallPoster(poster: String, title: String, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier.aspectRatio(2 / 3f),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box {
            AsyncImage(
                model =
                    ImageRequest.Builder(LocalContext.current).data(poster).crossfade(true).build(),
                placeholder = debugPlaceholder(R.drawable.captain_marvel),
                error = painterResource(R.drawable.poster_not_yet_available),
                contentDescription = "Poster of $title",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Preview
@Composable
private fun MoviePosterPreview() {
    MoviesTheme { MoviePoster() }
}
