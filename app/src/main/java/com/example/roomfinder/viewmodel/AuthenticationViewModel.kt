package com.example.roomfinder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutine.repository.StudentRepository
import com.example.roomfinder.RoomFinderApplication
import com.example.roomfinder.apiHandler.RetrofitClient
import com.example.roomfinder.model.AuthResponse
import com.example.roomfinder.model.Student
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthenticationViewModel(val studentRepository: StudentRepository) : ViewModel() {

    private val apiService = RetrofitClient.apiService

    fun login(
        student: Student,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                delay(2000)
                val response = apiService.loginStudent(student)
                if (response.isSuccessful) {
                    val studentResponse = response.body()
                    if (studentResponse != null) {
                        studentRepository.saveStudent(studentResponse.student)
                        studentRepository.saveToken(studentResponse.token)
                        onSuccess()
                    } else {
                        onError("Login failed: Empty response")
                    }
                } else {
                    onError("Login failed: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Invalid Email or Password")
            }
        }
    }

    fun signUpStudent(
        student: Student,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                delay(2000) // 2 seconds delay

                val response = apiService.createStudent(student)

                if (response.isSuccessful) {
                    val studentResponse = response.body()
                    if (studentResponse != null) {
                        // Save token or handle successful signup
                        studentRepository.saveStudent(studentResponse.student)
                        studentRepository.saveToken(studentResponse.token)
                        onSuccess()
                    } else {
                        onError("Signup failed: Empty response")
                    }
                } else {
                    onError("Signup failed: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Error: ${e.message ?: "Unknown error occurred"}")
            }
        }
    }

    // Add function to get stored student
    fun getStoredStudent(): Student? {
        return studentRepository.getStudent()
    }

    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                // Clear StudentRepository data
                studentRepository.clearStudent()
                studentRepository.clearToken()
                
                // Clear SessionManager data
                RoomFinderApplication.instance.sessionManager.clearSession()
                
                onComplete()
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error during logout: ${e.message}")
            }
        }
    }
}
