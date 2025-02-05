package com.example.roomfinder.model

import com.google.gson.annotations.SerializedName

data class CreateRoomRequestRequest(
    @SerializedName("room_id")
    val roomId: Int,
    @SerializedName("student_id")
    val studentId: Int,
    val purpose: String,
    @SerializedName("starting_time")
    val startingTime: String,
    @SerializedName("ending_time")
    val endingTime: String,
    val receiver: String
)
