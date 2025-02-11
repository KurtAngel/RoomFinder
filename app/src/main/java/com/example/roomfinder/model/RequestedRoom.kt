package com.example.roomfinder.model

import com.google.gson.annotations.SerializedName

data class RequestedRoom(
    @SerializedName("room_id")
    val roomId: Int,
    @SerializedName("student_id")
    val studentId: Int ?= null,
    val status: String,
    val roomName: String,
    val purpose: String,
    @SerializedName("starting_time")
    val startingTime: String,
    @SerializedName("ending_time")
    val endingTime: String,
)


