package com.hackertronix.cinematic.util

import androidx.recyclerview.widget.DiffUtil
import com.hackertronix.cinematic.model.Cast

class CastDiffCallback: DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }
}