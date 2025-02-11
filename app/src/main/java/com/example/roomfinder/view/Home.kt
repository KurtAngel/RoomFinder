package com.example.roomfinder.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.roomfinder.R
import com.example.roomfinder.model.Room
import com.example.roomfinder.viewmodel.AuthenticationViewModel
import com.example.roomfinder.viewmodel.ViewModelFactory
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Home : ComponentActivity() {

    private lateinit var authViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(
            this,
            ViewModelFactory(applicationContext)
        )[AuthenticationViewModel::class.java]
        val student = authViewModel.getStoredStudent()

        val roomNavType = object : NavType<Room>(isNullableAllowed = false) {
            override fun put(bundle: Bundle, key: String, value: Room) {
                bundle.putString(key, Json.encodeToString(value))
            }

            override fun get(bundle: Bundle, key: String): Room? {
                return Json.decodeFromString(bundle.getString(key) ?: "")
            }

            override fun parseValue(value: String): Room {
                return Json.decodeFromString(value)
            }
        }

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = ScreenB
            ) {
                composable<ScreenA> {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Screen A"
                        )
                    }
                }
                composable(
                    route = "ScreenA/{room}",
                    arguments = listOf(navArgument("room") { type = roomNavType })
                ) { backStackEntry ->
                    val room = backStackEntry.arguments?.getString("room")?.let {
                        Json.decodeFromString<Room>(it)
                    }
                    room?.let {
                        RequestFormScreen(room = it)
                    }
                }
                composable<ScreenB> {
                    if (student != null) {
                        HomeScreen(
                            onClick = { nav, room ->
                                when (nav) {
                                    "Request" -> navController.navigate("ScreenA/${Json.encodeToString(room)}")
                                    "Settings" -> navController.navigate(ScreenE)
                                    "Pending" -> navController.navigate(ScreenC)
                                    "Details" -> navController.navigate("ScreenF/${Json.encodeToString(room)}")
                                }
                            },
                            student
                        )
                    }
                }
                composable<ScreenC> {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        PendingScreen(onClick = { nav ->
                            when (nav) {
                                "Delete" -> navController.navigate(ScreenC)
                            }
                        })
                    }
                }
                composable<ScreenD> {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Screen D",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }
                }
                composable<ScreenE> {
                    if (student != null) {
                        SettingsScreen (
                            onClick = {
                                when (it) {
                                    "Edit" -> startActivity(Intent(this@Home, ChangePassword::class.java))
                                    "Log Out" -> {
                                    authViewModel.logout {
                                        // Navigate to login screen
                                        startActivity(
                                            Intent(this@Home, Login::class.java)
                                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                        )
                                        finish()
                                    }
                                }
                                }
                            },
                            student
                        )
                    }
                }
                composable(
                    route = "ScreenF/{room}",
                    arguments = listOf(navArgument("room") { type = roomNavType })
                ) { backStackEntry ->
                    val room = backStackEntry.arguments?.getString("room")?.let {
                        Json.decodeFromString<Room>(it)
                    }
                    room?.let {
                        DetailScreen(it)
                    }
                }
            }
            BottomMenu(navController)
        }
    }
}

@Composable
@Preview
fun BottomMenu(navController : NavController = rememberNavController()) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 26.dp),
        verticalArrangement = Arrangement.Bottom
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BottomMenuItem(
                icon = painterResource(id = R.drawable.request),
                text = "Request\n",
                onItemClick = {
                    navController.navigate(ScreenA)
                }
            )
            BottomMenuItem(icon = painterResource(id = R.drawable.file),
                text = "Rooms\n",
                onItemClick = {
                    navController.navigate(ScreenB)
                }
            )
            BottomMenuItem(icon = painterResource(id = R.drawable.form),
                text = "Pending \nRequest",
                onItemClick = {
                    navController.navigate(ScreenC)
                }
            )
            BottomMenuItem(icon = painterResource(id = R.drawable.notification_bell),
                text = "Notification\n",
                onItemClick = {
                    navController.navigate(ScreenD)
                }
            )
            BottomMenuItem(icon = painterResource(id = R.drawable.settings),
                text = "Settings\n",
                onItemClick = {
                    navController.navigate(ScreenE)
                }
            )
        }
    }
}

@Composable
fun BottomMenuItem(icon: Painter, text: String, onItemClick: () -> Unit = {}) {
    Column (
        modifier = Modifier
            .height(70.dp)
            .background(Color.White)
            .padding(4.dp)
            .clickable { onItemClick() },
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = icon,
            contentDescription = text,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 2.dp))
        Text(
            text = text,
            color = Color.Black,
            fontSize = 8.sp
        )
    }
}


@Serializable
object ScreenA

@Serializable
data class RequestFormNav(
    val index: Room
)
@Serializable
object ScreenB

@Serializable
object ScreenC

@Serializable
object ScreenD

@Serializable
object ScreenE

@Serializable
data class ScreenF(
    @Contextual
    val index: Room
)