package com.raywenderlich.cinematic.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.raywenderlich.cinematic.util.splitToWholeAndFraction


enum class RatingStyle {
  SMALL, LARGE
}

@Composable
fun RatingBar(
  range: IntRange = 1..5,
  currentRating: Float,
  modifier: Modifier,
  ratingStyle: RatingStyle = RatingStyle.SMALL,
) {
  Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.padding(top = 8.dp)) {
    Text(
      text = currentRating.toString(),
      color = if (ratingStyle == RatingStyle.SMALL) {
        Color.Black
      } else MaterialTheme.colors.onSurface,
      style = if (ratingStyle == RatingStyle.SMALL) {
        MaterialTheme.typography.h4
      } else MaterialTheme.typography.h3,
      modifier = Modifier.padding(end = 8.dp)
    )
    LazyRow(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      items(range.toList()) { value ->
        RatingItem(
          value = value,
          currentRating = currentRating,
          ratingStyle = ratingStyle
        )
      }
    }
  }
}

@Composable
fun RatingItem(value: Int, currentRating: Float, ratingStyle: RatingStyle) {
  val (whole, fraction) = currentRating.splitToWholeAndFraction()
  val imageVector = if (value <= whole) {
    Icons.Default.Star
  } else if (value.toFloat() == whole + 1 && fraction in 0.5..1.0) {
    Icons.Default.StarHalf
  } else {
    Icons.Outlined.StarBorder
  }

  val color = if (ratingStyle == RatingStyle.SMALL) Color.Gray
  else {
    if (value <= whole || value.toFloat() == whole + 1 && fraction in 0.5..0.9) {
      Color.Yellow
    } else Color.Gray
  }

  Icon(
    imageVector = imageVector,
    contentDescription = null,
    tint = color
  )
}
