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
package com.raywenderlich.cinematic.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.raywenderlich.cinematic.R
import com.raywenderlich.cinematic.model.Movie
import com.raywenderlich.cinematic.util.Events

@Composable
fun AddToFavoritesButton(
  movie: Movie,
  modifier: Modifier = Modifier,
  contentState: Events?,
  onFavoriteButtonClick: (Movie) -> Unit
) {
  Column(
    modifier = modifier
      .padding(top = 32.dp)
      .fillMaxSize(),
    verticalArrangement = Arrangement.Bottom,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    if (contentState is Events.Loading) {
      CircularProgressIndicator(
        modifier = Modifier.padding(top = 8.dp),
        strokeWidth = 2.5.dp,
        color = Color.Black
      )
    } else {
      Button(
        modifier = Modifier
          .size(250.dp, 56.dp),
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(
          backgroundColor = MaterialTheme.colors.secondary
        ),
        onClick = {
          onFavoriteButtonClick(movie)
        },
      ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

          Icon(
            imageVector = if (movie.isFavorite) {
              Icons.Default.Favorite
            } else {
              Icons.Default.FavoriteBorder
            },
            contentDescription = null
          )

          Spacer(modifier = Modifier.width(16.dp))

          Text(
            text = if (movie.isFavorite) {
              stringResource(id = R.string.remove_from_favorites)
            } else {
              stringResource(id = R.string.add_to_favorites)
            },
            style = MaterialTheme.typography.button,
            maxLines = 1
          )
        }

      }
    }
  }
}