package com.hackertronix.cinematic.login

import androidx.lifecycle.LiveData
import com.hackertronix.cinematic.util.BaseViewModel
import com.hackertronix.cinematic.util.SingleLiveEvent

class AuthViewModel : BaseViewModel() {
    private val _showMain = SingleLiveEvent<Unit>()
    val showMain = _showMain as LiveData<Unit>

    private val _showLogin = SingleLiveEvent<Unit>()
    val showLogin = _showLogin as LiveData<Unit>

    private val _showSignUp = SingleLiveEvent<Unit>()
    val showSignUp = _showSignUp as LiveData<Unit>

    fun onLoginPressed() = _showMain.postValue(Unit)

    fun onSignupPressed() = _showMain.postValue(Unit)

    fun onSignInPressed() = _showLogin.postValue(Unit)

    fun onNewUserPressed() = _showSignUp.postValue(Unit)
}