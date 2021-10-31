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
package com.raywenderlich.cinematic.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.Snackbar
import com.raywenderlich.cinematic.MoviesListAdapter
import com.raywenderlich.cinematic.MoviesRecyclerAdapter
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
    private val popularAdapter = MoviesRecyclerAdapter()

    private var firstVisibleItem: Int = 0
    private val listAdapter by lazy {
        MoviesListAdapter(object : MovieListClickListener {
            override fun onMovieClicked(movie: Movie) {
                findNavController().navigate(
                    PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailsFragment(
                        movie.id
                    )
                )
            }
        })
    }

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
        binding.scrollUp.hide()

        binding.popularMoviesList.apply {
            adapter = listAdapter
            divider = null

            /**
             * Note: You can achieve the same behavior with the RecyclerView.OnScrollListener, by
             * reading the RecyclerView.layoutManager.findFirstVisibleItemPosition() and comparing
             * it to the first item index (0).
             */
            setOnScrollListener(object : AbsListView.OnScrollListener {
                override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                    if (firstVisibleItem == 0 || scrollState != SCROLL_STATE_IDLE) {
                        binding.scrollUp.hide()
                    } else {
                        binding.scrollUp.show()
                    }
                }

                override fun onScroll(
                    view: AbsListView?,
                    firstVisibleItem: Int,
                    visibleItemCount: Int,
                    totalItemCount: Int
                ) {
                    this@PopularMoviesFragment.firstVisibleItem = firstVisibleItem
                }
            })
        }

        binding.scrollUp.setOnClickListener {
            binding.popularMoviesList.smoothScrollToPosition(0)
        }

        viewModel.getPopularMovies()
        attachObservers()
    }

    private fun showBottomSheet(direction: Int) {
        val text =
            getString(if (direction == ItemTouchHelper.RIGHT) R.string.item_added else R.string.item_removed)

        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }

    private fun attachObservers() {
        viewModel.movies.observe(viewLifecycleOwner, { movies ->
            listAdapter.setItems(movies)
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
