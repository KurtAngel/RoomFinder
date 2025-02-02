package com.example.roomfinder.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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

class RequestForm : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormScreen()
        }
    }
}


@Composable
@Preview
fun FormScreen(onClickListener: () -> Unit = {}) {

    val isError by remember { mutableStateOf(false) }
    //parent container
    Column (
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ){
        //header title
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
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(start = 12.dp)
            )
            Text(
                text = "Request Form",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
            )
        }
        Column (
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            var text by remember { mutableStateOf("") }

            //form container
            Column (
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = colorResource(id = R.color.up_itemBg),
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Column (
                    modifier = Modifier
                        .padding(16.dp)
                ){
                    Text(
                        text = "Purpose: ",
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        placeholder = {
                            Text(
                                text = "Message..."
                            )
                        },
                        supportingText = {
                            if (isError){
                                Text(
                                    text = "Required"
                                )
                            }
                        },
                        isError = isError,
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                        ,
                        shape = OutlinedTextFieldDefaults.shape,
                        colors = TextFieldDefaults.colors(
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.White,
                        ),
                        minLines = 3
                    )

                    Text(
                        text = "Available Time:"
                    )
                    //selection
                    Selection("7:30PM-9:00PM")
                    Selection("7:30PM-9:00PM")
                    Selection("7:30PM-9:00PM")
                    Selection("7:30PM-9:00PM")

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                    Button(
                        onClick = {
                            //TODO()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.up_blueBtn),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .height(30.dp)
                            .wrapContentSize(),
                        contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
                    ) {
                        Text(
                            text = "Submit",
                        )
                    }
                    }
                }
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

