package com.example.roomfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import com.example.roomfinder.model.CreateRoomRequestRequest
import com.example.roomfinder.RoomFinderApplication
import com.example.roomfinder.viewmodel.RoomRequestViewModel

class RequestForm : ComponentActivity() {
    private val viewModel: RoomRequestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RequestFormScreen(viewModel)
        }
    }
}

@Composable
fun RequestFormScreen(
    viewModel: RoomRequestViewModel = RoomRequestViewModel()
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

        // Form Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            selectedRoom?.let { room ->
                Text(
                    text = "Selected Room: ${room.name}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedTextField(
                value = purpose,
                onValueChange = { purpose = it },
                label = { Text("Purpose", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            )

            OutlinedTextField(
                value = startTime,
                onValueChange = { startTime = it },
                label = { Text("Start Time", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            )

            OutlinedTextField(
                value = endTime,
                onValueChange = { endTime = it },
                label = { Text("End Time", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            )

            Button(
                onClick = {
                    selectedRoom?.let { room ->
                        room.id?.let {
                            CreateRoomRequestRequest(
                                roomId = it,
                                studentId = RoomFinderApplication.instance.sessionManager.userId ?: return@Button,
                                purpose = purpose,
                                startingTime = startTime,
                                endingTime = endTime,
                                receiver = "admin" // This should be dynamically set based on room admin
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

@Composable
fun Selection(timeTxt: String){
    val fromTime = timeTxt.split("-")
    val from = fromTime[0]
    val to = fromTime[1]
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = from,
            Modifier.background(
                color = Color.Green, shape = RoundedCornerShape(5.dp)
            )
                .padding(6.dp)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null
        )
        Text(
            text = to,
            Modifier.background(
                color = Color.Red, shape = RoundedCornerShape(5.dp)
            ).padding(6.dp)
        )
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null
        )
    }
    Spacer(
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
@Preview
fun RequestFormPreview(){
    RequestFormScreen()
}

