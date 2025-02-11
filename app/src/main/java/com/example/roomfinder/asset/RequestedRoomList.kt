package com.example.roomfinder.asset

import com.example.roomfinder.model.RequestedRoom

data class RequestedRoomList(
    val requestedRooms: List<RequestedRoom> = listOf(
        RequestedRoom(
            roomId = 1,
            studentId = 1,
            roomName = "PTC 405",
            purpose = "For Practice",
            startingTime = "8:00am",
            endingTime = "9:00am",
            status = "Pending" // Approved or Reject
        ),
        RequestedRoom(
            roomId = 2,
            studentId = 2,
            roomName = "PTC 406",
            purpose = "Study Group",
            startingTime = "9:00am",
            endingTime = "10:00am",
            status = "Approved"
        ),
        RequestedRoom(
            roomId = 3,
            studentId = 3,
            roomName = "PTC 407",
            purpose = "Project Discussion",
            startingTime = "10:00am",
            endingTime = "11:00am",
            status = "Reject"
        ),
        RequestedRoom(
            roomId = 4,
            studentId = 4,
            roomName = "PTC 408",
            purpose = "Team Meeting",
            startingTime = "11:00am",
            endingTime = "12:00pm",
            status = "Pending"
        ),
        RequestedRoom(
            roomId = 5,
            studentId = 5,
            roomName = "PTC 409",
            purpose = "Workshop",
            startingTime = "12:00pm",
            endingTime = "1:00pm",
            status = "Approved"
        ),
        RequestedRoom(
            roomId = 6,
            studentId = 6,
            roomName = "PTC 410",
            purpose = "Exam Preparation",
            startingTime = "1:00pm",
            endingTime = "2:00pm",
            status = "Pending"
        ),
        RequestedRoom(
            roomId = 7,
            studentId = 7,
            roomName = "PTC 411",
            purpose = "Coding Session",
            startingTime = "2:00pm",
            endingTime = "3:00pm",
            status = "Reject"
        ),
        RequestedRoom(
            roomId = 8,
            studentId = 8,
            roomName = "PTC 412",
            purpose = "Study Session",
            startingTime = "3:00pm",
            endingTime = "4:00pm",
            status = "Pending"
        ),
        RequestedRoom(
            roomId = 9,
            studentId = 9,
            roomName = "PTC 413",
            purpose = "Team Building",
            startingTime = "4:00pm",
            endingTime = "5:00pm",
            status = "Approved"
        ),
        RequestedRoom(
            roomId = 10,
            studentId = 10,
            roomName = "PTC 414",
            purpose = "Final Presentation",
            startingTime = "5:00pm",
            endingTime = "6:00pm",
            status = "Reject"
        ),
        RequestedRoom(
            roomId = 11,
            studentId = 10,
            roomName = "PTC 415",
            purpose = "Final Presentation",
            startingTime = "5:00pm",
            endingTime = "6:00pm",
            status = "Reject"
        ),
        RequestedRoom(
            roomId = 12,
            studentId = 10,
            roomName = "PTC 416",
            purpose = "Final Presentation",
            startingTime = "5:00pm",
            endingTime = "6:00pm",
            status = "Reject"
        ),
        RequestedRoom(
            roomId = 13,
            studentId = 10,
            roomName = "PTC 417",
            purpose = "Final Presentation",
            startingTime = "5:00pm",
            endingTime = "6:00pm",
            status = "Reject"
        )
    )
)

