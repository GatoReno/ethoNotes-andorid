@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.ethonotes.Views.Login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ethonotes.Components.Alert
import com.example.ethonotes.ViewModes.LoginViewModel


@Preview
@Composable
fun LoginView(navController: NavController, loginVM: LoginViewModel = hiltViewModel<LoginViewModel>()) {
    Text(text = "LoginView",color= MaterialTheme.colorScheme.onBackground)
    Spacer(modifier = Modifier.height(20.dp))
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()){
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        val focusRequester = remember { FocusRequester() }



        OutlinedTextField(value = email,
            onValueChange = {email = it},
            label = {Text(text = "Email")},
            keyboardOptions = KeyboardOptions(keyboardType =KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
            )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = password,
            onValueChange = {password = it},
            label = {Text(text = "Password")},
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),  // Alterna la visibilidad de la contraseña
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.Done else Icons.Default.Clear
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = description)
                }
            },
            modifier = Modifier.fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .focusRequester(focusRequester)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            loginVM.loginEmail(email,password) {
                navController.navigate("Home")
            }
        }) {
            Text(text = "Login")
        }

        if (loginVM.showAlert){
            Alert(
                title = "Alert",
                message = "Something gone wrong try again.",
                confirmTxt = "ok",
                onConfirmClick = { loginVM.closeAlert()},
                onDismissClick = {})
        }
    }
}