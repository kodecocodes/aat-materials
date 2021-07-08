package com.hackertronix.cinematic.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hackertronix.cinematic.data.repository.MoviesRepository
import com.hackertronix.cinematic.model.Movie
import com.hackertronix.cinematic.util.BaseViewModel
import com.hackertronix.cinematic.util.Events
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PopularMoviesViewModel constructor(private val repository: MoviesRepository) :
    BaseViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies = _movies as LiveData<List<Movie>>

    fun getPopularMovies() {
        _events.value = Events.Loading

        viewModelScope.launch {
            repository.getPopularMovies().collect { result ->
                _events.value = Events.Done
                _movies.value = result
            }
        }
    }

    fun setMovieAsFavourite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setFavourite(id)
        }
    }
}