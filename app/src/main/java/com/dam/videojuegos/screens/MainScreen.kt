package com.dam.videojuegos.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dam.videojuegos.modelo.Juego
import com.dam.videojuegos.shared.ViewModelFirebase
import com.dam.videojuegos.ui.theme.Azne
import com.dam.videojuegos.ui.theme.AzulO
import com.dam.videojuegos.ui.theme.Rosa

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {

    val viewModelFirebase: ViewModelFirebase = viewModel()

    DisposableEffect(key1 = viewModelFirebase) {
        viewModelFirebase.crearListener()
        onDispose { viewModelFirebase.borrarListener() }
    }

    val listaJuegosUI = viewModelFirebase.listaJuegos.collectAsState().value
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AzulO)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Buscar", color = Color.White) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {

                }
            )
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(listaJuegosUI) { juego ->
                JuegoItem(juego, viewModelFirebase, navController)
            }
        }
        Row {
            IconButton(
                onClick = { navController.navigate(route = "add") },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(
                        color = Rosa,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Text(text = "Añadir", color = Color.White)
            }
            IconButton(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(
                        color = Rosa,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Text(text = "Borrar", color = Color.White)
            }
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(
                        color = Rosa,
                        shape = RoundedCornerShape(12.dp)
                    ),
                onClick = { },
            ) {
                Text(text = "Modificar", color = Color.White)
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JuegoItem(juego: Juego, viewModel: ViewModelFirebase, navController: NavHostController) {
    val imagePainter = rememberImagePainter(data = juego.foto)

    Row(
        modifier = Modifier
            .combinedClickable(
                onClick = { navController.navigate("detalleJuego/${juego.idJuego}")},
                onLongClick = { viewModel.actualizar(juego) }
            )
            .padding(16.dp)
            .background(
                color = Azne,
                shape = RoundedCornerShape(12.dp)
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = imagePainter,
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.padding(10.dp)) {
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Icon(imageVector = Icons.Default.Gamepad, contentDescription = "", tint = Rosa)
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = "JUEGO: ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = juego.titulo,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Money, contentDescription = "", tint = Rosa)
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = "PRECIO: ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = juego.precio.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "r", tint = Rosa)
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = "CALIFICACION: ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = juego.clasificacion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
    }
}


