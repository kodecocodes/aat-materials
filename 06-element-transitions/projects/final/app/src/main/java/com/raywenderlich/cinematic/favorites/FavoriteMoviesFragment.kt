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
package com.raywenderlich.cinematic.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.raywenderlich.cinematic.AnimationViewModel
import com.raywenderlich.cinematic.MoviesAdapter
import com.raywenderlich.cinematic.R
import com.raywenderlich.cinematic.databinding.FragmentFavoritesBinding
import com.raywenderlich.cinematic.model.Movie
import com.raywenderlich.cinematic.util.Events
import com.raywenderlich.cinematic.util.MovieListClickListener
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import kotlin.math.hypot

class FavoriteMoviesFragment : Fragment(R.layout.fragment_favorites) {
  private var _binding: FragmentFavoritesBinding? = null
  private val binding get() = _binding!!
  private val viewModel: FavoriteMoviesViewModel by inject()
  private val animationViewModel: AnimationViewModel by sharedViewModel()
  private val favoritesAdapter: MoviesAdapter by inject()

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
    favoritesAdapter.setListener(object : MovieListClickListener {
      override fun onMovieClicked(movie: Movie) {
        findNavController().navigate(
            FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(movie.id))
      }

    })
    binding.favoriteMoviesList.apply {
      adapter = favoritesAdapter
    }
    viewModel.getFavoriteMovies()
    attachObservers()
  }

  private fun animateContentIn() {
    binding.root.doOnPreDraw {
      val view = binding.root
      val centerX = view.width
      val centerY = view.height
      val finalRadius = hypot(view.width.toDouble(), view.height.toDouble())
      val anim = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0f, finalRadius.toFloat())
      anim.duration = 600
      anim.start()
    }
  }

  private fun attachObservers() {
    viewModel.movies.observe(viewLifecycleOwner, { movies ->
      favoritesAdapter.submitList(movies)
    })
    animationViewModel.animateFavoriteEntranceLiveData.observe(viewLifecycleOwner) { shouldAnimate ->
      if (shouldAnimate) {
        animateContentIn()
      }
    }
    viewModel.events.observe(viewLifecycleOwner, { event ->
      when (event) {
        is Events.Loading -> {
          binding.progressBar.visibility = View.VISIBLE
          binding.favoriteMoviesList.visibility = View.GONE
        }

        is Events.Done -> {
          binding.progressBar.visibility = View.GONE
          binding.favoriteMoviesList.visibility = View.VISIBLE
        }
      }
    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
