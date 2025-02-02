package com.example.roomfinder.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.roomfinder.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen(
                onClick = { nav ->
                    if(nav == "Request") {
                        startActivity(Intent(this, RequestForm::class.java))
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onClick: (String) -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Row(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.up_green)
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.upang_logo),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "UPang Room Search",
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp),
                fontSize = 16.sp
            )

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(30.dp),
                tint = Color.White
            )
        }
        ConstraintLayout (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp)
        ){
            var searchInput by remember { mutableStateOf("") }
            var activeInput by remember { mutableStateOf(false) }
            val (nameTxt,
                settingIcon,
                searchBar,
                pendingTxt,
                sortTxt,
                roomScroll,
                pendingScroll,
                roomTab,
                requestTab,
                statusTxt) = createRefs()

            Text(
                text = "Albert Einstein",
                modifier = Modifier
                    .constrainAs(nameTxt) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 10.dp),
                fontSize = 20.sp
            )

            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(settingIcon) {
                        top.linkTo(nameTxt.top)
                        end.linkTo(requestTab.start)
                    }
                    .padding(top = 10.dp, end = 6.dp)
                    .size(30.dp)
            )
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(requestTab) {
                        top.linkTo(settingIcon.top)
                        end.linkTo(searchBar.end)
                    }
                    .padding(top = 10.dp)
                    .size(30.dp)
            )

            SearchBar(
                query = searchInput,
                onQueryChange = {
                    searchInput = it
                },
                onSearch = {
                    activeInput = false
                },
                active = activeInput,
                onActiveChange = {
                    activeInput = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .constrainAs(searchBar) {
                        top.linkTo(nameTxt.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                placeholder = {
                    Text(text = "Search Available Rooms...")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            ) {

            }

            Text(
                text = "Room Status:",
                fontSize = 16.sp,
                modifier = Modifier
                    .constrainAs(statusTxt) {
                        top.linkTo(searchBar.bottom)
                        start.linkTo(searchBar.start)
                    }
                    .padding(bottom = 10.dp)
            )

            LazyColumn (
                modifier = Modifier
                    .constrainAs(roomScroll) {
                        top.linkTo(statusTxt.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(400.dp)
                    .fillMaxWidth(),
            ){
                item {
                    RoomItems(onClick = { onClick("Request") })
                    RoomItems(onClick = { onClick("Request") })
                    RoomItems(onClick = { onClick("Request") })
                    RoomItems(onClick = { onClick("Request") })
                    RoomItems(onClick = { onClick("Request") })
                    RoomItems(onClick = { onClick("Request") })
                    RoomItems(onClick = { onClick("Request") })
                }
            }

            Text(
                text = "Pending Requests:",
                fontSize = 16.sp,
                modifier = Modifier
                    .constrainAs(pendingTxt) {
                        start.linkTo(roomScroll.start)
                        top.linkTo(roomScroll.bottom)
                    }
                    .padding(top = 8.dp, bottom = 12.dp)
            )

            LazyColumn (
                modifier = Modifier
                    .constrainAs(pendingScroll) {
                        top.linkTo(pendingTxt.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(400.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                item {
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

@Composable
fun RoomItems(onClick : (String) -> Unit) {
    Row(
        modifier = Modifier
            .height(100.dp)
            .background(
                color = colorResource(id = R.color.up_itemBg),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(start = 10.dp)
    ) {
        Column (
            modifier = Modifier
                .padding(top = 10.dp)
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ){
            Column (
                modifier = Modifier.weight(1f)
            ){
                Text("Room: PTC 304")
                Text("Status: Available")
            }
            Column (
                modifier = Modifier.padding(bottom = 10.dp)
            ){
                Button(
                    onClick = {
                        //TODO()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor =
                    colorResource(R.color.up_greenBtn)
                    ),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .height(30.dp)
                        .wrapContentWidth(),
                    contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
                ) {
                    Text(
                        text = "More Details",
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
        Column (
            modifier = Modifier
                .padding(bottom = 10.dp, end = 10.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                onClick = {
                    onClick("Request")
                },
                colors = ButtonDefaults.buttonColors(containerColor =
                colorResource(R.color.up_blueBtn)
                ),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .height(30.dp)
                    .wrapContentSize(),
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
            ) {
                Text(
                    text = "Request",
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(8.dp))
}

@Composable
@Preview
fun Preview() {
    HomeScreen(onClick = {

    })
}


