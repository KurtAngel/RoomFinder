package com.example.roomfinder.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id: Int,
    val username: String,
    val email: String,
    @SerializedName("student_number")
    val studentNumber: String
)
