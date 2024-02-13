package com.dam.videojuegos.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.dam.videojuegos.modelo.Juego
import com.dam.videojuegos.ui.theme.AzulO
import com.dam.videojuegos.ui.theme.Rosa
import com.dam.videojuegos.ui.theme.Violeta

@Composable
fun GameScreen(juego: Juego, esAdmin: Boolean) {

    val imagePainter = rememberImagePainter(data = juego.foto)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AzulO)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp)
                    .padding(16.dp)
            ) {
                Icon(
                    painter = imagePainter,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = juego.titulo,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = juego.descripcion, color = Color.White)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Desarrollador: ${juego.desarrollador}", color = Color.White)
                    Text(text = "Editor: ${juego.editor}", color = Color.White)
                    Text(text = "Generos: ${juego.genero}", color = Color.White)
                    Text(text = "Idiomas: ${juego.idioma}", color = Color.White)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Puntuación: ${juego.puntuacion}", color = Color.White)
                    Text(text = "Fecha lanzamiento: ${juego.fecha}", color = Color.White)
                    Text(text = "Clasificación: ${juego.clasificacion}", color = Color.White)
                    Text(text = "Precio: ${juego.precio}", color = Color.White)
                }

                Spacer(modifier = Modifier.height(80.dp))
            }
        }
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
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
}

