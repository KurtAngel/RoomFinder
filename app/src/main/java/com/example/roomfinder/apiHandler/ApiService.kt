package com.example.roomfinder.apiHandler

import com.example.roomfinder.models.LoginRequest
import com.example.roomfinder.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}