package com.dam.videojuegos.screens

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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, auth: FirebaseAuth) {
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
        Row {
            Box(modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .clickable { navController.navigate("main/${auth.currentUser?.uid}?admin=true") }
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
                    text = "Admin",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Box(modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .clickable { navController.navigate("main/${auth.currentUser?.uid}?admin=false") }
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
                    text = "User",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Icon(
            painter = painterResource(id = R.drawable.bglogos),
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
            ),
            textStyle = TextStyle(color = Color.White)
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
            ),
            textStyle = TextStyle(color = Color.White)
        )
        Spacer(modifier = Modifier.height(60.dp))
        Box(modifier = Modifier
            .width(200.dp)
            .height(50.dp)
            .clickable {
                iniciarSesion(navController, auth, email, password) { error ->
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
                text = "Iniciar sesión",
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
            Text(text = "¿No tienes cuenta?")
            ClickableText(
                text = AnnotatedString("Regístrate"),
                onClick = { navController.navigate("register") },
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
        }

        else -> {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val db = FirebaseFirestore.getInstance()
                        val userRef = db.collection("Usuarios").document(auth.currentUser!!.uid)

                        userRef.get()
                            .addOnSuccessListener { document ->
                                if (document.exists()) {
                                    val esAdmin = document.getBoolean("administrador")

                                    if (esAdmin != null) {
                                        navController.navigate("main/${auth.currentUser?.uid}?admin=$esAdmin")
                                    } else {
                                        navController.navigate("main/${auth.currentUser?.uid}?admin=false")
                                    }
                                } else {
                                    onError("Error al obtener la información del usuario.")
                                }
                            }
                            .addOnFailureListener { e ->
                                onError("Error al obtener la información del usuario: ${e.message}")
                            }
                    } else {
                        val exception = task.exception
                        if (exception is FirebaseAuthInvalidCredentialsException) {
                            onError("Credenciales incorrectas.")
                        } else {
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