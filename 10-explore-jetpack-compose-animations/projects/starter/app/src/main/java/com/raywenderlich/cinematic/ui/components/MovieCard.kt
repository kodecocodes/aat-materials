package com.raywenderlich.cinematic.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.hackertronix.cinematic.util.FakeDataFactory.makeMovie
import com.raywenderlich.cinematic.model.Movie
import com.raywenderlich.cinematic.util.Constants

@Composable
fun MovieCard(movie: Movie, onMovieClicked: (Movie) -> Unit) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .height(250.dp)
      .padding(start = 8.dp, top = 8.dp, end = 8.dp)
      .clip(RoundedCornerShape(16.dp))
      .clickable {
        onMovieClicked.invoke(movie)
      },
    elevation = 8.dp,
  ) {
    Box(contentAlignment = Alignment.BottomCenter) {
      Image(
        painter = rememberImagePainter(
          data = Constants.IMAGE_BASE + movie.backdropPath,
          builder = {
            crossfade(true)
          }
        ),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
      )

      Column(
        Modifier
          .fillMaxWidth()
          .height(120.dp)
          .background(
            MaterialTheme.colors.secondary,
            RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp)
          )
      ) {

      }

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 16.dp, start = 16.dp),
        horizontalAlignment = Alignment.Start
      ) {
        Text(
          text = movie.title,
          style = MaterialTheme.typography.h6,
          color = Color.Black,
          textAlign = TextAlign.Start,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )
        Text(
          modifier = Modifier.padding(top = 8.dp),
          text = movie.movieInfo,
          style = MaterialTheme.typography.subtitle1,
          color = Color.Black,
          textAlign = TextAlign.Start,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )
        RatingBar(
          currentRating = movie.rating,
          modifier = Modifier.fillMaxWidth()
        )
      }
    }

  }
}


@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewMovieCard() {
  MovieCard(movie = makeMovie(), onMovieClicked = {})
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewMovieCardDark() {
  MovieCard(movie = makeMovie(), onMovieClicked = {})
}