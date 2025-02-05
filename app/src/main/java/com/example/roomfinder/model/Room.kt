package com.example.roomfinder.model

// data class Room(
//     val id: Int,
//     val name: String,
//     val status: String,
//     val availability: String,
//     val equipment: String,
//     val capacity: Int,
//     val room_type: String
// )

data class Room(
    val id: Int,
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

data class RoomSchedule(
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String,
    val purpose: String,
    val isRecurring: Boolean
)