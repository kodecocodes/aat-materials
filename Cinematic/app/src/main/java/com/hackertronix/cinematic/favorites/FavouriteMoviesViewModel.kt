package com.hackertronix.cinematic.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackertronix.cinematic.data.repository.MoviesRepository
import com.hackertronix.cinematic.model.Movie
import com.hackertronix.cinematic.util.BaseViewModel
import com.hackertronix.cinematic.util.Events
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouriteMoviesViewModel constructor(private val repository: MoviesRepository) : BaseViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies = _movies as LiveData<List<Movie>>

    fun getFavouriteMovies() {
        _events.value = Events.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavouriteMovies().collect { result ->
                withContext(Dispatchers.Main) {
                    _movies.value = result
                    _events.value = Events.Done
                }
            }
        }
    }
}