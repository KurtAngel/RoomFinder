package com.example.roomfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomfinder.R
import com.example.roomfinder.ui.theme.RoomFinderTheme


@Composable
fun PendingScreen(onClick: (String) -> Unit = {}) {
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
            // Add your pending requests content here
//            Text(
//                text = "No pending requests",
//                fontSize = 18.sp,
//                color = Color.Gray
//            )

            Text(
                text = "Pending Requests:",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 12.dp)
                    .align(Alignment.Start)
            )
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick("Pending") },
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
                        PendingList()
                        PendingList()
                        PendingList()
                        PendingList()
                        PendingList()
                        PendingList()
                        PendingList()
                        PendingList()
                        PendingList()
                        PendingList()
                        PendingList()
                        PendingList()
                    }
                }
            }
        }

    }
}

@Composable
fun PendingList() {
    Row (
        modifier = Modifier
            .wrapContentHeight()
            .background(
                color = colorResource(id = R.color.up_yellow),
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
                text = "Room: PTC 304"
            )
            Text(
                text = "Time: 12:00PM-1:00PM",
            )
        }

        Text(
            text = "Approved",
            modifier = Modifier.padding(end = 3.dp)
        )

        Button(
            onClick = {
                //TODO()
            },
            modifier = Modifier.height(35.dp)
        ) {
            Text(
                text = "Delete"
            )
        }
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