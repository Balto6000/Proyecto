package com.dam.videojuegos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.dam.videojuegos.R
import com.dam.videojuegos.modelo.Juego
import com.dam.videojuegos.ui.theme.AzulO
import com.dam.videojuegos.ui.theme.Rosa
import com.dam.videojuegos.ui.theme.Violeta

@Composable
fun GameScreen(juego: Juego, esAdmin: Boolean, navController: NavController) {

    val imagePainter = rememberImagePainter(data = juego.foto)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AzulO)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                modifier = Modifier
                    .clickable { navController.popBackStack() }
            )
            Icon(
                painter = painterResource(id = R.drawable.bglogos),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 270.dp)
                    .size(70.dp)
            )
        }
        Card(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
        ) {
            Icon(
                painter = imagePainter,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .weight(1f)
            )
        }
        Text(
            text = juego.titulo,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.padding(16.dp)
        )

    LazyColumn(
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = juego.descripcion, color = Color.White)
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Desarrollador: ${juego.desarrollador}",
                    color = Color.White
                )
                Text(text = "Editor: ${juego.editor}", color = Color.White)
                Text(text = "Generos: ${juego.genero}", color = Color.White)
                Text(text = "Idiomas: ${juego.idioma}", color = Color.White)
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Puntuación: ${juego.puntuacion}", color = Color.White)
                Text(text = "Fecha lanzamiento: ${juego.fecha}", color = Color.White)
                Text(
                    text = "Clasificación: ${juego.clasificacion}",
                    color = Color.White
                )
                Text(text = "Precio: ${juego.precio}", color = Color.White)
            }
        }
    }

    Row {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { }
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
                text = if (esAdmin) "Modificar" else "Comprar",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.Center)
            )

        }
    }
}}





