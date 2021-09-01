package com.raywenderlich.cinematic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnimationViewModel : ViewModel() {
  val animateEntranceLiveData = MutableLiveData(false)
}
