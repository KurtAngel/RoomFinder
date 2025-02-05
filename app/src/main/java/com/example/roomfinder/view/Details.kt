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
import com.example.roomfinder.ui.theme.RoomFinderTheme

class Details : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DetailScreen()
        }
    }
}

@Composable
fun DetailScreen() {
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
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.padding(start = 12.dp)
            )
            Text(
                text = "Details",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 22.dp),
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
                text = "PTC 405",
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
                        text = "Availability:"
                    )

                    Text(
                        text = "Open"
                    )
                }
                Row(

                ) {
                    Text(
                        text = "Equipment:"
                    )

                    Text(
                        text = "Monitor, Television, Aircon"
                    )
                }
                Row(

                ) {
                    Text(
                        text = "Capacity:"
                    )

                    Text(
                        text = "45"
                    )
                }
                Row(

                ) {
                    Text(
                        text = "Room Type: "
                    )

                    Text(
                        text = "Lecture Room"
                    )
                }

                Column (

                ){
                    Text(
                        text = "Schedule(s):"
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
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )

                            Text(
                                text = "7:30 am - 9:00 am"
                            )
                            Text(
                                text = "9:00 am - 10:30 am"
                            )
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                text = "Block"
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
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(30.dp)
                        .align(Alignment.End)
                        .wrapContentSize(),
                    contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
                ) {
                    Text(
                        text = "Send Request"
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
        DetailScreen()
    }
}