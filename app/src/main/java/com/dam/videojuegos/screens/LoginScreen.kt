package com.dam.videojuegos.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var dialogoError by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
            modifier = Modifier.width(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.width(200.dp),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { iniciarSesion(navController, auth, email, password) {error ->
                mensajeError = error
                dialogoError = true
            }},
            modifier = Modifier.width(200.dp)
        ) {
            Text("Iniciar sesión")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("register") },
            modifier = Modifier.width(200.dp)
        ) {
            Text("Registrarse")
        }
    }

    if (dialogoError) {
        AlertDialog(
            onDismissRequest = { dialogoError = false },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.ErrorOutline, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Error de inicio de sesión", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Text(mensajeError)
            },
            confirmButton = {
                Button(
                    onClick = { dialogoError = false },
                    modifier = Modifier.width(100.dp)
                ) {
                    Text("OK")
                }
            }
        )
    }
}

private fun iniciarSesion(
    navController: NavHostController,
    auth: FirebaseAuth,
    email: String,
    password: String,
    onError: (String) -> Unit
) {
    when {
        email.isEmpty() || password.isEmpty() -> {
            onError("Debe rellenar todos los campos.")
        }
        !validarEmail(email) -> {
            onError("El formato del correo electrónico no es válido.")
        }else -> {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate("main")
                } else {
                    val exception = task.exception
                    if(exception is FirebaseAuthInvalidCredentialsException){
                        onError("Credenciales incorrectas.")
                    }else {
                        val errorMessage = exception?.message ?: "Error"
                        onError(errorMessage)
                    }

                }
            }
        }
    }

}

private fun validarEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}