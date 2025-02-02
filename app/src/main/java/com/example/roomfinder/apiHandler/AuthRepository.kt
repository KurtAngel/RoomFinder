package com.example.roomfinder.apiHandler

import com.example.roomfinder.models.LoginRequest
import com.example.roomfinder.models.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {

    suspend fun login(studentNumber: String, email: String, password: String): Result<LoginResponse> {
        return withContext(Dispatchers.IO) {
            try {
                 val apiService = RetrofitClient.apiService
                val response = apiService.login(
                    LoginRequest(
                        studentNumber = studentNumber,
                        email = email,
                        password = password
                    )
                )

                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.success(it)
                    } ?: Result.failure(Exception("Empty response body"))
                } else {
                    Result.failure(Exception("Login failed: ${response.errorBody()?.string()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}