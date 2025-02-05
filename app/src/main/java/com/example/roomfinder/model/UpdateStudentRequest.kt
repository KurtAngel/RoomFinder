package com.example.roomfinder.model

import com.google.gson.annotations.SerializedName

data class UpdateStudentRequest(
    val username: String? = null,
    val email: String? = null,
    val password: String? = null,
    @SerializedName("student_number")
    val studentNumber: String? = null
)
