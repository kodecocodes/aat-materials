package com.raywenderlich.cinematic.util

import android.content.res.Resources
import kotlin.math.roundToInt

object DisplayMetricsUtil {

    fun dpToPx(dp: Int): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (dp * density).roundToInt()
    }

    fun dpToPx(dp: Float): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (dp * density).roundToInt()
    }
}