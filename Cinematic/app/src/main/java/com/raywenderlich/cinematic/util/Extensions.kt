package com.raywenderlich.cinematic.util

import java.text.DecimalFormat
import java.util.*

fun Float.convertToFiveStarScale(): Float {
  val decimalFormat = DecimalFormat("#.#")
  return decimalFormat.format(this / 2).toFloat()
}

fun Float.splitToWholeAndFraction(): Pair<Float, Float> {
  val fraction = this % 1
  val whole = this - fraction
  return Pair(whole, fraction)
}

fun String.toLanguageName(): String {
  val locale = Locale(this)
  return locale.displayName
}

fun String.toYear(): String {
  return this.split("-").first()
}