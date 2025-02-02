package com.example.roomfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomfinder.apiHandler.AuthRepository
import com.example.roomfinder.models.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(studentNumber: String, email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            repository.login(studentNumber, email, password)
                .onSuccess { response ->
                    if (response.success) {
                        // Save token and user data to preferences if needed
                        _loginState.value = LoginState.Success(response)
                    } else {
                        _loginState.value = LoginState.Error(response.message)
                    }
                }
                .onFailure { exception ->
                    _loginState.value = LoginState.Error(exception.message ?: "Unknown error occurred")
                }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val data: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}