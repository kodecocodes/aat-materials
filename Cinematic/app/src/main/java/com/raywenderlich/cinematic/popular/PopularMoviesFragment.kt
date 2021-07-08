package com.raywenderlich.cinematic.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.raywenderlich.cinematic.MoviesAdapter
import com.raywenderlich.cinematic.R
import com.raywenderlich.cinematic.databinding.FragmentPopularBinding
import com.raywenderlich.cinematic.model.Movie
import com.raywenderlich.cinematic.util.Events.Done
import com.raywenderlich.cinematic.util.Events.Loading
import com.raywenderlich.cinematic.util.MovieListClickListener
import org.koin.android.ext.android.inject

class PopularMoviesFragment : Fragment(R.layout.fragment_popular) {
  private var _binding: FragmentPopularBinding? = null
  private val binding get() = _binding!!

  private val viewModel: PopularMoviesViewModel by inject()
  private val popularAdapter: MoviesAdapter by inject()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    _binding = FragmentPopularBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    popularAdapter.setListener(object : MovieListClickListener {
      override fun onMovieClicked(movie: Movie) {
        findNavController().navigate(
            PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment(movie.id))
      }

    })
    binding.popularMoviesList.apply {
      adapter = popularAdapter
    }
    viewModel.getPopularMovies()
    attachObservers()
  }

  private fun attachObservers() {
    viewModel.movies.observe(viewLifecycleOwner, { movies ->
      popularAdapter.submitList(movies)
    })

    viewModel.events.observe(viewLifecycleOwner, { event ->
      when (event) {
        is Loading -> {
          binding.progressBar.visibility = View.VISIBLE
          binding.popularMoviesList.visibility = View.GONE
        }

        is Done -> {
          binding.progressBar.visibility = View.GONE
          binding.popularMoviesList.visibility = View.VISIBLE
        }
      }
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}