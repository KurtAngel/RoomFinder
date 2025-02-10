package com.example.roomfinder.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.roomfinder.R
import com.example.roomfinder.model.Student
import com.example.roomfinder.viewmodel.AuthenticationViewModel
import com.example.roomfinder.viewmodel.ViewModelFactory

class Login : ComponentActivity() {
    private lateinit var authViewModel: AuthenticationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(
            this,
            ViewModelFactory(applicationContext)
        )[AuthenticationViewModel::class.java]
        setContent {
            LoginScreen(
                onClick = { mode ->
                    if (mode == "Home"){
                        startActivity(Intent(this, Home::class.java))
                        finish()
                    } else if (mode == "SignUp"){
                        startActivity(Intent(this, SignUp::class.java))
                        finish()
                    }
                },
                authViewModel = authViewModel,
                context = this
            )
        }
    }
}

@Composable
fun LoginScreen(onClick : (String) -> Unit, authViewModel: AuthenticationViewModel ?= null, context: Context) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    fun validateInput(): Boolean {
        return if (email.isEmpty() || password.isEmpty()) {
            errorMessage = "Please fill in both fields."
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Please enter a valid email."
            false
        } else {
            errorMessage = ""
            true
        }
    }

    // Main layout of the screen
    val bgGradient = Brush.verticalGradient(
        listOf(
            colorResource(id = R.color.white),
            colorResource(id = R.color.gradient)
        ),
        startY = 50.0f,
        endY = 1500.0f
    )
    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.upang_image),
            contentDescription = "UPang University",
            modifier = Modifier
                .background(brush = bgGradient)
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.2f
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Image(
            painter = painterResource(id = R.drawable.upang_logo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )

        Text(
            "UPang Room Search",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))


        Spacer(modifier = Modifier.height(8.dp))

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ){
            // Email TextField
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        text = "Email",
                        color = Color.Gray
                    )
                        },
                singleLine = true,
                isError = !isValid,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = "Password",
                        color = Color.Gray
                    )
                        },
                visualTransformation = PasswordVisualTransformation(),
                isError = !isValid,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.password_eye),
                        contentDescription = "Show Password",
                        modifier = Modifier.size(30.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Error message
            if (!isValid) {
                Text(errorMessage, color = Color.Red, style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign In Button
            Button(
                onClick = {
                    isValid = validateInput()
                    isLoading = true
                    val student = Student(
                        username = email.split("@")[0],
                        studentNumber = null,
                        email = email,
                        password = password
                    )
                    authViewModel?.login(
                        student = student,
                        onSuccess = {
                            isLoading = false
                            context.startActivity(Intent(context, Home::class.java))
                        },
                        onError = { error ->
                            isLoading = false
                            errorMessage = error
                            isValid = false
                        }
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green_btn),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .height(30.dp)
                    .align(Alignment.CenterHorizontally)
                    .wrapContentWidth(),
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
            ) {
                if (isLoading && isValid) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Log In", fontSize = 16.sp)
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up link
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text(
                text = "Don't have an account?",
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = " Sign Up",
                color = colorResource(id = R.color.link),
                modifier = Modifier.clickable { onClick("SignUp") },
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreviews() {
    LoginScreen(
        onClick = {
            //TODO()
        },
        context = LocalContext.current,
        authViewModel = null
    )
}
