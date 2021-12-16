package com.raywenderlich.cinematic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimationViewModel : ViewModel() {
  val animateFavoriteEntranceLiveData = MutableLiveData(false)
  val animatePopularEntranceLiveData = MutableLiveData(false)
}
