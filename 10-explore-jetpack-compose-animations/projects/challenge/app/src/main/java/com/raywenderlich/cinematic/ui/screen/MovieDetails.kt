/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.cinematic.ui.screen

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.raywenderlich.cinematic.details.MovieDetailsViewModel
import com.raywenderlich.cinematic.model.Cast
import com.raywenderlich.cinematic.model.Movie
import com.raywenderlich.cinematic.ui.components.*
import com.raywenderlich.cinematic.util.Constants
import com.raywenderlich.cinematic.util.Events

@ExperimentalAnimationApi
@Composable
fun MovieDetails(viewModel: MovieDetailsViewModel, movieId: Int) {
  viewModel.getMovieDetails(movieId)
  viewModel.getCast(movieId)

  val movie by viewModel.movie.observeAsState()
  val casts by viewModel.cast.observeAsState()
  val contentState by viewModel.events.observeAsState()

  Box(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState()),
    contentAlignment = Alignment.TopCenter
  ) {
    MovieDetailsContent(movie, casts, contentState, LocalContext.current) { movie ->
      if (movie.isFavorite) {
        viewModel.unsetMovieAsFavorite(movie.id)
      } else {
        viewModel.setMovieAsFavorite(movie.id)
      }
    }
  }
}

@ExperimentalAnimationApi
@Composable
fun MovieDetailsContent(
  movie: Movie?,
  casts: List<Cast>?,
  contentState: Events?,
  context: Context,
  onFavoriteButtonClick: (Movie) -> Unit
) {
  if (movie == null) {
    return
  }
  DetailsHeaderBackdrop(movie, context)
  MovieDetailsBody(movie, casts, contentState, onFavoriteButtonClick)
  DetailsPoster(movie)
}

@Composable
fun DetailsHeaderBackdrop(movie: Movie, context: Context) {
  Image(
    painter = rememberImagePainter(
      data = Constants.IMAGE_BASE + movie.backdropPath,
      builder = {
        transformations(BlurTransformation(context, radius = 25f))
        crossfade(true)
      },
    ),
    contentDescription = null,
    modifier = Modifier
      .height(300.dp)
      .fillMaxWidth(),
    contentScale = ContentScale.Crop,
  )
}

@ExperimentalAnimationApi
@Composable
fun MovieDetailsBody(
  movie: Movie,
  casts: List<Cast>?,
  contentState: Events?,
  onFavoriteButtonClick: (Movie) -> Unit
) {
  Surface(
    modifier = Modifier
      .padding(top = 250.dp)
      .fillMaxHeight(),
    elevation = 8.dp,
    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
  ) {
    Column(
      Modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = 650.dp)
        .padding(top = 120.dp),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = movie.title,
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center
      )

      RatingBar(
        currentRating = movie.rating,
        modifier = Modifier
          .align(Alignment.CenterHorizontally),
        ratingStyle = RatingStyle.LARGE
      )

      SectionHeader(text = "Overview")

      Overview(movie = movie)

      CastsRow(casts)

      AddToFavoritesButton(
        movie = movie,
        Modifier.padding(vertical = 32.dp),
        contentState,
        onFavoriteButtonClick
      )

    }
  }
}

@Composable
fun SectionHeader(text: String) {
  Spacer(modifier = Modifier.padding(top = 32.dp))

  Text(
    style = MaterialTheme.typography.subtitle2,
    text = text,
    textAlign = TextAlign.Start,
    modifier = Modifier
      .fillMaxWidth()
      .padding(18.dp)
  )
}

@Composable
fun DetailsPoster(movie: Movie) {
  Surface(
    modifier = Modifier
      .padding(top = 142.dp)
      .size(150.dp, 210.dp),
    shape = RoundedCornerShape(8.dp),
    elevation = 12.dp
  ) {
    Image(
      painter = rememberImagePainter(
        data = Constants.IMAGE_BASE + movie.posterPath,
        builder = {
          crossfade(true)
        }
      ),
      contentDescription = null,
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop,
    )
  }
}