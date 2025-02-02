package com.example.roomfinder.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import com.example.roomfinder.R

class SignUp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpScreen(
                onClick = { mode ->
                    if (mode == "Home"){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else if (mode == "SignIn"){
                        startActivity(Intent(this, SignIn::class.java))
                        finish()
                    }
                }
            )
        }
    }
}

@Composable
fun SignUpScreen(onClick : (String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

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
                .size(200.dp)
        )

        Text(
            "UPang Room Search",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Sign Up",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Username") },
            singleLine = true,
            isError = !isValid,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
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
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (validateInput()) {
                        // Call the login API or navigate
                    }
                }
            ),
            modifier = Modifier.fillMaxWidth()
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
                if (isValid) {
                    onClick("Home")
                }
            },
            modifier = Modifier
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.up_blue),
                contentColor = Color.White
            )
        ) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up link (optional)
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Don't have an account?")
            Text(
                text = " Sign In",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable {
                        onClick("SignIn")
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviews() {
    SignUpScreen(
        onClick = {
            "sadf"
        }
    )
}
