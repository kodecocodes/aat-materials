package com.hackertronix.cinematic.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hackertronix.cinematic.data.repository.MoviesRepository
import com.hackertronix.cinematic.model.Cast
import com.hackertronix.cinematic.model.Movie
import com.hackertronix.cinematic.util.BaseViewModel
import com.hackertronix.cinematic.util.Events
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