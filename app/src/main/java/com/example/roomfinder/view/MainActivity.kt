package com.example.roomfinder.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.roomfinder.R
import com.example.roomfinder.asset.RoomList
import com.example.roomfinder.model.Room
import com.example.roomfinder.model.Student
import com.example.roomfinder.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onClick: (nav: String, room: Room) -> Unit, student: Student) {
    val viewModel = MainViewModel()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    viewModel.rooms.collectAsStateWithLifecycle()

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                roomScroll,
                emailTxt,
                requestTab,
                statusTxt) = createRefs()

            Text(
                text = student.username ?: "No Name",
                modifier = Modifier
                    .constrainAs(nameTxt) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 10.dp),
                fontSize = 20.sp
            )

            Text(
                text = student.email,
                color = Color.Gray,
                modifier = Modifier
                    .constrainAs(emailTxt) {
                        top.linkTo(nameTxt.bottom)
                        start.linkTo(nameTxt.start)
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.eye_password),
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
                    .padding(top = 5.dp)
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
            val roomDetails = RoomList()
            LazyColumn (
                modifier = Modifier
                    .constrainAs(roomScroll) {
                        top.linkTo(statusTxt.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxSize(),
            ){
                item {
                    if (isLoading){
                        Box (
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(150.dp)
                        ){
                            CircularProgressIndicator()
                        }
                    } else {
                        roomDetails.rooms.forEach {
                            RoomItems(onClick = { nav, room -> onClick(nav, room) }, roomDetails = listOf(it))
                        }

                        Spacer(modifier = Modifier.padding(bottom = 230.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun RoomItems(onClick : (String, Room) -> Unit, roomDetails: List<Room> = emptyList()) {

    Row(
        modifier = Modifier
            .height(100.dp)
            .background(
                color = colorResource(id = R.color.up_itemBg),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(start = 10.dp)
    ) {
        roomDetails.forEachIndexed { index, it ->
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
                    Text("Room: ${it.name}")
                    Text("Status: ${it.status}")
                }
                Column (
                    modifier = Modifier.padding(bottom = 10.dp)
                ){
                    Button(
                        onClick = {
                            onClick("Details", roomDetails[index])
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
                        onClick("Request", roomDetails[index])
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
    }
    Spacer(modifier = Modifier.padding(8.dp))
}

@Composable
@Preview
fun Preview() {
    HomeScreen(onClick = { nav, index ->

    }, student = Student(
        username = "Angel",
        studentNumber = "212-12",
        email = "angel@phinmaed.com",
        password = "0"
    ))
}


