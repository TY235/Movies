package com.tzeyi.movies.compose.moviedetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tzeyi.movies.compose.annotations.PreviewLightDarkBg
import com.tzeyi.movies.data.dto.Rating
import com.tzeyi.movies.ui.theme.MoviesTheme

@Composable
fun RatingsItem(rating: Rating, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier =
            modifier
                .height(100.dp)
                .aspectRatio(2.5f)
                .shadow(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = MaterialTheme.colorScheme.primary,
                    spotColor = MaterialTheme.colorScheme.primary,
                ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = rating.source,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                modifier =
                    Modifier.align(Alignment.TopStart)
                        .padding(start = 15.dp, top = 18.dp, end = 15.dp),
            )
            Text(
                text = rating.value,
                fontWeight = FontWeight.Black,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier =
                    Modifier.align(Alignment.BottomEnd)
                        .padding(start = 15.dp, bottom = 18.dp, end = 15.dp),
            )
        }
    }
}

@PreviewLightDarkBg
@Composable
fun RatingsItemPreview() {
    MoviesTheme { RatingsItem(Rating("Internet Movie Database", "6.8/10")) }
}
