package com.example.roomfinder.apiHandler

import com.example.roomfinder.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class AuthRepository {

//    suspend fun login(username: String, studentNumber: String, email: String, password: String): Result<*> {
//        return withContext(Dispatchers.IO) {
//            try {
//                val apiService = RetrofitClient.apiService
//                val response = apiService.createStudent(
//                    Student(
//                        username = username,
//                        studentNumber = studentNumber,
//                        email = email,
//                        password = password
//                    )
//                )
//
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        Result.success(it)
//                    } ?: Result.failure(Exception("Empty response body"))
//                } else {
//                    Result.failure(Exception("Login failed: ${response.errorBody()?.string()}"))
//                }
//            } catch (e: Exception) {
//                Result.failure(e)
//            }
//        }
//    }
}