package com.raywenderlich.cinematic.popular

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class MyItemAnimator : DefaultItemAnimator() {

  override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
    if (holder != null) {
      holder.itemView.scaleX = 0f
      holder.itemView.scaleY = 0f
      holder.itemView.animate()
        .scaleX(1f)
        .scaleY(1f)
        .setDuration(1000)
        .start()
      return true
    }

    return super.animateAdd(holder)
  }
}