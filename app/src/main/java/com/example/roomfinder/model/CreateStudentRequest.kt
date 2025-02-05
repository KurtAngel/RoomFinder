package com.example.roomfinder.model

import com.google.gson.annotations.SerializedName

data class CreateStudentRequest(
    val username: String,
    @SerializedName("student_number")
    val studentNumber: String,
    val email: String,
    val password: String
)
