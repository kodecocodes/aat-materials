package com.raywenderlich.cinematic.util

import androidx.recyclerview.widget.DiffUtil
import com.raywenderlich.cinematic.model.Movie

class MoviesDiffCallback : DiffUtil.ItemCallback<Movie>() {
  override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
    return oldItem == newItem
  }

}