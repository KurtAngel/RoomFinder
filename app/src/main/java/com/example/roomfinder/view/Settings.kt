package com.example.roomfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material3.HorizontalDivider
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

class Settings : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SettingsScreen()
        }
    }
}

@Composable
fun SettingsScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Row(
            modifier = Modifier
                .height(89.dp)
                .background(
                    color = colorResource(id = R.color.up_green)
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center, // Center elements horizontally
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(start = 12.dp)
            )
            Text(
                text = "Settings",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 36.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 22.dp)
            )

        }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){

        Column (
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column (
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 156.dp)
                    .background(
                        color = colorResource(id = R.color.up_itemBg),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(1.dp, colorResource(id = R.color.up_outlineBg), RoundedCornerShape(10.dp))
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ){
                ProfileInfo(text = "Student Name: John Doe")
                ProfileInfo(text = "Password: ********")
                ProfileInfo(text = "Student Number: 03-4545-0333112")

                Text(
                    text = "Phinmaed Account:",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )

                ProfileInfo(text = "GMAIL: john.cInridge@altostrat.com")
            }
        }
    }
    }
}

@Composable
fun ProfileInfo(text: String) {

    if(text == "Password: ********"){
        Column (
            modifier = Modifier.padding(horizontal = 16.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row (
                    modifier = Modifier
                ){
                    Text(
                        text = text,
                    )
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ){
                    Icon(
                        imageVector = Icons.Sharp.Edit,
                        contentDescription = null
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .background(Color.Black)
                    .padding(3.dp)
            )
        }
        Spacer(
            modifier = Modifier.padding(8.dp)
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = text
            )
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .background(Color.Black)
                    .padding(3.dp)
            )
            Spacer(
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomFinderTheme {
        SettingsScreen()
    }
}