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

  fun getMovieDetails(id: Int) {
    _events.value = Events.Loading
    viewModelScope.launch(Dispatchers.IO) {
      repository.getMovie(id).collect { movie ->
        withContext(Dispatchers.Main) {
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

  fun setMovieAsFavourite(id: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.setFavourite(id)
    }
  }

  fun unsetMovieAsFavourite(id: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      repository.removeFavourite(id)
    }
  }
}