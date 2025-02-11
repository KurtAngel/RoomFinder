package com.example.roomfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomfinder.R
import com.example.roomfinder.RoomFinderApplication
import com.example.roomfinder.model.CreateRoomRequest
import com.example.roomfinder.model.Room
import com.example.roomfinder.model.RoomSchedule
import com.example.roomfinder.viewmodel.RoomRequestViewModel

@Composable
fun RequestFormScreen(
    viewModel: RoomRequestViewModel = RoomRequestViewModel(),
    room: Room
) {
    var purpose by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }

    val selectedRoom by viewModel.selectedRoom.collectAsState()
    val requestStatus by viewModel.requestStatus.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Row(
            modifier = Modifier
                .height(89.dp)
                .fillMaxWidth()
                .background(colorResource(id = R.color.up_green)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Request Room",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 36.sp
            )
        }
        Column (
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 50.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
        ){
            // Form Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.up_itemBg),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = room.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                selectedRoom?.let { room ->
                    Text(
                        text = "Selected Room: ${room.name}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Purpose: ",
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        value = purpose,
                        onValueChange = { purpose = it },
                        label = { Text("Purpose", color = Color.Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    )
                }
                Column (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "Available Time Slots:",
                        fontWeight = FontWeight.Bold
                    )

                    val timeRanger = room.schedule.map { "${it.startTime} - ${it.endTime}" }

                    val selectedTimeRange = remember { mutableStateOf(timeRanger[0]) }
                    startTime = selectedTimeRange.value.split(" ")[0]
                    endTime = selectedTimeRange.value.split(" ")[2]
                    Text(
                        text = "Selected Time Range: $startTime - $endTime",
                        fontWeight = FontWeight.Bold
                    )
                    timeRanger.forEach { timeRange ->
                        TimeRangeRadioButton(
                            timeRange = timeRange,
                            selectedTimeRange = selectedTimeRange.value,
                            onSelectTimeRange = { selectedTimeRange.value = it }
                        )
                    }
                }

                Button(
                    onClick = {
                        selectedRoom?.let { room ->
                            room.id?.let {
                                CreateRoomRequest(
                                    roomId = it,
                                    studentId = RoomFinderApplication.instance.sessionManager.userId ?: return@Button,
                                    purpose = purpose,
                                    startingTime = startTime,
                                    endingTime = endTime,
                                    status = null,
                                    roomName = room.name
                                )
                            }?.let {
                                viewModel.submitRequest(
                                    it
                                )
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.up_blueBtn),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .height(30.dp)
                        .align(Alignment.CenterHorizontally)
                        .wrapContentSize(),
                    contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
                    enabled = selectedRoom != null && purpose.isNotBlank() &&
                            startTime.isNotBlank() && endTime.isNotBlank()
                ) {
                    Text(
                        text = "Submit Request"
                    )
                }

                when (val status = requestStatus) {
                    is RoomRequestViewModel.RequestStatus.Success -> {
                        Text(
                            text = status.message,
                            color = Color.Green,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    is RoomRequestViewModel.RequestStatus.Error -> {
                        Text(
                            text = status.message,
                            color = Color.Red,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    is RoomRequestViewModel.RequestStatus.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                    else -> Unit
                }
            }
        }

    }
}


@Composable
fun TimeRangeRadioButton(timeRange: String, selectedTimeRange: String, onSelectTimeRange: (String) -> Unit) {
    val startTime = timeRange.split(" - ")[0]
    val endTime = timeRange.split(" - ")[1]
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp, end = 8.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = startTime,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.startTime_ind),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(8.dp)
            )
        }
        Text(
            text = " --- ",
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = endTime,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(
                        color= colorResource(id = R.color.endTime_ind),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .padding(8.dp)
            )
        }
        RadioButton(
            selected = timeRange == selectedTimeRange,
            onClick = { onSelectTimeRange(timeRange) },
            colors = RadioButtonDefaults.colors(
                selectedColor = colorResource(id = R.color.up_blueBtn),
                unselectedColor = Color.Gray
            )
        )
    }
}

@Composable
@Preview
fun RequestFormPreview(){
    RequestFormScreen(room = Room(
        id = 1,
        name = "PTC 123",
        status = null.toString(),
        availability = "Open",
        equipment = "Outlet",
        capacity = 23,
        roomType = "Lecture Room",
        building = "PTC",
        floor = 1,
        schedule = listOf(
            RoomSchedule(
                startTime = "8:00 AM",
                endTime = "9:00 PM",
                purpose = "Practice"
            ),
            RoomSchedule(
                startTime = "9:00 AM",
                endTime = "10:00 PM",
                purpose = "Practice"
            ),
            RoomSchedule(
                startTime = "11:00 AM",
                endTime = "12:00 PM",
                purpose = "Practice"
            ),
            RoomSchedule(
                startTime = "1:00 AM",
                endTime = "2:00 PM",
                purpose = "Practice"
            ),
            RoomSchedule(
                startTime = "3:00 AM",
                endTime = "4:00 PM",
                purpose = "Practice"
            )
        )
    )
    )
}
