package com.example.roomfinder.model

import com.google.gson.annotations.SerializedName

data class Student(
    val username: String ?,
    val email: String,
    @SerializedName("student_number")
    val studentNumber: String ?,
    val password: String
)

data class AuthResponse(
    val student: Student,
    val message: String,
    val token: String ?= null
)