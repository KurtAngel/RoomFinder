package com.example.roomfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomfinder.R
import com.example.roomfinder.asset.RequestedRoomList
import com.example.roomfinder.asset.RoomList
import com.example.roomfinder.model.RequestedRoom
import com.example.roomfinder.ui.theme.RoomFinderTheme


@Composable
fun PendingScreen(onClick: (String) -> Unit = {}) {

    fun onClick(nav: String) {

    }
    val isLoading by remember { mutableStateOf(false) }
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
                text = "Pending Requests",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier,
                textAlign = TextAlign.Center,
                fontSize = 36.sp
            )
        }

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val roomRequest = RequestedRoomList()
            // Add your pending requests content here
            if (roomRequest != null) {
                LazyColumn (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    item {
                        if (isLoading){
                            Box (
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(70.dp)
                            ){
                                CircularProgressIndicator()
                            }
                        } else {
                            roomRequest.requestedRooms.forEach { index ->
                                PendingList(index, onClick = { nav -> onClick(nav)})
                            }
                        }
                    Spacer(modifier = Modifier.padding(bottom = 150.dp))
                    }
                }
            } else {
                Text(
                    text = "No pending requests",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun PendingList(room: RequestedRoom, onClick: (String) -> Unit) {
    // Define color based on the status
    val statusColor = when (room.status) {
        "Approved" -> colorResource(id = R.color.green_ind)
        "Reject" -> colorResource(id = R.color.red_ind)
        else -> colorResource(id = R.color.yellow_ind)
    }
    Row (
        modifier = Modifier
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.up_itemBg),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = "Room: ${room.roomName}"
            )
            Text(
                text = "Time: ${room.startingTime} - ${room.endingTime}",
            )
        }

        Column (
            modifier = Modifier
                .padding(end = 10.dp)
                .background(
                    color = statusColor,
                    shape = RoundedCornerShape(4.dp)
                ),
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = room.status,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
                fontSize = 14.sp
            )
        }

        Image(
            painter = painterResource(id = R.drawable.trash_bin),
            contentDescription = "Trash",
            modifier = Modifier
                .size(20.dp)
                .padding(end = 3.dp)
                .clickable {
                    onClick("Delete")
                }
        )
    }

    Spacer(
        modifier = Modifier.padding(bottom = 10.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PendingScreenPreview() {
    RoomFinderTheme {
        PendingScreen()
    }
}