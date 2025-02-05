package com.example.roomfinder.model

import com.google.gson.annotations.SerializedName

data class CreateRoomRequest(
    val name: String,
    val status: String,
    val availability: String,
    val equipment: String,
    val capacity: Int,
    @SerializedName("room_type")
    val roomType: String
)

