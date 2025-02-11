package com.example.roomfinder.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val id: Int ?= null,
    val name: String,
    val status: String,
    val availability: String,
    val equipment: String,
    val capacity: Int,
    val roomType: String,
    val building: String,
    val floor: Int,
    val schedule: List<RoomSchedule> = emptyList()
)
@Serializable
data class RoomSchedule(
    val startTime: String,
    val endTime: String,
    val purpose: String,
)