package com.raywenderlich.cinematic.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

  val _events = SingleLiveEvent<Events>()
  val events = _events as LiveData<Events>

}