package com.example.roomfinder.asset

import com.example.roomfinder.model.Room
import com.example.roomfinder.model.RoomSchedule

data class RoomList(
    val rooms: List<Room> = listOf(
        Room(
            id = 1,
            name = "PTC 405",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Outlet",
            capacity = 45,
            roomType = "Laboratory Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "8:00am",
                    endTime = "9:00am",
                    purpose = "For Practice"
                ),
                RoomSchedule(
                    startTime = "9:00am",
                    endTime = "10:00am",
                    purpose = "For Practice"
                )
            )
        ),
        Room(
            id = 2,
            name = "PTC 406",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Outlet",
            capacity = 45,
            roomType = "Laboratory Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "8:00am",
                    endTime = "9:00am",
                    purpose = "For Practice"
                )
            )
        ),
        Room(
            id = 3,
            name = "PTC 402",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Outlet",
            capacity = 45,
            roomType = "Laboratory Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "8:00am",
                    endTime = "9:00am",
                    purpose = "For Practice"
                )
            )
        ),
        Room(
            id = 4,
            name = "PTC 407",
            status = "Available",
            availability = "Open",
            equipment = "Projector, Whiteboard, Outlet",
            capacity = 30,
            roomType = "Classroom",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "9:00am",
                    endTime = "10:00am",
                    purpose = "Lecture"
                )
            )
        ),
        Room(
            id = 5,
            name = "PTC 408",
            status = "Booked",
            availability = "Closed",
            equipment = "Monitor, Whiteboard, Video Conferencing",
            capacity = 50,
            roomType = "Conference Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "10:00am",
                    endTime = "12:00pm",
                    purpose = "Meeting"
                ),
                RoomSchedule(
                    startTime = "1:00pm",
                    endTime = "3:00pm",
                    purpose = "Team Discussion"
                )
            )
        ),
        Room(
            id = 6,
            name = "PTC 409",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Outlet, Air Conditioning",
            capacity = 40,
            roomType = "Seminar Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "2:00pm",
                    endTime = "4:00pm",
                    purpose = "Seminar"
                )
            )
        ),
        Room(
            id = 7,
            name = "PTC 410",
            status = "Available",
            availability = "Open",
            equipment = "Projector, Whiteboard, Outlet",
            capacity = 35,
            roomType = "Classroom",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "3:00pm",
                    endTime = "6:00pm",
                    purpose = "Workshop"
                )
            )
        ),

        Room(
            id = 8,
            name = "PTC 411",
            status = "Available",
            availability = "Open",
            equipment = "Projector, Whiteboard, Outlet",
            capacity = 40,
            roomType = "Classroom",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "8:00am",
                    endTime = "9:00am",
                    purpose = "Lecture"
                )
            )
        ),
        Room(
            id = 9,
            name = "PTC 412",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Air Conditioning",
            capacity = 30,
            roomType = "Laboratory Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "9:00am",
                    endTime = "10:00am",
                    purpose = "Lab Session"
                )
            )
        ),
        Room(
            id = 10,
            name = "PTC 413",
            status = "Available",
            availability = "Open",
            equipment = "Projector, Whiteboard, Air Conditioning",
            capacity = 40,
            roomType = "Seminar Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "10:00am",
                    endTime = "12:00pm",
                    purpose = "Seminar"
                )
            )
        ),
        Room(
            id = 11,
            name = "PTC 414",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Video Conferencing",
            capacity = 50,
            roomType = "Conference Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "12:00pm",
                    endTime = "2:00pm",
                    purpose = "Conference"
                )
            )
        ),
        Room(
            id = 12,
            name = "PTC 415",
            status = "Booked",
            availability = "Closed",
            equipment = "Monitor, Whiteboard, Video Conferencing, Air Conditioning",
            capacity = 45,
            roomType = "Laboratory Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "2:00pm",
                    endTime = "4:00pm",
                    purpose = "Lab Session"
                )
            )
        ),
        Room(
            id = 13,
            name = "PTC 416",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Outlet",
            capacity = 40,
            roomType = "Classroom",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "4:00pm",
                    endTime = "5:00pm",
                    purpose = "Workshop"
                )
            )
        ),
        Room(
            id = 14,
            name = "PTC 417",
            status = "Available",
            availability = "Open",
            equipment = "Projector, Whiteboard, Outlet",
            capacity = 35,
            roomType = "Classroom",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "5:00pm",
                    endTime = "6:00pm",
                    purpose = "Lecture"
                )
            )
        ),
        Room(
            id = 15,
            name = "PTC 418",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Video Conferencing",
            capacity = 50,
            roomType = "Conference Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "6:00pm",
                    endTime = "8:00pm",
                    purpose = "Board Meeting"
                )
            )
        ),
        Room(
            id = 16,
            name = "PTC 419",
            status = "Available",
            availability = "Open",
            equipment = "Projector, Whiteboard, Air Conditioning",
            capacity = 35,
            roomType = "Seminar Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "8:00pm",
                    endTime = "10:00pm",
                    purpose = "Seminar"
                )
            )
        ),
        Room(
            id = 17,
            name = "PTC 420",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Outlet",
            capacity = 45,
            roomType = "Laboratory Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "10:00pm",
                    endTime = "11:00pm",
                    purpose = "Practice"
                )
            )
        ),
        Room(
            id = 18,
            name = "PTC 421",
            status = "Booked",
            availability = "Closed",
            equipment = "Projector, Whiteboard, Air Conditioning",
            capacity = 40,
            roomType = "Classroom",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "11:00am",
                    endTime = "01:00am",
                    purpose = "Lecture"
                )
            )
        ),
        Room(
            id = 19,
            name = "PTC 422",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Outlet",
            capacity = 50,
            roomType = "Conference Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "01:00am",
                    endTime = "03:00am",
                    purpose = "Meeting"
                )
            )
        ),
        Room(
            id = 20,
            name = "PTC 423",
            status = "Available",
            availability = "Open",
            equipment = "Monitor, Whiteboard, Air Conditioning",
            capacity = 30,
            roomType = "Seminar Room",
            building = "PTC",
            floor = 4,
            schedule = listOf(
                RoomSchedule(
                    startTime = "03:00pm",
                    endTime = "05:00pm",
                    purpose = "Workshop"
                )
            )
        )
    )
)

