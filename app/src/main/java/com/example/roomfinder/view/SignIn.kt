package com.example.roomfinder.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import com.example.roomfinder.R

//class SignIn : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            SignInScreen(
//                onClick = { mode ->
//                    if (mode == "Home") {
//                        startActivity(Intent(this, MainActivity::class.java))
//                        finish()
//                    } else if (mode == "SignUp") {
//                        startActivity(Intent(this, SignUp::class.java))
//                        finish()
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Composable
//fun SignInScreen(onClick : (String) -> Unit) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var isValid by remember { mutableStateOf(true) }
//    var errorMessage by remember { mutableStateOf("") }
//    val context = LocalContext.current
//
//    fun validateInput(): Boolean {
//        return if (email.isEmpty() || password.isEmpty()) {
//            errorMessage = "Please fill in both fields."
//            false
//        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            errorMessage = "Please enter a valid email."
//            false
//        } else {
//            errorMessage = ""
//            true
//        }
//    }
//
//
//    // Main layout of the screen
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Spacer(modifier = Modifier.height(24.dp))
//        Image(
//            painter = painterResource(id = R.drawable.upang_logo),
//            contentDescription = null,
//            modifier = Modifier
//                .size(200.dp)
//        )
//
//        Text(
//            "UPang Room Search",
//            style = MaterialTheme.typography.headlineSmall,
//            color = Color.Gray,
//            fontSize = 14.sp
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(
//            "Sign In",
//            style = MaterialTheme.typography.headlineLarge
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Email TextField
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Username") },
//            singleLine = true,
//            isError = !isValid,
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Email,
//                imeAction = ImeAction.Next
//            ),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Password TextField
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") },
//            visualTransformation = PasswordVisualTransformation(),
//            isError = !isValid,
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Password,
//                imeAction = ImeAction.Done
//            ),
//            keyboardActions = KeyboardActions(
//                onDone = {
//                    if (validateInput()) {
//                        // Call the login API or navigate
//                        context.startActivity(Intent(context, MainActivity::class.java))
//                    }
//                }
//            ),
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Error message
//        if (!isValid) {
//            Text(errorMessage, color = Color.Red, style = MaterialTheme.typography.bodyLarge)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Sign In Button
//        Button(
//            onClick = {
//                isValid = validateInput()
////                if (isValid) {
//                    onClick("Home")
////                }
//            },
//            modifier = Modifier
//                .width(200.dp),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = colorResource(id = R.color.up_blue),
//                contentColor = Color.White
//            )
//        ) {
//            Text("Sign In")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Sign Up link (optional)
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Don't have an account?")
//            Text(
//                text = " Sign Up",
//                color = MaterialTheme.colorScheme.primary,
//                modifier = Modifier
//                    .clickable {
//                        onClick("SignUp")
//                    }
//            )
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SignInScreen(
        onClick = {
            //TODO()
        }
    )
}

class SignIn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignInScreen(
                onClick = { mode ->
                    if (mode == "Home") {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else if (mode == "SignUp") {
                        startActivity(Intent(this, SignUp::class.java))
                        finish()
                    }
                }
            )
        }
    }
}

@Composable
fun SignInScreen(onClick : (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var studentNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
//    val viewModel: LoginViewModel = LoginViewModel()
//    val loginState by viewModel.loginState.collectAsState()

//     Handle login state
//    LaunchedEffect(loginState) {
//        when (loginState) {
//            is LoginState.Success -> {
//                onClick("Home")
//            }
//            is LoginState.Error -> {
//                errorMessage = (loginState as LoginState.Error).message
//                isValid = false
//            }
//            else -> {
//
//            }
//        }
//    }

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
        OutlinedTextField(
            value = studentNumber,
            onValueChange = { studentNumber = it },
            label = { Text(
                "Student Number",
            ) },
            singleLine = true,
            isError = !isValid,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = !isValid,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password TextField
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        // Error message
        if (!isValid) {
            Text(errorMessage, color = Color.Red, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign In Button
        Button(
            onClick = {
                if (validateInput()) {
//                    viewModel.login(email, email, password)
                } else {
                    Log.d("Error", "Working")
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.up_blueBtn),
                contentColor = Color.White
            )
        ) {
            if (false) {
                CircularProgressIndicator(color = Color.White)
            } else {
                Text("Sign In", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up link
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account?")
            Text(
                text = " Log In",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onClick("SignUp") }
            )
        }
    }
}
