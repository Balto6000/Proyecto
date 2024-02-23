package com.dam.videojuegos.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Brush
import coil.compose.rememberImagePainter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dam.videojuegos.R
import com.dam.videojuegos.modelo.Juego
import com.dam.videojuegos.shared.ViewModelFirebase
import com.dam.videojuegos.ui.theme.Azne
import com.dam.videojuegos.ui.theme.AzulO
import com.dam.videojuegos.ui.theme.Rosa
import com.dam.videojuegos.ui.theme.Violeta

@Composable
fun MainScreen(navController: NavHostController, idUsuario: String, esAdmin: Boolean) {

    val viewModelFirebase: ViewModelFirebase = viewModel()

    DisposableEffect(key1 = viewModelFirebase) {
        viewModelFirebase.crearListener()
        onDispose { viewModelFirebase.borrarListener() }
    }

    val listaJuegosUI = viewModelFirebase.listaJuegos.collectAsState().value
    var searchText by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AzulO)
    ) {
        Row (verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 10.dp, start = 16.dp))
        {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                modifier = Modifier
                    .clickable{ navController.popBackStack() }
            )
            Icon(painter = painterResource(id = R.drawable.bglogos),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 270.dp)
                    .size(70.dp)
            )
        }
        OutlinedTextField(
            value = searchText ?: "",
            onValueChange = { searchText = if (it.isEmpty()) null else it},
            label = { Text("Buscar", color = Color.White) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            textStyle = TextStyle(color = Color.White)
        )

        val juegosFiltrados = viewModelFirebase.filtrarJuego(listaJuegosUI, searchText)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(juegosFiltrados) { juego ->
                JuegoItem(juego, viewModelFirebase, navController, esAdmin)
            }
        }

        if (esAdmin) {
            Row {
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .height(50.dp)
                    .clickable {
                        navController.navigate(route = "add")
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
                        text = "Añadir",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JuegoItem(juego: Juego, viewModel: ViewModelFirebase, navController: NavHostController, esAdmin: Boolean) {
    val imagePainter = rememberImagePainter(data = juego.foto)

    Row(
        modifier = Modifier
            .combinedClickable(
                onClick = { navController.navigate("detalleJuego/${juego.idJuego}?admin=$esAdmin") },
                onLongClick = { viewModel.borrarJuego(juego) }
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


