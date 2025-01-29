package com.example.roomfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreen()
        }
    }
}

@Composable
@Preview
fun LoginScreen() {
    ConstraintLayout (
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    ){
        val (logo, form, titleTxt) = createRefs()
        var email by remember{ mutableStateOf("") }
        var password by remember{ mutableStateOf("") }

        Image(
            painter = painterResource(id = R.drawable.upang_logo),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(150.dp)
                .padding(top = 50.dp)
        )

        Text(
            text = "UPang Room Search",
            modifier = Modifier
                .constrainAs(titleTxt) {
                    top.linkTo(logo.bottom)
                    start.linkTo(logo.start)
                    end.linkTo(logo.end)
                }
                .padding(10.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        //Form
        ConstraintLayout (
            modifier = Modifier.constrainAs(form) {
                top.linkTo(logo.bottom)
                start.linkTo(logo.start)
                end.linkTo(logo.end)
            }
                .padding(top = 40.dp)
                .clip(RoundedCornerShape(10.dp))
//                .border(1.dp, Color.White, RoundedCornerShape(10.dp))
                .background(
                    Color.White
                )
                .height(330.dp)
                .width(300.dp)
                .shadow(10.dp, shape = RoundedCornerShape(10.dp), clip = false)
        ){
            val (emailTxt, emailInput, passwordTxt, passwordInput, loginBtn) = createRefs()
            Text(
                text = "Email",
                color = Color.Gray,
                fontSize =  17.sp,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .constrainAs(emailTxt) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 30.dp)
            )

            TextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .width(350.dp)
                    .height(60.dp)
                    .constrainAs(emailInput) {
                        top.linkTo(emailTxt.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .clip(RoundedCornerShape(30.dp))
                    .padding(horizontal = 20.dp)
                    .background(Color.White),
                placeholder = {
                    Text(
                        text = "Email",
                        color = Color.Gray
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                )
            )
            Text(
                text = "Password",
                color = Color.Gray,
                fontSize =  17.sp,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .constrainAs(passwordTxt) {
                        top.linkTo(emailInput.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 20.dp)
            )

            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .width(350.dp)
                    .height(60.dp)
                    .constrainAs(passwordInput) {
                        top.linkTo(passwordTxt.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .clip(RoundedCornerShape(30.dp))
                    .padding(horizontal = 20.dp)
                    .background(Color.White),
                placeholder = {
                    Text(
                        text = "Password",
                        color = Color.Gray
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                )
            )

            Button(
                onClick = {
//                    onClick("login")
                },
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .width(200.dp)
                    .height(40.dp)
                    .constrainAs(loginBtn) {
                        top.linkTo(passwordInput.bottom)
                        start.linkTo(passwordInput.start)
                        end.linkTo(passwordInput.end)
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.up_blue),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Sign in"
                )
            }
        }
    }
}
