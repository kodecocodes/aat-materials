package com.raywenderlich.cinematic.details

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.raywenderlich.cinematic.R
import com.raywenderlich.cinematic.databinding.ViewFavouriteButtonBinding
import com.raywenderlich.cinematic.util.DisplayMetricsUtil

class FavouriteButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding: ViewFavouriteButtonBinding =
        ViewFavouriteButtonBinding.inflate(LayoutInflater.from(context), this)

    init {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val padding = DisplayMetricsUtil.dpToPx(16)
        setPadding(padding, 0, padding, padding)
    }

    fun setOnFavouriteClickListener(listener: () -> Unit) {
        binding.favouriteButton.setOnClickListener {
            listener.invoke()
        }
    }

    fun setFavourite(isFavourite: Boolean) {
        binding.favouriteButton.apply {
            icon = if (isFavourite) {
                AppCompatResources.getDrawable(context, R.drawable.ic_baseline_favorite_24)
            } else {
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_baseline_favorite_border_24
                )
            }
            text = if (isFavourite) {
                context.getString(R.string.remove_from_favourites)
            } else {
                context.getString(R.string.add_to_favourites)
            }
        }

        hideProgress()
    }

    fun showProgress() {
        binding.progressBar.isVisible = true
        binding.favouriteButton.apply {
            icon = null
            text = null

            isClickable = false
            isFocusable = false

        }

        //TODO animate button
    }


    private fun hideProgress() {
        binding.progressBar.isVisible = false
        binding.favouriteButton.apply {
            extend()
            isClickable = true
            isFocusable = true
        }

        //TODO reverse the animations
    }

}