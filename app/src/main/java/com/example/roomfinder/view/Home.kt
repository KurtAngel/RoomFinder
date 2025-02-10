package com.example.roomfinder.view

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roomfinder.R
import com.example.roomfinder.viewmodel.AuthenticationViewModel
import com.example.roomfinder.viewmodel.ViewModelFactory
import kotlinx.serialization.Serializable

class Home : ComponentActivity() {

    private lateinit var authViewModel: AuthenticationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(
            this,
            ViewModelFactory(applicationContext)
        )[AuthenticationViewModel::class.java]
        val student = authViewModel.getStoredStudent()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = ScreenB
            ) {
                composable<ScreenA> {
                    RequestFormScreen()
                }
                composable<ScreenB> {
                    if (student != null) {
                        HomeScreen(
                            onClick = { nav, index ->
                                when (nav) {
                                    "Request" -> navController.navigate(ScreenA)
                                    "Settings" -> navController.navigate(ScreenE)
                                    "Pending" -> navController.navigate(ScreenC)
                                    "Details" -> navController.navigate(ScreenF(index))
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
                                "Request" -> navController.navigate(ScreenA)
                                "Settings" -> navController.navigate(ScreenE)
                                "Pending" -> navController.navigate(ScreenC)
                                "Details" -> navController.navigate(ScreenF(0))
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
                            onClick = { nav ->
                                when (nav) {
                                    "edit" -> navController.navigate(ScreenF(0))
                                }
                            },
                            student
                        )
                    }
                }
                composable<ScreenF> {
                    DetailScreen()
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
                .height(60.dp)
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
object ScreenB

@Serializable
object ScreenC

@Serializable
object ScreenD

@Serializable
object ScreenE

@Serializable
data class ScreenF(
    val index: Int
)