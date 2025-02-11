package com.example.roomfinder.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.roomfinder.R
import com.example.roomfinder.model.Student
import com.example.roomfinder.viewmodel.AuthenticationViewModel
import com.example.roomfinder.viewmodel.ViewModelFactory

@Preview
@Composable
fun DefaultPreview() {
    SignUpScreen(
        onClick = {
            //TODO()
        },
        context = LocalContext.current
    )
}

class SignUp : ComponentActivity() {

    private lateinit var authViewModel: AuthenticationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel = ViewModelProvider(
            this,
            ViewModelFactory(applicationContext)
        )[AuthenticationViewModel::class.java]
        if (authViewModel.studentRepository.authToken?.isNotEmpty() == true){
            startActivity(Intent(this, Home::class.java))
            finish()
        }
        setContent {
            SignUpScreen(
                onClick = { mode ->
                    if (mode == "Home") {
                        startActivity(Intent(this, Home::class.java))
                        finish()
                    } else if (mode == "Login") {
                        startActivity(Intent(this, Login::class.java))
                        finish()
                    }
                },
                context = this,
                authViewModel
            )
        }
    }
}

@Composable
fun SignUpScreen(onClick: (String) -> Unit, context: Context, authViewModel: AuthenticationViewModel? = null) {
    var email by remember { mutableStateOf("") }
    var studentNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    fun validateInput(): Boolean {
        return if (email.isEmpty() || password.isEmpty()) {
            errorMessage = "Please fill in required fields."
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Please enter a valid email."
            false
        } else if (password !== confirmPassword) {
            errorMessage = "Password didn't match."
            false
        } else {
            errorMessage = ""
            true
        }
    }

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
    // Main layout of the screen
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
                .size(150.dp)
        )

        Text(
            "UPang Room Search",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Student Number TextField

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ){
            OutlinedTextField(
                value = studentNumber,
                onValueChange = { studentNumber = it },
                label = {
                    Text(
                        "Student Number",
                    )
                },
                isError = !isValid,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email TextField
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                isError = !isValid,
                label = { Text("Email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            var passwordVisible by remember { mutableStateOf(false) }
            // Password TextField
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                isError = !isValid,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    val icon = if (passwordVisible) R.drawable.password_eye else R.drawable.password_eye_close
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "Show Password",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                passwordVisible = !passwordVisible
                            }
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            var confirmPasswordVisible by remember { mutableStateOf(false) }
            // Confirm Password TextField
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                isError = !isValid,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    val icon = if (confirmPasswordVisible) R.drawable.password_eye else R.drawable.password_eye_close
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "Show Password",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                confirmPasswordVisible = !confirmPasswordVisible
                            }
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                )
            )

            // Error message
            if (isValid) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 6.dp),
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sign In Button
            Button(
                onClick = {
                    isValid = validateInput()
                    if (validateInput()) {
                        isLoading = true
                        val student = Student(
                            username = email.split("@")[0],
                            studentNumber = studentNumber,
                            email = email,
                            password = password
                        )
                        authViewModel?.signUpStudent(
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
                    }
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
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Sign Up", fontSize = 16.sp)
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
                text = "Already have an account?",
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = " Log In",
                color = colorResource(id = R.color.link),
                modifier = Modifier.clickable { onClick("Login") },
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp
            )
        }
    }
}
