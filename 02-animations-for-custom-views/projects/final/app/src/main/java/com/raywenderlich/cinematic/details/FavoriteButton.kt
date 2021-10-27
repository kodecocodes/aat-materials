package com.raywenderlich.cinematic.details

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.raywenderlich.cinematic.R
import com.raywenderlich.cinematic.databinding.ViewFavoriteButtonBinding
import com.raywenderlich.cinematic.util.DisplayMetricsUtil

class FavoriteButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding: ViewFavoriteButtonBinding =
        ViewFavoriteButtonBinding.inflate(LayoutInflater.from(context), this)

    private val animators = mutableListOf<ValueAnimator>()

    init {
        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val padding = DisplayMetricsUtil.dpToPx(16)
        setPadding(padding, 0, padding, padding)
    }

    fun setOnFavoriteClickListener(listener: () -> Unit) {
        binding.favoriteButton.setOnClickListener {
            listener.invoke()
        }
    }

    fun setFavorite(isFavorite: Boolean) {
        binding.favoriteButton.apply {
            icon = if (isFavorite) {
                AppCompatResources.getDrawable(context, R.drawable.ic_baseline_favorite_24)
            } else {
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_baseline_favorite_border_24
                )
            }
            text = if (isFavorite) {
                context.getString(R.string.remove_from_favorites)
            } else {
                context.getString(R.string.add_to_favorites)
            }
        }
        hideProgress()
    }

    fun showProgress() {
        binding.progressBar.isVisible = true
        binding.favoriteButton.apply {
            icon = null
            text = null

            isClickable = false
            isFocusable = false

            animateButton()
        }
    }

    private fun animateButton() {

        val initialWidth = binding.favoriteButton.measuredWidth
        val finalWidth = binding.favoriteButton.measuredHeight

        val initialTextSize = binding.favoriteButton.textSize

        val widthAnimator = ValueAnimator.ofInt(initialWidth, finalWidth)
        val textSizeAnimator = ValueAnimator.ofFloat(initialTextSize, 0f)
        val alphaAnimator = ObjectAnimator.ofFloat(binding.progressBar, "alpha", 0f, 1f)

        widthAnimator.duration = 1000
        textSizeAnimator.apply {
            interpolator = OvershootInterpolator()
            duration = 1000
        }
        alphaAnimator.duration = 1000

        widthAnimator.addUpdateListener {
            binding.favoriteButton.updateLayoutParams {
                this.width = it.animatedValue as Int
            }
        }

        textSizeAnimator.addUpdateListener {
            binding.favoriteButton.textSize =
                (it.animatedValue as Float) / resources.displayMetrics.density
        }

        alphaAnimator.addUpdateListener {
            binding.progressBar.alpha = it.animatedValue as Float
        }

        binding.progressBar.apply {
            alpha = 0f
            isVisible = true
        }

        widthAnimator.start()
        textSizeAnimator.start()
        alphaAnimator.start()

        animators.addAll(
            listOf(
                widthAnimator,
                textSizeAnimator,
                alphaAnimator
            )
        )
    }

    private fun hideProgress() {
        binding.progressBar.isVisible = false
        binding.favoriteButton.apply {
            extend()
            isClickable = true
            isFocusable = true
        }
        reverseAnimation()
    }

    private fun reverseAnimation() {
        animators.forEach { animation ->
            animation.reverse()
            if (animators.indexOf(animation) == animators.lastIndex) {
                animation.doOnEnd {
                    animators.clear()
                }
            }
        }
    }


}