package com.example.roomfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.example.roomfinder.model.Student


@Composable
fun SettingsScreen(onClick: (nav : String) -> Unit = {}, student: Student) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .height(89.dp)
                .background(colorResource(id = R.color.up_green))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Settings",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 36.sp,
                modifier = Modifier
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
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
                ) {
                    ProfileInfo(text = "Student Name: ${student.username}")
                    ProfileInfo(text = "Password: ********")
                    ProfileInfo(text = "Student Number: ${student.studentNumber}")

                    Text(
                        text = "Phinmaed Account:",
                        fontSize = 22.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    ProfileInfo(text = "GMAIL: ${student.email}", onClick = { nav ->
                        onClick(nav)
                    })
                }
            }
        }
    }
}

@Composable
fun ProfileInfo(text: String, onClick: (nav: String) -> Unit = {}) {
    if (text == "Password: ********") {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                ) {
                    Text(
                        text = text,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pencil_edit),
                        contentDescription = "Edit",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                onClick("edit")
                            }
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
        SettingsScreen(
            onClick = {
                //TODO()
            },
            student = Student(
                studentNumber = "123-123",
                email = "angel@phinmaed.com",
                password = "123123",
                username = "kurt"
            )
        )
    }
}