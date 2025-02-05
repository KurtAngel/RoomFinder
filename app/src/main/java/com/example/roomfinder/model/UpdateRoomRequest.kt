package com.example.roomfinder.model

import com.google.gson.annotations.SerializedName

data class UpdateRoomRequest(
    val name: String? = null,
    val status: String? = null,
    val availability: String? = null,
    val equipment: String? = null,
    val capacity: Int? = null,
    @SerializedName("room_type")
    val roomType: String? = null
)
