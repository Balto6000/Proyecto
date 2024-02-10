package com.dam.videojuegos.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dam.videojuegos.R
import com.dam.videojuegos.ui.theme.Azne
import com.dam.videojuegos.ui.theme.AzulO
import com.dam.videojuegos.ui.theme.Rosa
import com.dam.videojuegos.ui.theme.Violeta
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController, auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var dialogoError by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AzulO)
            .padding(20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.bglogo),
            contentDescription = "BiblioGames Logo"
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
            modifier = Modifier.width(200.dp),
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Azne,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedLabelColor = Rosa,
                unfocusedLabelColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.width(200.dp),
            shape = RoundedCornerShape(30.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Azne,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedLabelColor = Rosa,
                unfocusedLabelColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .clickable {
                    registrarse(navController, auth, email, password) { error ->
                        mensajeError = error
                        dialogoError = true
                    }
                }
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Violeta,
                            Rosa
                        )
                    ),
                    shape = RoundedCornerShape(30.dp)
                )
        ) {
            Text(
                text = "Registrarse",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "¿Ya tienes cuenta?")
            ClickableText(
                text = AnnotatedString("Iniciar Sesión"),
                onClick = { navController.navigate("login") },
                style = TextStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            )
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
                    Text("Error de registro", fontWeight = FontWeight.Bold)
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

private fun registrarse(
    navController: NavHostController,
    auth: FirebaseAuth,
    email: String,
    password: String,
    onError: (String) -> Unit
) {
    when {
        email.isEmpty() || password.isEmpty() -> {
            onError("Debe rellenar todos los campos")
        }

        !validarEmail(email) -> {
            onError("El formato del correo electrónico no es válido")
        }

        password.length < 6 -> {
            onError("La contraseña debe tener al menos 6 caracteres.")
        }

        else -> {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navController.navigate("login")
                    } else {
                        val exception = task.exception
                        val errorMessage = exception?.message ?: "Error"
                        onError(errorMessage)
                    }
                }
        }
    }

}

private fun validarEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}