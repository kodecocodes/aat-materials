package com.raywenderlich.cinematic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import coil.load
import com.raywenderlich.cinematic.databinding.ItemMovieBinding
import com.raywenderlich.cinematic.model.Movie
import com.raywenderlich.cinematic.util.Constants
import com.raywenderlich.cinematic.util.MovieListClickListener

class MoviesListAdapter(private val listener: MovieListClickListener) : BaseAdapter() {

    private val items: MutableList<Movie> = mutableListOf()

    override fun getItem(position: Int): Movie {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }

    public fun setItems(items: List<Movie>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = getItem(position)
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        setupView(binding, item)

        return binding.root
    }

    private fun setupView(binding: ItemMovieBinding, movie: Movie) {
        binding.movieName.text = movie.title
        binding.movieRating.rating = movie.rating
        binding.ratingValue.text = movie.rating.toString()
        binding.movieInfo.text = movie.movieInfo
        binding.background.load(Constants.IMAGE_BASE + movie.backdropPath) {
            crossfade(true)
        }

        binding.root.setOnClickListener {
            listener.onMovieClicked(movie)
        }
    }
}