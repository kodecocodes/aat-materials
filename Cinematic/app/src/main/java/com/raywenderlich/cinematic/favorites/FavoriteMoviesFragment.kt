package com.raywenderlich.cinematic.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.raywenderlich.cinematic.MoviesAdapter
import com.raywenderlich.cinematic.R
import com.raywenderlich.cinematic.databinding.FragmentFavoritesBinding
import com.raywenderlich.cinematic.model.Movie
import com.raywenderlich.cinematic.util.Events
import com.raywenderlich.cinematic.util.MovieListClickListener
import org.koin.android.ext.android.inject

class FavoriteMoviesFragment : Fragment(R.layout.fragment_favorites) {
  private var _binding: FragmentFavoritesBinding? = null
  private val binding get() = _binding!!

  private val viewModel: FavouriteMoviesViewModel by inject()
  private val favouritesAdapter: MoviesAdapter by inject()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    favouritesAdapter.setListener(object : MovieListClickListener {
      override fun onMovieClicked(movie: Movie) {
        findNavController().navigate(
            FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(movie.id))
      }

    })
    binding.favouriteMoviesList.apply {
      adapter = favouritesAdapter
    }
    viewModel.getFavouriteMovies()
    attachObservers()
  }

  private fun attachObservers() {
    viewModel.movies.observe(viewLifecycleOwner, { movies ->
      favouritesAdapter.submitList(movies)
    })

    viewModel.events.observe(viewLifecycleOwner, { event ->
      when (event) {
        is Events.Loading -> {
          binding.progressBar.visibility = View.VISIBLE
          binding.favouriteMoviesList.visibility = View.GONE
        }

        is Events.Done -> {
          binding.progressBar.visibility = View.GONE
          binding.favouriteMoviesList.visibility = View.VISIBLE
        }
      }
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}