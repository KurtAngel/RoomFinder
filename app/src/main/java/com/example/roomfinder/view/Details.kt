package com.example.roomfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.roomfinder.asset.RoomList
import com.example.roomfinder.model.Room
import com.example.roomfinder.model.RoomSchedule
import com.example.roomfinder.ui.theme.RoomFinderTheme

class Details : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            DetailScreen()
        }
    }
}

@Composable
fun DetailScreen(details: Room) {
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
                text = "Details",
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
            Text(
                text = details.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
            //detail container
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.up_itemBg),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(16.dp)
            ){
                Row(

                ) {
                    Text(
                        text = "Availability: ",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = details.availability
                    )
                }
                Row(

                ) {
                    Text(
                        text = "Equipment: ",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = details.equipment
                    )
                }
                Row(

                ) {
                    Text(
                        text = "Capacity: ",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = details.capacity.toString()
                    )
                }
                Row(

                ) {
                    Text(
                        text = "Room Type: ",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = details.roomType
                    )
                }

                Column (

                ){
                    Text(
                        text = "Schedule(s):",
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                            Column (
                            ){
                                Text(
                                    text = "Time",
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                details.schedule.forEach{

                                Text(
                                    text = it.startTime + " - " + it.endTime
                                )
                            }
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                text = "Block",
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Block 5"
                            )
                            Text(
                                text = "Block 7"
                            )
                        }
                    }
                }
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.up_blueBtn),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .height(30.dp)
                        .align(Alignment.End)
                        .wrapContentSize(),
                    contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
                ) {
                    Text(
                        text = "Send Request",
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    RoomFinderTheme {
        DetailScreen(
            details = Room(
                id = 2,
                name = "TODO()",
                status = "TODO()",
                availability = "TODO()",
                equipment = "TODO()",
                capacity = 2,
                roomType = "TODO()",
                building = "TODO()",
                floor = 2,
                schedule = listOf(
                    RoomSchedule(
                        startTime = "TODO()",
                        endTime = "TODO()",
                        purpose = "TODO()"
                    ),
                    RoomSchedule(
                        startTime = "TODO()",
                        endTime = "TODO()",
                        purpose = "TODO()"
                    )
                )
            )
        )
    }
}