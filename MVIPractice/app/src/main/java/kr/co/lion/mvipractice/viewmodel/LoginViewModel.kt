package kr.co.lion.mvipractice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.co.lion.mvipractice.intent.LoginIntent
import kr.co.lion.mvipractice.model.LoginState

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EnterEmail -> {
                _state.value = _state.value.copy(email = intent.email)
            }
            is LoginIntent.EnterPassword -> {
                _state.value = _state.value.copy(password = intent.password)
            }
            is LoginIntent.SubmitLogin -> {
                submitLogin()
            }
        }
    }

    private fun submitLogin() {

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            delay(2000)
            if (_state.value.email == "user@example.com" && _state.value.password == "password") {
                _state.value = _state.value.copy(isLoading = false, isLoginSuccessful = true)
            } else {
                _state.value = _state.value.copy(isLoading = false, errorMessage = "Invalid credentials")
            }
        }
    }
}
