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
package com.raywenderlich.cinematic.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.raywenderlich.cinematic.data.repository.MoviesRepository
import com.raywenderlich.cinematic.model.Cast
import com.raywenderlich.cinematic.model.Movie
import com.raywenderlich.cinematic.util.BaseViewModel
import com.raywenderlich.cinematic.util.Events
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel constructor(private val repository: MoviesRepository) :
  BaseViewModel() {

  private val _movie = MutableLiveData<Movie>()
  val movie = _movie as LiveData<Movie>

  private val _cast = MutableLiveData<List<Cast>>()
  val cast = _cast as LiveData<List<Cast>>

  private var _shouldAnimate = true
  val shouldAnimate: Boolean
    get() = _shouldAnimate

  fun getMovieDetails(id: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.getMovie(id).collect { movie ->
        withContext(Dispatchers.Main) {
          _events.value = Events.Done
          _movie.value = movie
        }
      }
    }
  }

  fun getCast(id: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.getCastDetails(id).also { cast ->
        withContext(Dispatchers.Main) {
          _cast.value = cast.filter {
            !it.profilePath.isNullOrEmpty()
          }
        }
      }
    }
  }

  fun setMovieAsFavorite(id: Int) {
    _events.value = Events.Loading
    viewModelScope.launch(Dispatchers.IO) {
      repository.setFavorite(id)
      _shouldAnimate = false
    }
  }

  fun unsetMovieAsFavorite(id: Int) {
    _events.value = Events.Loading
    viewModelScope.launch(Dispatchers.IO) {
      repository.removeFavorite(id)
      _shouldAnimate = false
    }
  }
}