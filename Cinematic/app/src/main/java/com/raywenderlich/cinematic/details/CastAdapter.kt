package com.raywenderlich.cinematic.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.raywenderlich.cinematic.databinding.ItemCastBinding
import com.raywenderlich.cinematic.model.Cast
import com.raywenderlich.cinematic.util.CastDiffCallback
import com.raywenderlich.cinematic.util.Constants.IMAGE_BASE

class CastAdapter : ListAdapter<Cast, CastAdapter.CastViewHolder>(CastDiffCallback()) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
    val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return CastViewHolder(binding)
  }

  override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class CastViewHolder(val binding: ItemCastBinding) :
      RecyclerView.ViewHolder(binding.root) {
    fun bind(cast: Cast) {
      binding.castImage.load(IMAGE_BASE + cast.profilePath) {
        crossfade(true)
        transformations(CircleCropTransformation())
      }
    }

  }
}