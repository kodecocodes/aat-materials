package com.raywenderlich.cinematic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.raywenderlich.cinematic.databinding.ItemMovieBinding
import com.raywenderlich.cinematic.model.Movie
import com.raywenderlich.cinematic.util.Constants.IMAGE_BASE
import com.raywenderlich.cinematic.util.MovieListClickListener
import com.raywenderlich.cinematic.util.MoviesDiffCallback

class MoviesAdapter : ListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MoviesDiffCallback()) {

  private var listener: MovieListClickListener? = null

  fun setListener(listener: MovieListClickListener) {
    this.listener = listener
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
    val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return MoviesViewHolder(binding)
  }

  override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class MoviesViewHolder(val binding: ItemMovieBinding) :
      RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
      binding.movieName.text = movie.title
      binding.movieRating.rating = movie.rating
      binding.ratingValue.text = movie.rating.toString()
      binding.movieInfo.text = movie.movieInfo
      binding.background.load(IMAGE_BASE + movie.backdropPath) {
        crossfade(true)
      }

      binding.root.setOnClickListener {
        listener?.onMovieClicked(movie)
      }
    }
  }


}